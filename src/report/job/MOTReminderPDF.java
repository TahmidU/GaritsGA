package report.job;

import database.domain.account.CustomerAcc;
import database.domain.job.Vehicle;
import database.domain.reminder.MOTReminder;
import net.sf.jasperreports.engine.*;
import util.DBDateHelper;
import util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class MOTReminderPDF implements Runnable
{
    private final String dataSource = "job_sheet_source";
    private final String jrxmlSource = "src/report/job/mot_reminder.jrxml";
    private final String pdfDest = "mot_reminder.pdf";

    private MOTReminder motReminder;
    private Vehicle vehicle;
    private ArrayList<MOTReminder> motReminders;
    private CustomerAcc customerAcc;

    public MOTReminderPDF(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    @Override
    public void run()
    {
        try
        {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlSource);

            motReminders = vehicle.getCustomerAcc().getAccountHolder().getMotReminders();
            customerAcc = vehicle.getCustomerAcc();

            for(MOTReminder m : motReminders)
            {
                if(m.getRenewalDate().after(DBDateHelper.parseCurrentDate()))
                {
                    motReminder = m;
                }
            }

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("cust_name", customerAcc.getFirstName() + " " + customerAcc.getLastName());
            parameters.put("address", customerAcc.getAddressLine());
            parameters.put("postcode", customerAcc.getPostCode());
            parameters.put("dear", customerAcc.getFirstName() + " " + customerAcc.getLastName());
            parameters.put("todays_date", DBDateHelper.parseCurrentDate());
            parameters.put("vehicle_reg", vehicle.getVehicleRegistration());
            parameters.put("renewal_date", motReminder.getRenewalDate().toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDest);
            Log.write("PDF Generation finished...");
        } catch (JRException e) {
            e.printStackTrace();
            Log.write("Something went wrong.");
        }
    }
}
