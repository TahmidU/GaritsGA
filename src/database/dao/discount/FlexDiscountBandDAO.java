package database.dao.discount;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IFlexDiscountBand;
import database.domain.discount.FlexDiscountBand;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlexDiscountBandDAO implements IFlexDiscountBand
{

    private ArrayList<FlexDiscountBand> flexDiscountBands;
    private FlexDiscountBand flexDiscountBand;

    private Connection con;
    private IDBConnectivity connectivity;

    public FlexDiscountBandDAO(){
        flexDiscountBands = new ArrayList<>();
        flexDiscountBand = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<FlexDiscountBand> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        flexDiscountBands.clear();
        String sql = "SELECT * FROM " +FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                flexDiscountBands.add( new FlexDiscountBand(rs.getString(FlexDiscountBand.INDEX_BAND),rs.getFloat(FlexDiscountBand.INDEX_BAND_DISCOUNT)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return flexDiscountBands;
    }

    @Override
    public FlexDiscountBand getByValuationBand(String band)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND + " WHERE " + FlexDiscountBand.COLUMN_BAND +
                "='" + band + "'";


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                flexDiscountBand = new FlexDiscountBand(rs.getString(FlexDiscountBand.INDEX_BAND),rs.getFloat(FlexDiscountBand.INDEX_BAND_DISCOUNT));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return flexDiscountBand;
    }

    @Override
    public void save(FlexDiscountBand flexDiscountBand){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND+"( "+FlexDiscountBand.COLUMN_BAND+","+
                FlexDiscountBand.COLUMN_BAND_DISCOUNT+")"+ " VALUES(?,?)";
        String[] values = {flexDiscountBand.getFlexBandId(),String.valueOf(flexDiscountBand.getBandDiscount())};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(FlexDiscountBand flexDiscountBand){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND + " SET "+
                FlexDiscountBand.COLUMN_BAND_DISCOUNT+" =?"+" WHERE " +FlexDiscountBand.COLUMN_BAND+ "='" +flexDiscountBand.getFlexBandId()+"'";
        String[] values = {String.valueOf(flexDiscountBand.getBandDiscount())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(FlexDiscountBand flexDiscountBand)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND + " WHERE " + FlexDiscountBand.COLUMN_BAND + "="
                + "'" + flexDiscountBand.getFlexBandId() + "'";
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

        String sql = "DELETE FROM " + FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND + " WHERE " + FlexDiscountBand.COLUMN_BAND + "="
                + "'" + band + "'";
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
