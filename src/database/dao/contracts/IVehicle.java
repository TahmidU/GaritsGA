package database.dao.contracts;

import database.domain.job.Vehicle;

import java.util.ArrayList;

public interface IVehicle
{
    ArrayList<Vehicle> getAll();
    void save(Vehicle vehicle);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    void delete(String reg_num);
}
