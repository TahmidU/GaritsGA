/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits;

import database.dao.DBHelper;
import database.dao.account.StaffDAO;
import database.dao.job.VehicleDAO;
import database.domain.account.Staff;
import database.domain.job.Vehicle;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Huntees
 */
public class GARITS extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        /*For removing vehicles in db incase of corruption due to not linking customer to vehicle
        VehicleDAO vDAO = new VehicleDAO();
        ArrayList<Vehicle> vehicles = vDAO.getAll();
        
        for(int i = 0; i < vehicles.size(); ++i) {
            vDAO.delete(vehicles.get(i));
        }
        */
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("GARITS FX");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setX(600);
        stage.setY(300);

        DBHelper db = new DBHelper();
        if(db.createDB())
        {
            //Default Administrator account created if database did not exist.
            new StaffDAO().save(new Staff(0,"SYSDBA","Masterkey", "Administrator", "Administrator", "n/a", "n/a", "Administrator", 0f));
            new StaffDAO().save(new Staff(1,"alain","12345", "Alain", "Aspire", "83297482", "alain@gmail.com", "Administrator", 0f));
            new StaffDAO().save(new Staff(2,"tahmid","123", "Tahmid", "Aspire", "23894290", "tahmid@gmail.com", "Franchisee", 0f));
            new StaffDAO().save(new Staff(3,"cameron","123", "Cameron", "Aspire", "23940872304", "cameron@gmail.com", "Foreperson", 0f));
            new StaffDAO().save(new Staff(4,"mahat","123", "Mahat", "Aspire", "4328974924", "mahat@gmail.com", "Mechanic", 10f));
            new StaffDAO().save(new Staff(5,"manar","123", "Manar", "Aspire", "329842234", "manar@gmail.com", "Receptionist", 0f));
            new StaffDAO().save(new Staff(6,"1","1", "Receptionist", "1", "1", "1", "Receptionist", 0f));
            
        }
        db.createBackUpDB();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
