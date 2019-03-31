package database.dao.account;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.ICustomerAcc;
import database.domain.account.CustomerAcc;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerAccDAO implements ICustomerAcc
{

    private ArrayList<CustomerAcc> customerAccs;
    private CustomerAcc customerAcc;

    private Connection con;
    private IDBConnectivity connectivity;

    public CustomerAccDAO()
    {
        customerAccs = new ArrayList<>();
        customerAcc = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<CustomerAcc> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        customerAccs.clear();
        String sql = "SELECT * FROM " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                customerAccs.add(new CustomerAcc(rs.getString(CustomerAcc.INDEX_NI), rs.getString(CustomerAcc.INDEX_FIRST_NAME),
                        rs.getString(CustomerAcc.INDEX_LAST_NAME), rs.getString(CustomerAcc.INDEX_ADDRESS), rs.getString(CustomerAcc.INDEX_POSTCODE),
                        rs.getString(CustomerAcc.INDEX_EMAIL), rs.getString(CustomerAcc.INDEX_PHONE)));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return customerAccs;
    }

    @Override
    public CustomerAcc getByNI(String nI)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + " WHERE " + CustomerAcc.COLUMN_NI +
                "='" + nI + "'";


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                customerAcc = new CustomerAcc(rs.getString(CustomerAcc.INDEX_NI), rs.getString(CustomerAcc.INDEX_FIRST_NAME),
                        rs.getString(CustomerAcc.INDEX_LAST_NAME), rs.getString(CustomerAcc.INDEX_ADDRESS), rs.getString(CustomerAcc.INDEX_POSTCODE),
                        rs.getString(CustomerAcc.INDEX_EMAIL), rs.getString(CustomerAcc.INDEX_PHONE));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return customerAcc;
    }

    public ArrayList<CustomerAcc> SearchAllFields(String search)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        customerAccs.clear();
        String sql = "SELECT * FROM " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + " WHERE "
                + CustomerAcc.COLUMN_NI + " LIKE '%" + search + "%' OR "
                + CustomerAcc.COLUMN_FIRST_NAME + " LIKE '%"+search+ "%' OR "
                + CustomerAcc.COLUMN_LAST_NAME + " LIKE '%"+search+"%' OR "
                + CustomerAcc.COLUMN_ADDRESS + " LIKE '%"+search+"%' OR "
                + CustomerAcc.COLUMN_POSTCODE + " LIKE '%"+search+"%' OR "
                + CustomerAcc.COLUMN_PHONE + " LIKE '%"+search+"%' OR "
                + CustomerAcc.COLUMN_EMAIL + " LIKE '%"+search+"%'";
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                customerAccs.add(new CustomerAcc(rs.getString(CustomerAcc.INDEX_NI), rs.getString(CustomerAcc.INDEX_FIRST_NAME),
                        rs.getString(CustomerAcc.INDEX_LAST_NAME), rs.getString(CustomerAcc.INDEX_ADDRESS), rs.getString(CustomerAcc.INDEX_POSTCODE),
                        rs.getString(CustomerAcc.INDEX_EMAIL), rs.getString(CustomerAcc.INDEX_PHONE)));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return customerAccs;
    }

    @Override
    public void save(CustomerAcc customerAcc)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + "( " + CustomerAcc.COLUMN_NI + ","
                + CustomerAcc.COLUMN_FIRST_NAME + "," + CustomerAcc.COLUMN_LAST_NAME + "," + CustomerAcc.COLUMN_ADDRESS + "," +
                CustomerAcc.COLUMN_POSTCODE + "," + CustomerAcc.COLUMN_EMAIL + "," + CustomerAcc.COLUMN_PHONE + ")" + " VALUES(?,?,?,?,?,?,?)";

        String[] values = {customerAcc.getNationalInsurance(), customerAcc.getFirstName(), customerAcc.getLastName(), customerAcc.getAddressLine(),
        customerAcc.getPostCode(), customerAcc.getEmail(), customerAcc.getPhoneNumber()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(CustomerAcc customerAcc)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + " SET " + CustomerAcc.COLUMN_NI + " =?," + CustomerAcc.COLUMN_FIRST_NAME + " =?," +
                CustomerAcc.COLUMN_LAST_NAME + " =?," + CustomerAcc.COLUMN_ADDRESS + " =?," + CustomerAcc.COLUMN_POSTCODE + " =?," + CustomerAcc.COLUMN_EMAIL
                + " =?," + CustomerAcc.COLUMN_PHONE + " =?" + " WHERE " + CustomerAcc.COLUMN_NI +
                " ='" + customerAcc.getNationalInsurance() + "'";

        String[] values = {customerAcc.getNationalInsurance(), customerAcc.getFirstName(), customerAcc.getLastName(), customerAcc.getAddressLine(),
        customerAcc.getPostCode(), customerAcc.getEmail(), customerAcc.getPhoneNumber()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(CustomerAcc customerAcc)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + " WHERE " + CustomerAcc.COLUMN_NI + "='"
                + customerAcc.getNationalInsurance() + "'";

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(String nationalInsuranceNumber)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + " WHERE " + CustomerAcc.COLUMN_NI + "='"
                + nationalInsuranceNumber + "'";

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
