package database.dao.job;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IBooking;
import database.domain.job.Booking;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAO implements IBooking
{

    private ArrayList<Booking> bookings;
    private Booking booking;

    private Connection con;
    private IDBConnectivity connectivity;

    public BookingDAO(){
        bookings = new ArrayList<>();
        booking = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<Booking> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        bookings.clear();
        String sql = "SELECT * FROM " +Booking.TABLE_BOOKING;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                bookings.add( new Booking(rs.getInt(Booking.INDEX_ID),rs.getString(Booking.INDEX_JOB_TYPE),
                        DBDateHelper.parseDate(rs.getString(Booking.INDEX_DATE_BOOKED)),rs.getString(Booking.INDEX_VEHICLE_REG),
                        rs.getString(Booking.INDEX_FIRST_NAME), rs.getString(Booking.INDEX_LAST_NAME), rs.getString(Booking.INDEX_CHECK_IN)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return bookings;
    }

    @Override
    public Booking getById(int id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Booking.TABLE_BOOKING + " WHERE " + Booking.COLUMN_ID +
                "=" + id;

        booking = null;
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                booking = new Booking(rs.getInt(Booking.INDEX_ID),rs.getString(Booking.INDEX_JOB_TYPE),
                        DBDateHelper.parseDate(rs.getString(Booking.INDEX_DATE_BOOKED)),rs.getString(Booking.INDEX_VEHICLE_REG),
                        rs.getString(Booking.INDEX_FIRST_NAME), rs.getString(Booking.INDEX_LAST_NAME), rs.getString(Booking.INDEX_CHECK_IN));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return booking;
    }

    @Override
    public ArrayList<Booking> getByVehicleReg(String vehicleReg)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Booking.TABLE_BOOKING + " WHERE " + Booking.COLUMN_VEHICLE_REG +
                "='" + vehicleReg + "'";

        bookings.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                bookings.add( new Booking(rs.getInt(Booking.INDEX_ID),rs.getString(Booking.INDEX_JOB_TYPE),
                        DBDateHelper.parseDate(rs.getString(Booking.INDEX_DATE_BOOKED)),rs.getString(Booking.INDEX_VEHICLE_REG),
                        rs.getString(Booking.INDEX_FIRST_NAME), rs.getString(Booking.INDEX_LAST_NAME), rs.getString(Booking.INDEX_CHECK_IN)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return bookings;
    }

    @Override
    public void save(Booking booking){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + Booking.TABLE_BOOKING+"( "+Booking.COLUMN_JOB_TYPE+","+Booking.COLUMN_DATE_BOOKED+","+Booking.COLUMN_VEHICLE_REG
                + "," + Booking.COLUMN_FIRST_NAME + "," + Booking.COLUMN_LAST_NAME + "," + Booking.COLUMN_CHECK_IN + ")" + " VALUES(?,?,?,?,?,?)";
        String[] values = {String.valueOf(booking.getJobType()),String.valueOf(booking.getDateBooked()),String.valueOf(booking.getVehicleRegistrationNumber()),
        booking.getFirstName(), booking.getLastName(), booking.getCheckIn()};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(Booking booking){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + Booking.TABLE_BOOKING + " SET "+Booking.COLUMN_JOB_TYPE+" =?,"+Booking.COLUMN_DATE_BOOKED+" =?,"+
                Booking.COLUMN_VEHICLE_REG+" =?,"+Booking.COLUMN_FIRST_NAME+" =?,"+Booking.COLUMN_LAST_NAME+" =?,"+
                Booking.COLUMN_CHECK_IN+" =?"+" WHERE " +Booking.COLUMN_ID+ "=" +booking.getId();
        String[] values = {String.valueOf(booking.getJobType()),String.valueOf(booking.getDateBooked()),String.valueOf(booking.getVehicleRegistrationNumber()),
        booking.getFirstName(), booking.getLastName(), booking.getCheckIn()};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(Booking booking)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Booking.TABLE_BOOKING + " WHERE " + Booking.COLUMN_ID + "="
                + booking.getId();
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(int id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Booking.TABLE_BOOKING + " WHERE " + Booking.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
