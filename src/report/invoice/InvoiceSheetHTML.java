package report.invoice;

import database.domain.job.Task;
import database.domain.part.StockPart;
import database.domain.payment.Invoice;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import report.table.InvoiceTable;
import util.DBDateHelper;
import util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class InvoiceSheetHTML implements Runnable
{
    private final String datasource = "InvoiceDataSource";
    private final String jrxmlSource = "src/report/invoice/invoice_sheet.jrxml";
    private final String pdfDest = "preview.html";

    private Invoice invoice;
    private ArrayList<Task> tasks;
    private ArrayList<InvoiceTable> invoiceTables;

    public InvoiceSheetHTML(Invoice invoice)
    {
        this.invoice = invoice;
    }

    @Override
    public void run()
    {
        try
        {
            invoiceTables = new ArrayList<>();
            tasks = invoice.getJobSheet().getTasks();

            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlSource);


            String descWork = "";

            float total = 0.0f;

            for(Task t: tasks)
            {
                StockPart s = t.getStockPart();
                descWork = descWork + "\n -" + t.getTaskDesc();
                total = total + t.getStockPart().getPrice();
                invoiceTables.add(new InvoiceTable(s.getPartName(),String.valueOf(s.getPartId()),String.valueOf(s.getPrice()),
                        String.valueOf(s.getQuantity()), String.valueOf(s.getQuantity()*s.getPrice())));
            }

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(invoiceTables);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(datasource, jrBeanCollectionDataSource);
            parameters.put("cust_name", invoice.getCustomerAcc().getFirstName() + " " + invoice.getCustomerAcc().getLastName());
            parameters.put("address", invoice.getCustomerAcc().getAddressLine() + ",\n" + invoice.getCustomerAcc().getPostCode());
            parameters.put("dear", invoice.getCustomerAcc().getFirstName() + " " + invoice.getCustomerAcc().getLastName());
            parameters.put("todays_date", String.valueOf(DBDateHelper.parseCurrentDate()));
            parameters.put("invoice_num", String.valueOf(invoice.getId()));
            parameters.put("vehicle_reg", invoice.getJobSheet().getVehicle().getVehicleRegistration());
            parameters.put("make", invoice.getJobSheet().getVehicle().getMake());
            parameters.put("model", invoice.getJobSheet().getVehicle().getModel());




            total = total + invoice.getJobSheet().getStaff().getLabourRate();
            float vat = total * 0.2f;
            float grand = total + vat;

            parameters.put("desc", descWork);
            parameters.put("total", "£" + String.valueOf(total));
            parameters.put("vat", "£"+vat);
            parameters.put("grand_total", "£"+grand);


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDest);
            Log.write("PDF Generation finished...");
        } catch (JRException e) {
            e.printStackTrace();
            Log.write("Something went wrong.");
        }
    }
}
