/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits.singleton;

import javafx.scene.Scene;

/**
 *
 * @author Huntees
 */
public class AddVehicleSceneSingleton
{
    private static AddVehicleSceneSingleton instance = null;
    private static Scene scene;

    public static AddVehicleSceneSingleton getInstance()
    {
        if(instance == null)
            instance = new AddVehicleSceneSingleton();

        return instance;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        AddVehicleSceneSingleton.scene = scene;
    }
}
