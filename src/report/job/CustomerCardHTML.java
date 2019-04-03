package report.job;

import database.dao.job.VehicleDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.Vehicle;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import report.table.VehicleTable;
import util.DBDateHelper;
import util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerCardHTML implements Runnable
{
    private final String dataSource = "CustomerVehicleDataSource";
    private final String jrxmlSource = "src/report/job/customer_cards.jrxml";
    private final String pdfDest = "preview.html";

    //parameters in jrxml...
    private final String dateParam = "date";
    private final String nameParam = "name";
    private final String addressParam = "address";
    private final String postcodeParam = "postcode";
    private final String mobParam = "mob";
    private final String emailParam = "email";

    private CustomerAcc customerAcc;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<VehicleTable> vehicleTables;
    private String date;
    private String name;
    private String address;
    private String postcode;
    private String mob;
    private String email;

    public CustomerCardHTML(CustomerAcc customerAcc)
    {
        this.customerAcc = customerAcc;
    }

    @Override
    public void run()
    {

        try
        {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlSource);

            vehicles = new VehicleDAO().getByNI(customerAcc.getNationalInsurance());
            vehicleTables = new ArrayList<>();

            for(Vehicle v : vehicles)
            {
                vehicleTables.add(new VehicleTable(v.getVehicleRegistration(), v.getMake(), v.getModel(), v.getEngineSerial(), v.getChassisNum(),
                        v.getColor()));
            }



            date = DBDateHelper.parseCurrentDate().toString();
            name = customerAcc.getFirstName() + " " + customerAcc.getLastName();
            address = customerAcc.getAddressLine();
            postcode = customerAcc.getPostCode();
            mob = customerAcc.getPhoneNumber();
            email = customerAcc.getEmail();

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(vehicleTables);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(dataSource, jrBeanCollectionDataSource);
            parameters.put(dateParam, date);
            parameters.put(nameParam, name);
            parameters.put(addressParam, address);
            parameters.put(postcodeParam, postcode);
            parameters.put(mobParam, mob);
            parameters.put(emailParam, email);


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDest);
            Log.write("PDF Generation finished...");
        } catch (JRException e) {
            e.printStackTrace();
            Log.write("Something went wrong.");
        }
    }
}
