package database.dao.discount;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IFixedDiscount;
import database.domain.discount.DiscountPlan;
import database.domain.discount.FixedDiscount;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FixedDiscountDAO implements IFixedDiscount
{
    private ArrayList<FixedDiscount> fixedDiscounts;
    private FixedDiscount fixedDiscount;

    private Connection con;
    private IDBConnectivity connectivity;

    public FixedDiscountDAO(){
        fixedDiscounts = new ArrayList<>();
        fixedDiscount = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<FixedDiscount> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        fixedDiscounts.clear();
        String sql = "SELECT * FROM " +FixedDiscount.TABLE_FIXED_DISCOUNT;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                fixedDiscounts.add( new FixedDiscount(rs.getInt(FixedDiscount.INDEX_FIX_DISCOUNT_ID), rs.getInt(FixedDiscount.INDEX_DISCOUNT_PLAN_ID),
                        rs.getFloat(FixedDiscount.INDEX_PERCENTAGE)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return fixedDiscounts;
    }

    @Override
    public FixedDiscount getById(int id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + FixedDiscount.TABLE_FIXED_DISCOUNT + " WHERE " + FixedDiscount.COLUMN_FIX_DISCOUNT_ID +
                "=" + id;

        fixedDiscount = null;
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                fixedDiscount = new FixedDiscount(rs.getInt(FixedDiscount.INDEX_FIX_DISCOUNT_ID), rs.getInt(FixedDiscount.INDEX_DISCOUNT_PLAN_ID),
                        rs.getFloat(FixedDiscount.INDEX_PERCENTAGE));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return fixedDiscount;
    }

    @Override
    public FixedDiscount getByDiscountId(int discountId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + FixedDiscount.TABLE_FIXED_DISCOUNT + " WHERE " + DiscountPlan.COLUMN_ID +
                "=" + discountId;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                fixedDiscount = new FixedDiscount(rs.getInt(FixedDiscount.INDEX_FIX_DISCOUNT_ID), rs.getInt(FixedDiscount.INDEX_DISCOUNT_PLAN_ID),
                        rs.getFloat(FixedDiscount.INDEX_PERCENTAGE));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return fixedDiscount;
    }

    @Override
    public void save(FixedDiscount fixedDiscount){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + FixedDiscount.TABLE_FIXED_DISCOUNT+"("+ DiscountPlan.COLUMN_ID + "," +
                FixedDiscount.COLUMN_PERCENTAGE+")"+ " VALUES(?,?);";
        String[] values = {String.valueOf(fixedDiscount.getDiscountPlanId()),String.valueOf(fixedDiscount.getPercentage())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void update(FixedDiscount fixedDiscount)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + FixedDiscount.TABLE_FIXED_DISCOUNT + " SET "+DiscountPlan.COLUMN_ID+" =?,"+FixedDiscount.COLUMN_PERCENTAGE+" =?"+
                " WHERE " +FixedDiscount.COLUMN_FIX_DISCOUNT_ID+ "=" +fixedDiscount.getFixedDiscountId();
        String[] values = {String.valueOf(fixedDiscount.getDiscountPlanId()),String.valueOf(fixedDiscount.getPercentage())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(FixedDiscount fixedDiscount)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + FixedDiscount.TABLE_FIXED_DISCOUNT + " WHERE " + FixedDiscount.COLUMN_FIX_DISCOUNT_ID + "="
                + fixedDiscount.getFixedDiscountId();
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

        String sql = "DELETE FROM " + FixedDiscount.TABLE_FIXED_DISCOUNT + " WHERE " + FixedDiscount.COLUMN_FIX_DISCOUNT_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
