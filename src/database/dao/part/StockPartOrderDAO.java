package database.dao.part;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IStockPartOrder;
import database.domain.part.StockPart;
import database.domain.part.StockPartOrder;
import util.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockPartOrderDAO implements IStockPartOrder
{

    private ArrayList<StockPartOrder> stockPartOrders;

    private Connection con;
    private IDBConnectivity connectivity;

    public StockPartOrderDAO(){
        stockPartOrders = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<StockPartOrder> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        stockPartOrders.clear();
        String sql = "SELECT * FROM " +StockPartOrder.TABLE_STOCK_PART_ORDER;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                stockPartOrders.add( new StockPartOrder(rs.getString(StockPartOrder.INDEX_PART_ORDER_NUM),
                        rs.getInt(StockPartOrder.INDEX_STOCK_PART_ID)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return stockPartOrders;
    }

    @Override
    public ArrayList<StockPartOrder> getByPartOrderNum(String orderNum)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + StockPartOrder.TABLE_STOCK_PART_ORDER + " WHERE " + StockPartOrder.COLUMN_PART_ORDER_NUM +
                "='" + orderNum + "'";

        stockPartOrders.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                stockPartOrders.add( new StockPartOrder(rs.getString(StockPartOrder.INDEX_PART_ORDER_NUM),
                        rs.getInt(StockPartOrder.INDEX_STOCK_PART_ID)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return stockPartOrders;
    }

    @Override
    public ArrayList<StockPartOrder> getByStockPartId(int stockPartId)
    {

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        stockPartOrders.clear();
        String sql = "SELECT * FROM " + StockPartOrder.TABLE_STOCK_PART_ORDER + " WHERE " + StockPartOrder.COLUMN_PART_ORDER_NUM +
                "=" + stockPartId;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                stockPartOrders.add( new StockPartOrder(rs.getString(StockPartOrder.INDEX_PART_ORDER_NUM),
                        rs.getInt(StockPartOrder.INDEX_STOCK_PART_ID)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return stockPartOrders;
    }

    @Override
    public void save(StockPartOrder stockPartOrder){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + StockPartOrder.TABLE_STOCK_PART_ORDER+
                "( "+ StockPartOrder.COLUMN_PART_ORDER_NUM+ "," +
                StockPartOrder.COLUMN_STOCK_PART_ID+")"+ " VALUES(?,?)";
        String[] values = {stockPartOrder.getPartOrderNum(),String.valueOf(stockPartOrder.getStockPartId())};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
}
