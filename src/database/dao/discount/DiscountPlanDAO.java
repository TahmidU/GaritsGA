package database.dao.discount;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IDiscountPlan;
import database.domain.discount.DiscountPlan;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscountPlanDAO implements IDiscountPlan
{

    private ArrayList<DiscountPlan> discountPlans;
    private DiscountPlan discountPlan;

    private Connection con;
    private IDBConnectivity connectivity;

    public DiscountPlanDAO(){
        discountPlans = new ArrayList<>();
        discountPlan = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<DiscountPlan> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        discountPlans.clear();
        String sql = "SELECT * FROM " +DiscountPlan.TABLE_DISCOUNT_PLAN;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                discountPlans.add( new DiscountPlan(rs.getInt(DiscountPlan.INDEX_ID)
                        ,rs.getString(DiscountPlan.INDEX_TYPE),rs.getString(DiscountPlan.INDEX_NI)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return discountPlans;
    }

    @Override
    public DiscountPlan getByDiscountId(int discountId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + DiscountPlan.TABLE_DISCOUNT_PLAN + " WHERE " + DiscountPlan.COLUMN_ID +
                "=" + discountId;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                discountPlan = new DiscountPlan(rs.getInt(DiscountPlan.INDEX_ID)
                        ,rs.getString(DiscountPlan.INDEX_TYPE),rs.getString(DiscountPlan.INDEX_NI));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return discountPlan;
    }

    @Override
    public DiscountPlan getByNI(String nI)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + DiscountPlan.TABLE_DISCOUNT_PLAN + " WHERE " + DiscountPlan.COLUMN_NI +
                "='" + nI + "'";


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                discountPlan = new DiscountPlan(rs.getInt(DiscountPlan.INDEX_ID)
                        ,rs.getString(DiscountPlan.INDEX_TYPE),rs.getString(DiscountPlan.INDEX_NI));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return discountPlan;
    }

    @Override
    public void save(DiscountPlan discountPlan){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + DiscountPlan.TABLE_DISCOUNT_PLAN+
                "( "+DiscountPlan.COLUMN_TYPE+","+DiscountPlan.COLUMN_NI+")"+ " VALUES(?,?)";
        String[] values = {discountPlan.getType(),discountPlan.getNationalInsurance()};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(DiscountPlan discountPlan){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + DiscountPlan.TABLE_DISCOUNT_PLAN + " SET "+DiscountPlan.COLUMN_TYPE+" =?,"+DiscountPlan.COLUMN_NI+" =?"
                +" WHERE " +DiscountPlan.COLUMN_ID+ "=" +discountPlan.getId();
        String[] values = {discountPlan.getType(),discountPlan.getNationalInsurance()};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(DiscountPlan discountPlan)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + DiscountPlan.TABLE_DISCOUNT_PLAN + " WHERE " + DiscountPlan.COLUMN_ID + "="
                + discountPlan.getId();
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

        String sql = "DELETE FROM " + DiscountPlan.TABLE_DISCOUNT_PLAN + " WHERE " + DiscountPlan.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
