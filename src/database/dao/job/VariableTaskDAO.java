package database.dao.job;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IVariableTask;
import database.domain.job.VariableTask;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VariableTaskDAO implements IVariableTask
{

    private ArrayList<VariableTask> variableTasks;
    private Connection con;
    private IDBConnectivity connectivity;

    public VariableTaskDAO(){
        variableTasks = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<VariableTask> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        variableTasks.clear();
        String sql = "SELECT * FROM " + VariableTask.TABLE_VARIABLE_TASK;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                variableTasks.add( new VariableTask(rs.getInt(VariableTask.INDEX_TASK_ID), rs.getInt(VariableTask.INDEX_VAR_DISCOUNT_ID),
                        rs.getFloat(VariableTask.INDEX_DISCOUNT_VAL)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return variableTasks;
    }

    @Override
    public ArrayList<VariableTask> getByTaskId(int taskId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + VariableTask.TABLE_VARIABLE_TASK + " WHERE " + VariableTask.COLUMN_TASK_ID +
                "=" + taskId;
        variableTasks.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                variableTasks.add( new VariableTask(rs.getInt(VariableTask.INDEX_TASK_ID), rs.getInt(VariableTask.INDEX_VAR_DISCOUNT_ID),
                        rs.getFloat(VariableTask.INDEX_DISCOUNT_VAL)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return variableTasks;
    }

    @Override
    public ArrayList<VariableTask> getByVariableDiscountId(int variableDiscountId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + VariableTask.TABLE_VARIABLE_TASK + " WHERE " + VariableTask.COLUMN_VAR_DISCOUNT_ID +
                "=" + variableDiscountId;
        variableTasks.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                variableTasks.add( new VariableTask(rs.getInt(VariableTask.INDEX_TASK_ID), rs.getInt(VariableTask.INDEX_VAR_DISCOUNT_ID),
                        rs.getFloat(VariableTask.INDEX_DISCOUNT_VAL)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return variableTasks;
    }

    @Override
    public void save(VariableTask variableTask){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + VariableTask.TABLE_VARIABLE_TASK+
                "( "+ VariableTask.COLUMN_TASK_ID+ ","+ VariableTask.COLUMN_VAR_DISCOUNT_ID +"," +
                VariableTask.COLUMN_DISCOUNT_VAL+")"+ " VALUES(?,?,?)";
        String[] values = {String.valueOf(variableTask.getTaskId()), String.valueOf(variableTask.getVarDiscountId()),
                String.valueOf(variableTask.getDiscountVal())};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void update(VariableTask variableTask)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + VariableTask.TABLE_VARIABLE_TASK + " SET "+VariableTask.COLUMN_TASK_ID+" =?,"+VariableTask.COLUMN_VAR_DISCOUNT_ID+" =?,"+
                VariableTask.COLUMN_DISCOUNT_VAL+" =?"+" WHERE " +VariableTask.COLUMN_TASK_ID+ "=" +variableTask.getTaskId() + " OR " +
                VariableTask.COLUMN_VAR_DISCOUNT_ID+ "=" + variableTask.getDiscountVal();
        String[] values = {String.valueOf(variableTask.getTaskId()), String.valueOf(variableTask.getVarDiscountId()),
        String.valueOf(variableTask.getDiscountVal())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
}
