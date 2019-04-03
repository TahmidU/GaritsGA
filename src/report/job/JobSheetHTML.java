package report.job;

import database.dao.job.TaskDAO;
import database.domain.job.JobSheet;
import database.domain.job.Task;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import report.table.JobSheetTable;
import util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class JobSheetHTML implements Runnable
{
    private final String dataSource = "job_sheet_source";
    private final String jrxmlSource = "src/report/job/job_sheet.jrxml";
    private final String pdfDest = "preview.html";

    private final String jobNumParam = "job_num";
    private final String vehicleRegParam = "vehicle_reg";
    private final String makeParam = "make";
    private final String customerNameParam = "cust_name";
    private final String dateBookedParam = "booked";
    private final String modelParam = "model";
    private final String phoneParam = "phone";
    private final String workRequiredParam = "desc_required";
    private final String actualTimeParam = "est_time";

    private JobSheet jobSheet;
    private ArrayList<JobSheetTable> jobSheetTables;
    private ArrayList<Task> tasks;
    private String jobNum;
    private String customerName;
    private String vehicleReg;
    private String make;
    private String model;
    private String dateBooked;
    private String mob;
    private String workRequired = "";
    private String actualTime;

    public JobSheetHTML(JobSheet jobSheet)
    {
        this.jobSheet = jobSheet;
    }

    @Override
    public void run()
    {
        try
        {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlSource);

            jobSheetTables = new ArrayList<>();
            tasks = new TaskDAO().getByJobNum(jobSheet.getJobNum());

            int tmpTime = 0;
            for(Task t : tasks)
            {
                workRequired = workRequired + "-" + t.getTaskDesc() + " \n";
                tmpTime = tmpTime + t.getEstDuration();
                jobSheetTables.add(new JobSheetTable(t.getStockPart().getPartName(), String.valueOf(t.getStockPart().getPartId()), String.valueOf(t.getPartQty()) ));
            }

            actualTime = String.valueOf(tmpTime);
            jobNum = String.valueOf(jobSheet.getJobNum());
            customerName = jobSheet.getVehicle().getCustomerAcc().getFirstName() + " " + jobSheet.getVehicle().getCustomerAcc().getLastName();
            vehicleReg = jobSheet.getVehicle().getVehicleRegistration();
            make = jobSheet.getVehicle().getMake();
            model = jobSheet.getVehicle().getModel();
            dateBooked = String.valueOf(jobSheet.getBooking().getDateBooked());
            mob = jobSheet.getVehicle().getCustomerAcc().getPhoneNumber();

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(jobSheetTables);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(dataSource, jrBeanCollectionDataSource);
            parameters.put(workRequiredParam, workRequired);
            parameters.put(actualTimeParam, actualTime);
            parameters.put(jobNumParam, jobNum);
            parameters.put(customerNameParam, customerName);
            parameters.put(vehicleRegParam, vehicleReg);
            parameters.put(makeParam, make);
            parameters.put(modelParam, model);
            parameters.put(dateBookedParam, dateBooked);
            parameters.put(phoneParam, mob);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDest);
            Log.write("PDF Generation finished...");
        } catch (JRException e) {
            e.printStackTrace();
            Log.write("Something went wrong.");
        }
    }
}
