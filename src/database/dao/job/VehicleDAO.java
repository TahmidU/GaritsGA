package database.dao.job;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IVehicle;
import database.domain.job.Vehicle;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAO implements IVehicle {

    private ArrayList<Vehicle> vehicles;
    private Connection con;
    private IDBConnectivity connectivity;

    public VehicleDAO()
    {
        vehicles = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<Vehicle> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false .");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Vehicle.TABLE_VEHICLE;
        ResultSet rs = connectivity.read(sql, con);
        try {
            while (rs.next()) {
                vehicles.add(new Vehicle(rs.getString(Vehicle.INDEX_VEHICLE_REG), rs.getString(Vehicle.INDEX_NI), rs.getString(Vehicle.INDEX_MAKE),
                        rs.getString(Vehicle.INDEX_MODEL), rs.getString(Vehicle.INDEX_ENGINE_SERIAL), rs.getString(Vehicle.INDEX_CHASSIS_NUM),
                        rs.getString(Vehicle.INDEX_COLOR)));
            }
            Log.write("DAO: Query successful.");
        } catch (SQLException e) {
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return vehicles;
    }

    @Override
    public void save(Vehicle vehicle)
    {

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false .");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + Vehicle.TABLE_VEHICLE + "( "+ Vehicle.COLUMN_VEHICLE_REG + "," + Vehicle.COLUMN_NI + "," + Vehicle.COLUMN_MAKE + "," +
                Vehicle.COLUMN_MODEL + "," + Vehicle.COLUMN_ENGINE_SERIAL + "," + Vehicle.COLUMN_CHASSIS_NUM + "," + Vehicle.COLUMN_COLOR + ")" + " VALUES(?,?,?,?,?,?,?)";
        String[] values = {vehicle.getVehicleRegistration(),vehicle.getNationalInsurance(), vehicle.getMake(), vehicle.getModel(), vehicle.getEngineSerial(), vehicle.getChassisNum(), vehicle.getColor()};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void update(Vehicle vehicle)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }


        String sql = "UPDATE " + Vehicle.TABLE_VEHICLE + " SET " + Vehicle.COLUMN_NI + " =?," +
                Vehicle.COLUMN_MAKE + " =?," + Vehicle.COLUMN_MODEL + " =?," + Vehicle.COLUMN_ENGINE_SERIAL + " =?," +
                Vehicle.COLUMN_CHASSIS_NUM + " =?," + Vehicle.COLUMN_COLOR + " =?" + " WHERE " + Vehicle.COLUMN_VEHICLE_REG + " = '" + vehicle.getVehicleRegistration()+"'";
        System.out.println(sql);
        String[] values = {vehicle.getNationalInsurance(), vehicle.getMake(), vehicle.getModel(), vehicle.getEngineSerial(), vehicle.getChassisNum(), vehicle.getColor()};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(Vehicle vehicle)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Vehicle.TABLE_VEHICLE + " WHERE " + Vehicle.COLUMN_VEHICLE_REG + "='"
                + vehicle.getVehicleRegistration()+"'";

        connectivity.write(sql, con);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(String reg_num)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Vehicle.TABLE_VEHICLE + " WHERE " + Vehicle.COLUMN_VEHICLE_REG + "='"
                + reg_num+"'";

        connectivity.write(sql, con);
        connectivity.closeConnection(con);

    }
}
