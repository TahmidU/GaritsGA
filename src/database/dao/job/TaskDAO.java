package database.dao.job;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.ITask;
import database.domain.job.Task;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskDAO implements ITask
{

    private ArrayList<Task> tasks;
    private Task task;

    private Connection con;
    private IDBConnectivity connectivity;

    public TaskDAO(){
        tasks = new ArrayList<>();
        task = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<Task> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        tasks.clear();
        String sql = "SELECT * FROM " +Task.TABLE_TASK;
        ResultSet rs = connectivity.read(sql, con);
        try{
            String dateComplete = rs.getString(Task.INDEX_DATE_TASK_COMPLETE);
            Date date = null;
            if(!dateComplete.equals("null"))
            {
                date = DBDateHelper.parseDate(dateComplete);
            }
            while(rs.next()){
                tasks.add( new Task(rs.getInt(Task.INDEX_ID), rs.getInt(Task.INDEX_JOB_NUM),rs.getString(Task.INDEX_TASK_DESC),
                        rs.getInt(Task.INDEX_EST_DURATION), rs.getInt(Task.INDEX_PARTS_QTY), date));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Failed to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return tasks;
    }

    @Override
    public Task getById(int id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Task.TABLE_TASK + " WHERE " + Task.COLUMN_ID +
                "=" + id;

        task = null;
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                String dateComplete = rs.getString(Task.INDEX_DATE_TASK_COMPLETE);
                Date date = null;
                if(!dateComplete.equals("null"))
                {
                    date = DBDateHelper.parseDate(dateComplete);
                }
                task = new Task(rs.getInt(Task.INDEX_ID),rs.getInt(Task.INDEX_JOB_NUM),rs.getString(Task.INDEX_TASK_DESC),
                    rs.getInt(Task.INDEX_EST_DURATION),rs.getInt(Task.INDEX_PARTS_QTY), date);
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return task;
    }

    @Override
    public ArrayList<Task> getByJobNum(int jobNum)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Task.TABLE_TASK + " WHERE " + Task.COLUMN_JOB_NUM +
                "=" + jobNum;

        tasks.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                String dateComplete = rs.getString(Task.INDEX_DATE_TASK_COMPLETE);
                Date date = null;
                if(!dateComplete.equals("null"))
                {
                    date = DBDateHelper.parseDate(dateComplete);
                }
                tasks.add( new Task(rs.getInt(Task.INDEX_ID), rs.getInt(Task.INDEX_JOB_NUM),rs.getString(Task.INDEX_TASK_DESC),
                        rs.getInt(Task.INDEX_EST_DURATION), rs.getInt(Task.INDEX_PARTS_QTY), date));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return tasks;
    }

    @Override
    public void save(Task task){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + Task.TABLE_TASK+"( "+Task.COLUMN_JOB_NUM+","+Task.COLUMN_TASK_DESC+","+Task.COLUMN_EST_DURATION+","+
                Task.COLUMN_PARTS_QTY+","+Task.COLUMN_DATE_TASK_COMPLETE+")"+ " VALUES(?,?,?,?,?)";
        String[] values = {String.valueOf(task.getJobNum()),String.valueOf(task.getTaskDesc()),String.valueOf(task.getEstDuration()),
                String.valueOf(task.getPartQty()),String.valueOf(task.getDateTaskComplete())};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(Task task){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + Task.TABLE_TASK + " SET "+Task.COLUMN_JOB_NUM+" =?,"+Task.COLUMN_TASK_DESC+" =?,"+
                Task.COLUMN_EST_DURATION+" =?,"+Task.COLUMN_PARTS_QTY+" =?,"+Task.COLUMN_DATE_TASK_COMPLETE+" =?"+" WHERE " +Task.COLUMN_ID+ "=" +task.getId();
        String[] values = {String.valueOf(task.getJobNum()),String.valueOf(task.getTaskDesc()),String.valueOf(task.getEstDuration()),String.valueOf(task.getPartQty()),String.valueOf(task.getDateTaskComplete())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(Task task) {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Task.TABLE_TASK + " WHERE " + Task.COLUMN_ID + "="
                + task.getId();
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

        String sql = "DELETE FROM " + Task.TABLE_TASK + " WHERE " + Task.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
