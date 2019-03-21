package database.dao.account;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IStaff;
import database.domain.account.Staff;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDAO implements IStaff
{

    private ArrayList<Staff> staffs;
    private Staff staff;

    private Connection con;
    private IDBConnectivity connectivity;

    public StaffDAO()
    {
        staffs = new ArrayList<>();
        staff = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<Staff> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        staffs.clear();
        String sql = "SELECT * FROM " + Staff.TABLE_STAFF;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                staffs.add(new Staff(rs.getInt(Staff.INDEX_ID), rs.getString(Staff.INDEX_USER_NAME) , rs.getString(Staff.INDEX_PASSWORD),
                        rs.getString(Staff.INDEX_FIRST_NAME), rs.getString(Staff.INDEX_LAST_NAME),
                        rs.getString(Staff.INDEX_PHONE_NUM), rs.getString(Staff.INDEX_EMAIL), rs.getString(Staff.INDEX_TYPE)));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return staffs;
    }

    @Override
    public Staff getById(int id) {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Staff.TABLE_STAFF + " WHERE " + Staff.COLUMN_ID +
                "=" + id;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                staff = new Staff(rs.getInt(Staff.INDEX_ID), rs.getString(Staff.INDEX_USER_NAME) , rs.getString(Staff.INDEX_PASSWORD),
                        rs.getString(Staff.INDEX_FIRST_NAME), rs.getString(Staff.INDEX_LAST_NAME),
                        rs.getString(Staff.INDEX_PHONE_NUM), rs.getString(Staff.INDEX_EMAIL), rs.getString(Staff.INDEX_TYPE));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return staff;
    }

    @Override
    public void save(Staff staff)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + Staff.TABLE_STAFF + "( "+ Staff.COLUMN_USER_NAME + ", " + Staff.COLUMN_PASSWORD + ", " +
                Staff.COLUMN_FIRST_NAME + ", " + Staff.COLUMN_LAST_NAME + ", " + Staff.COLUMN_PHONE_NUM + ", " +
                Staff.COLUMN_EMAIL + ", " + Staff.COLUMN_TYPE + ")" + " VALUES(?,?,?,?,?,?,?)";
        String[] values = {staff.getUserName(), staff.getPassword(), staff.getFirstName(), staff.getLastName(), staff.getPhoneNum(),
        staff.getEmail(), staff.getType()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(Staff staff)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + Staff.TABLE_STAFF + " SET " + Staff.COLUMN_USER_NAME + " =?, " + Staff.COLUMN_PASSWORD + " =?, "
                + Staff.COLUMN_FIRST_NAME + " =?," + Staff.COLUMN_LAST_NAME + " =?," + Staff.COLUMN_PHONE_NUM + " =?," +
                Staff.COLUMN_EMAIL + " =?," + Staff.COLUMN_TYPE + " =?" + " WHERE " + Staff.COLUMN_ID +
                " =" + staff.getId();

        String[] values = {staff.getUserName(), staff.getPassword()
                ,staff.getFirstName(), staff.getLastName(), staff.getPhoneNum(), staff.getEmail(), staff.getType()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(Staff staff)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Staff.TABLE_STAFF + " WHERE " + Staff.COLUMN_ID + "=" + staff.getId();
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

        String sql = "DELETE FROM " + Staff.TABLE_STAFF + " WHERE " + Staff.COLUMN_ID + "=" + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
