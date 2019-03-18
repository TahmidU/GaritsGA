package database.dao.contracts;

import database.domain.job.Vehicle;

import java.util.ArrayList;

public interface IVehicle
{
    ArrayList<Vehicle> getAll();
    Vehicle getByRegNum(String regNum);
    ArrayList<Vehicle> getByNI(String nI);
    void save(Vehicle vehicle);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    void delete(String reg_num);
}
