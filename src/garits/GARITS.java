/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits;

import database.dao.DBHelper;
import database.dao.account.StaffDAO;
import database.domain.account.Staff;
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
            new StaffDAO().save(new Staff(1,"SYSDBA","Masterkey", "Administrator", "Administrator", "n/a", "n/a", "Administrator"));
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
