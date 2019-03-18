package database.dao.contracts;

import database.domain.job.Booking;

import java.util.ArrayList;

public interface IBooking
{
    ArrayList<Booking> getAll();
    Booking getById(int id);
    ArrayList<Booking> getByVehicleReg(String vehicleReg);
    void save(Booking booking);
    void update(Booking booking);
    void delete(Booking booking);
    void delete(int id);
}
