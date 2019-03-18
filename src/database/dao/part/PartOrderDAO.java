package database.dao.part;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IPartOrder;
import database.domain.part.PartOrder;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartOrderDAO implements IPartOrder
{

    private ArrayList<PartOrder> partOrders;
    private PartOrder partOrder;

    private Connection con;
    private IDBConnectivity connectivity;

    public PartOrderDAO(){
        partOrders = new ArrayList<>();
        partOrder = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<PartOrder> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        partOrders.clear();
        String sql = "SELECT * FROM " +PartOrder.TABLE_PART_ORDER;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                partOrders.add( new PartOrder(rs.getString(PartOrder.INDEX_ORDER_NUM),rs.getString(PartOrder.INDEX_DESC),
                        rs.getInt(PartOrder.INDEX_QUANTITY),rs.getFloat(PartOrder.INDEX_PRICE), DBDateHelper.parseDate(rs.getString(PartOrder.INDEX_DATE)),
                        rs.getString(PartOrder.INDEX_COMPANY_NAME),rs.getString(PartOrder.INDEX_ADDRESS_LINE), DBDateHelper.parseDate(rs.getString(PartOrder.INDEX_DAY_DELIVERED)),
                        rs.getString(PartOrder.INDEX_TELEPHONE), rs.getString(PartOrder.INDEX_FAX)));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return partOrders;
    }

    @Override
    public PartOrder getByOrderNum(String orderNum)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + PartOrder.TABLE_PART_ORDER + " WHERE " + PartOrder.COLUMN_ORDER_NUM +
                "='" + orderNum +"'";


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                partOrder = new PartOrder(rs.getString(PartOrder.INDEX_ORDER_NUM),rs.getString(PartOrder.INDEX_DESC),
                        rs.getInt(PartOrder.INDEX_QUANTITY),rs.getFloat(PartOrder.INDEX_PRICE), DBDateHelper.parseDate(rs.getString(PartOrder.INDEX_DATE)),
                        rs.getString(PartOrder.INDEX_COMPANY_NAME),rs.getString(PartOrder.INDEX_ADDRESS_LINE), DBDateHelper.parseDate(rs.getString(PartOrder.INDEX_DAY_DELIVERED)),
                        rs.getString(PartOrder.INDEX_TELEPHONE), rs.getString(PartOrder.INDEX_FAX));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return partOrder;
    }

    @Override
    public void save(PartOrder partOrder){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + PartOrder.TABLE_PART_ORDER+"( "+ PartOrder.COLUMN_ORDER_NUM+","+PartOrder.COLUMN_DESC+","+PartOrder.COLUMN_QUANTITY+","+
                PartOrder.COLUMN_PRICE+","+PartOrder.COLUMN_DATE+","+PartOrder.COLUMN_COMPANY_NAME+","+
                PartOrder.COLUMN_ADDRESS_LINE+","+PartOrder.COLUMN_DAY_DELIVERED+","+PartOrder.COLUMN_TELEPHONE+","+PartOrder.COLUMN_FAX+")"+
                " VALUES(?,?,?,?,?,?,?,?,?,?)";
        String[] values = {partOrder.getOrderNum(),partOrder.getDesc(),String.valueOf(partOrder.getQuantity()),String.valueOf(partOrder.getPrice()),
                String.valueOf(partOrder.getDate()),String.valueOf(partOrder.getCompanyName()),partOrder.getAddressLine(),
                String.valueOf(partOrder.getDayDelivered()),String.valueOf(partOrder.getTelephone()),partOrder.getFax()};
        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(PartOrder partOrder){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + PartOrder.TABLE_PART_ORDER + " SET "+PartOrder.COLUMN_DESC+" =?,"+PartOrder.COLUMN_QUANTITY+" =?,"+
                PartOrder.COLUMN_PRICE+" =?,"+PartOrder.COLUMN_DATE+" =?,"+PartOrder.COLUMN_DATE+" =?,"+PartOrder.COLUMN_COMPANY_NAME+" =?,"+
                PartOrder.COLUMN_ADDRESS_LINE+" =?,"+PartOrder.COLUMN_DAY_DELIVERED+" =?,"+PartOrder.COLUMN_TELEPHONE+" =?,"+PartOrder.COLUMN_FAX+
                " =?"+" WHERE " +PartOrder.COLUMN_ORDER_NUM+ "=" +partOrder.getOrderNum();
        String[] values = {partOrder.getDesc(),String.valueOf(partOrder.getQuantity()),String.valueOf(partOrder.getPrice()),
                String.valueOf(partOrder.getDate()),String.valueOf(partOrder.getCompanyName()),partOrder.getAddressLine(),
                String.valueOf(partOrder.getDayDelivered()),String.valueOf(partOrder.getTelephone()),partOrder.getFax()};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(PartOrder partOrder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + PartOrder.TABLE_PART_ORDER + " WHERE " + PartOrder.COLUMN_ORDER_NUM + "='"
                + partOrder.getOrderNum() +"'";
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(String order_number)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + PartOrder.TABLE_PART_ORDER + " WHERE " + PartOrder.COLUMN_ORDER_NUM + "='"
                + order_number +"'";
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
