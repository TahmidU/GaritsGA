package database.dao.reminder;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IMOTReminder;
import database.domain.reminder.MOTReminder;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MOTReminderDAO implements IMOTReminder
{
    private ArrayList<MOTReminder> motReminders;

    private Connection con;
    private IDBConnectivity connectivity;

    public MOTReminderDAO()
    {
        motReminders = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<MOTReminder> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + MOTReminder.TABLE_MOT_REMINDERS;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                motReminders.add(new MOTReminder(rs.getInt(MOTReminder.COLUMN_ID), rs.getInt(MOTReminder.COLUMN_ACCOUNT_HOLDER_ID),
                        DBDateHelper.parseDate(rs.getString(MOTReminder.INDEX_RENEWAL_DATE))));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return motReminders;
    }

    @Override
    public void save(MOTReminder motReminder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + MOTReminder.TABLE_MOT_REMINDERS + "( " + MOTReminder.COLUMN_ACCOUNT_HOLDER_ID + ","
                + MOTReminder.COLUMN_RENEWAL_DATE + ")" + " VALUES(?,?)";
        String[] values = {Integer.toString(motReminder.getAccountHolderId()), String.valueOf(motReminder.getRenewalDate())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(MOTReminder motReminder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + MOTReminder.TABLE_MOT_REMINDERS + " SET " + MOTReminder.COLUMN_ACCOUNT_HOLDER_ID + " =?," +
                MOTReminder.COLUMN_RENEWAL_DATE + " =?" + " WHERE " + MOTReminder.COLUMN_ID +
                " =" + motReminder.getId();

        String[] values = {Integer.toString(motReminder.getAccountHolderId()), String.valueOf(motReminder.getRenewalDate())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(MOTReminder motReminder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + MOTReminder.TABLE_MOT_REMINDERS + " WHERE " + MOTReminder.COLUMN_ID + "="
                + motReminder.getId();
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

        String sql = "DELETE FROM " + MOTReminder.TABLE_MOT_REMINDERS + " WHERE " + MOTReminder.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
