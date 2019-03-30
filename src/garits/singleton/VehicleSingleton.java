package garits.singleton;

import database.domain.job.Vehicle;

public class VehicleSingleton
{
    private static VehicleSingleton instance = null;
    private static Vehicle vehicle;

    public static VehicleSingleton getInstance()
    {
        if(instance == null)
            instance = new VehicleSingleton();

        return instance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        VehicleSingleton.vehicle = vehicle;
    }
}
