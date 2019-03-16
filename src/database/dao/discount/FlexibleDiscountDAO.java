package database.dao.discount;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IFlexibleDiscount;
import database.domain.discount.DiscountPlan;
import database.domain.discount.FlexibleDiscount;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlexibleDiscountDAO implements IFlexibleDiscount
{

    private ArrayList<FlexibleDiscount> flexibleDiscounts;
    private Connection con;
    private IDBConnectivity connectivity;

    public FlexibleDiscountDAO(){
        flexibleDiscounts = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<FlexibleDiscount> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " +FlexibleDiscount.TABLE_FLEXIBLE_DISCOUNT;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                flexibleDiscounts.add( new FlexibleDiscount(rs.getInt(FlexibleDiscount.INDEX_FLEX_ID),rs.getInt(FlexibleDiscount.INDEX_DISCOUNT_ID)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return flexibleDiscounts;
    }
    @Override
    public void save(FlexibleDiscount flexibleDiscount){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + FlexibleDiscount.TABLE_FLEXIBLE_DISCOUNT+"( "+ DiscountPlan.COLUMN_ID +") VALUES(?)";
        String[] values = {String.valueOf(flexibleDiscount.getDiscountId())};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(FlexibleDiscount flexibleDiscount){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + FlexibleDiscount.TABLE_FLEXIBLE_DISCOUNT + " SET "+ DiscountPlan.COLUMN_ID + "=?" +" WHERE " +FlexibleDiscount.COLUMN_FLEX_DISCOUNT_ID+
                "=" +flexibleDiscount.getFlexId();
        String[] values = {String.valueOf(flexibleDiscount.getDiscountId())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(FlexibleDiscount flexibleDiscount)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + FlexibleDiscount.TABLE_FLEXIBLE_DISCOUNT + " WHERE " + FlexibleDiscount.COLUMN_FLEX_DISCOUNT_ID + "="
                + flexibleDiscount.getFlexId();
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

        String sql = "DELETE FROM " + FlexibleDiscount.TABLE_FLEXIBLE_DISCOUNT + " WHERE " + FlexibleDiscount.COLUMN_FLEX_DISCOUNT_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
