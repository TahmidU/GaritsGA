package database.dao.job;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IJobSheet;
import database.domain.job.JobSheet;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobSheetDAO implements IJobSheet
{

    private ArrayList<JobSheet> jobSheets;
    private JobSheet jobSheet;

    private Connection con;
    private IDBConnectivity connectivity;

    public JobSheetDAO()
    {
        jobSheets = new ArrayList<>();
        jobSheet = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<JobSheet> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        jobSheets.clear();
        String sql = "SELECT * FROM " + JobSheet.TABLE_JOB_SHEET;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                jobSheets.add(new JobSheet(rs.getInt(JobSheet.INDEX_JOB_NUM), rs.getInt(JobSheet.INDEX_STAFF_ID),
                        rs.getString(JobSheet.INDEX_VEHICLE_REG), rs.getInt(JobSheet.INDEX_BOOKING_ID), rs.getString(JobSheet.INDEX_PROBLEM_DESC),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_CREATED)), rs.getString(JobSheet.INDEX_STATUS), DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_ALLOCATION_DATE)),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_COMPLETED))));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return jobSheets;
    }

    @Override
    public JobSheet getByJobNum(int jobNum)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + JobSheet.TABLE_JOB_SHEET + " WHERE " + JobSheet.COLUMN_JOB_NUM +
                "=" + jobNum;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                jobSheet = new JobSheet(rs.getInt(JobSheet.INDEX_JOB_NUM), rs.getInt(JobSheet.INDEX_STAFF_ID),
                        rs.getString(JobSheet.INDEX_VEHICLE_REG), rs.getInt(JobSheet.INDEX_BOOKING_ID), rs.getString(JobSheet.INDEX_PROBLEM_DESC),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_CREATED)), rs.getString(JobSheet.INDEX_STATUS), DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_ALLOCATION_DATE)),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_COMPLETED)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return jobSheet;
    }

    @Override
    public ArrayList<JobSheet> getByStaffId(int staffId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + JobSheet.TABLE_JOB_SHEET + " WHERE " + JobSheet.COLUMN_STAFF_ID +
                "=" + staffId;

        jobSheets.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                jobSheets.add(new JobSheet(rs.getInt(JobSheet.INDEX_JOB_NUM), rs.getInt(JobSheet.INDEX_STAFF_ID),
                        rs.getString(JobSheet.INDEX_VEHICLE_REG), rs.getInt(JobSheet.INDEX_BOOKING_ID), rs.getString(JobSheet.INDEX_PROBLEM_DESC),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_CREATED)), rs.getString(JobSheet.INDEX_STATUS), DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_ALLOCATION_DATE)),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_COMPLETED))));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return jobSheets;
    }

    @Override
    public ArrayList<JobSheet> getByVehicleReg(String vehicleReg)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + JobSheet.TABLE_JOB_SHEET + " WHERE " + JobSheet.COLUMN_VEHICLE_REG +
                "='" + vehicleReg + "'";

        jobSheets.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                jobSheets.add(new JobSheet(rs.getInt(JobSheet.INDEX_JOB_NUM), rs.getInt(JobSheet.INDEX_STAFF_ID),
                        rs.getString(JobSheet.INDEX_VEHICLE_REG), rs.getInt(JobSheet.INDEX_BOOKING_ID), rs.getString(JobSheet.INDEX_PROBLEM_DESC),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_CREATED)), rs.getString(JobSheet.INDEX_STATUS), DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_ALLOCATION_DATE)),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_COMPLETED))));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return jobSheets;
    }

    @Override
    public JobSheet getByBookingId(int bookingId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + JobSheet.TABLE_JOB_SHEET + " WHERE " + JobSheet.COLUMN_BOOKING_ID +
                "=" + bookingId;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                jobSheet = new JobSheet(rs.getInt(JobSheet.INDEX_JOB_NUM), rs.getInt(JobSheet.INDEX_STAFF_ID),
                        rs.getString(JobSheet.INDEX_VEHICLE_REG), rs.getInt(JobSheet.INDEX_BOOKING_ID), rs.getString(JobSheet.INDEX_PROBLEM_DESC),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_CREATED)), rs.getString(JobSheet.INDEX_STATUS), DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_ALLOCATION_DATE)),
                        DBDateHelper.parseDate(rs.getString(JobSheet.INDEX_DATE_COMPLETED)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return jobSheet;
    }

    @Override
    public void save(JobSheet jobSheet)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + JobSheet.TABLE_JOB_SHEET + "( "
                + JobSheet.COLUMN_STAFF_ID + "," + JobSheet.COLUMN_VEHICLE_REG + "," + JobSheet.COLUMN_BOOKING_ID
                + "," + JobSheet.COLUMN_PROBLEM_DESC + "," + JobSheet.COLUMN_DATE_CREATED + "," + JobSheet.COLUMN_STATUS + "," +
                JobSheet.COLUMN_ALLOCATION_DATE + "," + JobSheet.COLUMN_DATE_COMPLETED + ")" + " VALUES(?,?,?,?,?,?,?,?)";
        String[] values = {Integer.toString(jobSheet.getStaffId()), jobSheet.getVehicleReg(),
         Integer.toString(jobSheet.getBookingId()), jobSheet.getProblemDesc(), String.valueOf(jobSheet.getDateCreated()),
         jobSheet.getStatus(), String.valueOf(jobSheet.getAllocationDate()), String.valueOf(jobSheet.getDateCompleted())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(JobSheet jobSheet)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + JobSheet.TABLE_JOB_SHEET + " SET " + JobSheet.COLUMN_STAFF_ID + " =?," +
                JobSheet.COLUMN_VEHICLE_REG + " =?," + JobSheet.COLUMN_BOOKING_ID + " =?," + JobSheet.COLUMN_PROBLEM_DESC
                + " =?," + JobSheet.COLUMN_DATE_CREATED + " =?," + JobSheet.COLUMN_STATUS + " =?," + JobSheet.COLUMN_ALLOCATION_DATE
                + " =?," + JobSheet.COLUMN_DATE_COMPLETED + " =?" + " WHERE " + JobSheet.COLUMN_JOB_NUM +
                " =" + jobSheet.getJobNum();

        String[] values = {Integer.toString(jobSheet.getStaffId()), jobSheet.getVehicleReg(),
        Integer.toString(jobSheet.getBookingId()), jobSheet.getProblemDesc(), String.valueOf(jobSheet.getDateCreated()),
        jobSheet.getStatus(), String.valueOf(jobSheet.getAllocationDate()), String.valueOf(jobSheet.getDateCompleted())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(JobSheet jobSheet)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + JobSheet.TABLE_JOB_SHEET + " WHERE " + JobSheet.COLUMN_JOB_NUM + "="
                + jobSheet.getJobNum();
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(int job_no)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + JobSheet.TABLE_JOB_SHEET + " WHERE " + JobSheet.COLUMN_JOB_NUM + "="
                + job_no;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
