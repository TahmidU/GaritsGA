package database.dao.payment;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IOutstandingBalance;
import database.domain.payment.OutstandingBalance;
import org.sqlite.SQLiteConfig;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OutstandingBalanceDAO implements IOutstandingBalance
{
    private ArrayList<OutstandingBalance> outstandingBalances;

    private Connection con;
    private IDBConnectivity connectivity;

    public OutstandingBalanceDAO()
    {
        outstandingBalances = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<OutstandingBalance> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + OutstandingBalance.TABLE_OUTSTANDING_BALANCE;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                outstandingBalances.add(new OutstandingBalance(rs.getInt(OutstandingBalance.INDEX_ID), rs.getInt(OutstandingBalance.INDEX_STAFF_ID),
                        rs.getInt(OutstandingBalance.INDEX_ACCOUNT_HOLDER_ID), DBDateHelper.parseDate(rs.getString(OutstandingBalance.INDEX_DATE_AUTHORISED))));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return outstandingBalances;
    }

    @Override
    public void save(OutstandingBalance outstandingBalance)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + OutstandingBalance.TABLE_OUTSTANDING_BALANCE + "( "
                + OutstandingBalance.COLUMN_STAFF_ID + "," + OutstandingBalance.COLUMN_ACCOUNT_HOLDER_ID + "," +
                OutstandingBalance.COLUMN_DATE_AUTHORISED + ")" + " VALUES(?,?,?)";
        String[] values = { Integer.toString(outstandingBalance.getStaffId()),
        Integer.toString(outstandingBalance.getAccHolderId()), String.valueOf(outstandingBalance.getDateAuthorised())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(OutstandingBalance outstandingBalance)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);

        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }
        //SQLiteConfig config = new SQLiteConfig();
        //config.enforceForeignKeys(true);
        //connectivity.write("pragma foreign_keys = 1;", con);
        String sql = "UPDATE " + OutstandingBalance.TABLE_OUTSTANDING_BALANCE + " SET " +
                OutstandingBalance.COLUMN_STAFF_ID + " =?," + OutstandingBalance.COLUMN_ACCOUNT_HOLDER_ID + " =?," +
                OutstandingBalance.COLUMN_DATE_AUTHORISED + " =?" + " WHERE " + OutstandingBalance.COLUMN_ID +
                " =" + outstandingBalance.getId()+";";

        String[] values = {Integer.toString(outstandingBalance.getStaffId()),
        Integer.toString(outstandingBalance.getAccHolderId()), String.valueOf(outstandingBalance.getDateAuthorised())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(OutstandingBalance outstandingBalance)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + OutstandingBalance.TABLE_OUTSTANDING_BALANCE + " WHERE " + OutstandingBalance.COLUMN_ID + "="
                + outstandingBalance.getId();
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

        String sql = "DELETE FROM " + OutstandingBalance.TABLE_OUTSTANDING_BALANCE + " WHERE " + OutstandingBalance.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
