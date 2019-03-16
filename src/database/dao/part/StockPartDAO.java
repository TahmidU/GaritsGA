package database.dao.part;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IStockPart;
import database.domain.part.StockPart;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockPartDAO implements IStockPart
{

    private ArrayList<StockPart> stockParts;
    private Connection con;
    private IDBConnectivity connectivity;

    public StockPartDAO(){
        stockParts = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<StockPart> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " +StockPart.TABLE_STOCK_PART;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                stockParts.add( new StockPart(rs.getInt(StockPart.INDEX_PART_ID),rs.getString(StockPart.INDEX_PART_NAME),
                        rs.getFloat(StockPart.INDEX_PRICE),rs.getInt(StockPart.INDEX_LOW_THRES),
                        rs.getString(StockPart.INDEX_MANUFACTURER),rs.getString(StockPart.INDEX_VEHICLE_TYPE),
                        rs.getString(StockPart.INDEX_START_YEAR),rs.getString(StockPart.INDEX_END_YEAR)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return stockParts;
    }
    @Override
    public void save(StockPart stockPart){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + StockPart.TABLE_STOCK_PART+"( "+StockPart.COLUMN_PART_NAME+","+StockPart.COLUMN_PRICE+","+
                StockPart.COLUMN_LOW_THRES+","+StockPart.COLUMN_MANUFACTURER+","+StockPart.COLUMN_VEHICLE_TYPE+","+
                StockPart.COLUMN_START_YEAR+","+StockPart.COLUMN_END_YEAR+")"+ " VALUES(?,?,?,?,?,?,?)";
        String[] values = {String.valueOf(stockPart.getPartName()),String.valueOf(stockPart.getPrice()),String.valueOf(stockPart.getThreshold()),
                String.valueOf(stockPart.getManufacturer()),stockPart.getVehicleType(),stockPart.getStartYr(),stockPart.getEndYr()};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(StockPart stockPart){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + StockPart.TABLE_STOCK_PART + " SET "+StockPart.COLUMN_PART_NAME+" =?,"+StockPart.COLUMN_PRICE+
                " =?,"+StockPart.COLUMN_LOW_THRES+" =?,"+StockPart.COLUMN_MANUFACTURER+" =?,"+StockPart.COLUMN_VEHICLE_TYPE+" =?,"+
                StockPart.COLUMN_START_YEAR+" =?,"+StockPart.COLUMN_END_YEAR+" =?"+" WHERE " +StockPart.COLUMN_PART_ID+ "=" +stockPart.getPartId();
        String[] values = {String.valueOf(stockPart.getPartName()),String.valueOf(stockPart.getPrice()),String.valueOf(stockPart.getThreshold()),
                String.valueOf(stockPart.getManufacturer()),stockPart.getVehicleType(),stockPart.getStartYr(),stockPart.getEndYr()};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(StockPart stockPart)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + StockPart.TABLE_STOCK_PART + " WHERE " + StockPart.COLUMN_PART_ID + "="
                + stockPart.getPartId();
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(int part_id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + StockPart.TABLE_STOCK_PART + " WHERE " + StockPart.COLUMN_PART_ID + "="
                + part_id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
