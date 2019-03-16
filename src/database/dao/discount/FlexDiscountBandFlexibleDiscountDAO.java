package database.dao.discount;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IFlexDiscountBandFlexibleDiscount;
import database.domain.discount.FlexDiscountBand;
import database.domain.discount.FlexDiscountBandFlexibleDiscount;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlexDiscountBandFlexibleDiscountDAO implements IFlexDiscountBandFlexibleDiscount
{

    private ArrayList<FlexDiscountBandFlexibleDiscount> flexDiscountBandFlexibleDiscounts;
    private Connection con;
    private IDBConnectivity connectivity;

    public FlexDiscountBandFlexibleDiscountDAO(){
        flexDiscountBandFlexibleDiscounts = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<FlexDiscountBandFlexibleDiscount> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " +FlexDiscountBandFlexibleDiscount.TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                flexDiscountBandFlexibleDiscounts.add( new FlexDiscountBandFlexibleDiscount(rs.getInt(FlexDiscountBandFlexibleDiscount.INDEX_FLEXIBLE_DISCOUNT_ID),
                        rs.getString(FlexDiscountBandFlexibleDiscount.INDEX_FLEX_DISCOUNT_BAND_VALUATION_BAND)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return flexDiscountBandFlexibleDiscounts;
    }
    @Override
    public void save(FlexDiscountBandFlexibleDiscount flexDiscountBandFlexibleDiscount){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + FlexDiscountBandFlexibleDiscount.TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT+
                "( "+ FlexDiscountBandFlexibleDiscount.COLUMN_FLEXIBLE_DISCOUNT_ID+ "," +
                FlexDiscountBandFlexibleDiscount.COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND+")"+ " VALUES(?,?)";
        String[] values = {String.valueOf(flexDiscountBandFlexibleDiscount.getFlexDiscountBandFlexDiscount()),
                flexDiscountBandFlexibleDiscount.getFlexDiscountBandValuationBand()};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(FlexDiscountBandFlexibleDiscount flexDiscountBandFlexibleDiscount)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + FlexDiscountBandFlexibleDiscount.TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT + " WHERE " +
                FlexDiscountBandFlexibleDiscount.COLUMN_FLEXIBLE_DISCOUNT_ID + "="
                + flexDiscountBandFlexibleDiscount.getFlexDiscountBandFlexDiscount() + " OR " + FlexDiscountBandFlexibleDiscount.COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND+
                " ='" + flexDiscountBandFlexibleDiscount.getFlexDiscountBandValuationBand() + "'";

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(String band)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + FlexDiscountBandFlexibleDiscount.TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT + " WHERE " +
                FlexDiscountBandFlexibleDiscount.COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND+
                " ='" + band + "'";

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

        String sql = "DELETE FROM " + FlexDiscountBandFlexibleDiscount.TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT + " WHERE " +
                FlexDiscountBandFlexibleDiscount.COLUMN_FLEXIBLE_DISCOUNT_ID + "="
                + id;

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }


}
