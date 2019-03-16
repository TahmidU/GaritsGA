package database.dao.discount;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IVariableDiscount;
import database.domain.account.CustomerAcc;
import database.domain.discount.DiscountPlan;
import database.domain.discount.VariableDiscount;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VariableDiscountDAO implements IVariableDiscount
{

    private ArrayList<VariableDiscount> variableDiscounts;
    private Connection con;
    private IDBConnectivity connectivity;

    public VariableDiscountDAO(){
        variableDiscounts = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<VariableDiscount> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " +VariableDiscount.TABLE_VARIABLE_DISCOUNT;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                variableDiscounts.add( new VariableDiscount(rs.getInt(VariableDiscount.INDEX_VAR_ID),rs.getInt(VariableDiscount.INDEX_DISCOUNT_ID)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return variableDiscounts;
    }
    @Override
    public void save(VariableDiscount variableDiscount){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + VariableDiscount.TABLE_VARIABLE_DISCOUNT+"( "+ DiscountPlan.COLUMN_ID+")"+ " VALUES(?)";
        String[] values = {String.valueOf(variableDiscount.getDiscountId())};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(VariableDiscount variableDiscount){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + VariableDiscount.TABLE_VARIABLE_DISCOUNT + " SET "+DiscountPlan.COLUMN_ID+" =?"+
                " WHERE " +VariableDiscount.COLUMN_VAR_ID+ "=" +variableDiscount.getVarId();
        String[] values = {String.valueOf(variableDiscount.getDiscountId())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(VariableDiscount variableDiscount)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + VariableDiscount.TABLE_VARIABLE_DISCOUNT + " WHERE " + VariableDiscount.COLUMN_VAR_ID + "="
                + variableDiscount.getVarId();

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

        String sql = "DELETE FROM " + VariableDiscount.TABLE_VARIABLE_DISCOUNT + " WHERE " + VariableDiscount.COLUMN_VAR_ID + "="
                + id;

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
