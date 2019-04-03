package report.invoice;

import database.domain.payment.Invoice;
import net.sf.jasperreports.engine.*;
import util.DBDateHelper;
import util.Log;

import java.util.HashMap;

public class InvoiceReminderHTML implements Runnable
{
    private final String jrxmlSource = "src/report/invoice/invoice_reminder.jrxml";
    private final String pdfDest = "preview.html";

    private Invoice invoice;

    public InvoiceReminderHTML(Invoice invoice)
    {
        this.invoice = invoice;
    }

    @Override
    public void run()
    {
        try
        {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlSource);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("cust_name", invoice.getCustomerAcc().getFirstName() + " " + invoice.getCustomerAcc().getLastName());
            parameters.put("address", invoice.getCustomerAcc().getAddressLine() + ",\n" + invoice.getCustomerAcc().getPostCode());
            parameters.put("dear", invoice.getCustomerAcc().getFirstName() + " " + invoice.getCustomerAcc().getLastName());
            parameters.put("todays_date", String.valueOf(DBDateHelper.parseCurrentDate()));
            parameters.put("reminder", String.valueOf(invoice.getId()));
            parameters.put("vehicle_reg", invoice.getJobSheet().getVehicle().getVehicleRegistration());
            parameters.put("amount", String.valueOf(invoice.getTotalAmount()));
            parameters.put("desc_date"," "+String.valueOf(invoice.getDateCreated()));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDest);
            Log.write("PDF Generation finished...");
        } catch (JRException e) {
            e.printStackTrace();
            Log.write("Something went wrong.");
        }
    }
}
