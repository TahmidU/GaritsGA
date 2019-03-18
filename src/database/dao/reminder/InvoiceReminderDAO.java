package database.dao.reminder;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IInvoiceReminder;
import database.domain.reminder.InvoiceReminder;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceReminderDAO implements IInvoiceReminder
{
    private ArrayList<InvoiceReminder> invoiceReminders;
    private InvoiceReminder invoiceReminder;

    private Connection con;
    private IDBConnectivity connectivity;

    public InvoiceReminderDAO(){
        invoiceReminders = new ArrayList<>();
        invoiceReminder = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<InvoiceReminder> getAll(){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        invoiceReminders.clear();
        String sql = "SELECT * FROM " +InvoiceReminder.TABLE_INVOICE_REMINDER;
        ResultSet rs = connectivity.read(sql, con);
        try{
            while(rs.next()){
                invoiceReminders.add( new InvoiceReminder(rs.getInt(InvoiceReminder.INDEX_ID),rs.getInt(InvoiceReminder.INDEX_INVOICE_ID),
                        rs.getInt(InvoiceReminder.INDEX_ACC_HOLDER_ID),rs.getString(InvoiceReminder.INDEX_TYPE),
                        DBDateHelper.parseDate(rs.getString(InvoiceReminder.INDEX_DATE_CREATED))));
            }
            Log.write("DAO: Query successful.");
        }catch(SQLException e){
            Log.write("DAO: Faild to retrieve date from database.");
            e.printStackTrace();
        }
        connectivity.closeConnection(con);
        return invoiceReminders;
    }

    @Override
    public InvoiceReminder getById(int id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + InvoiceReminder.TABLE_INVOICE_REMINDER + " WHERE " + InvoiceReminder.COLUMN_ID +
                "=" + id;


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                invoiceReminder = new InvoiceReminder(rs.getInt(InvoiceReminder.INDEX_ID),rs.getInt(InvoiceReminder.INDEX_INVOICE_ID),
                        rs.getInt(InvoiceReminder.INDEX_ACC_HOLDER_ID),rs.getString(InvoiceReminder.INDEX_TYPE),
                        DBDateHelper.parseDate(rs.getString(InvoiceReminder.INDEX_DATE_CREATED)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return invoiceReminder;
    }

    @Override
    public ArrayList<InvoiceReminder> getByInvoiceId(int invoiceId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + InvoiceReminder.TABLE_INVOICE_REMINDER + " WHERE " + InvoiceReminder.COLUMN_INVOICE_ID +
                "=" + invoiceId;

        invoiceReminders.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                invoiceReminders.add( new InvoiceReminder(rs.getInt(InvoiceReminder.INDEX_ID),rs.getInt(InvoiceReminder.INDEX_INVOICE_ID),
                        rs.getInt(InvoiceReminder.INDEX_ACC_HOLDER_ID),rs.getString(InvoiceReminder.INDEX_TYPE),
                        DBDateHelper.parseDate(rs.getString(InvoiceReminder.INDEX_DATE_CREATED))));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return invoiceReminders;
    }

    @Override
    public ArrayList<InvoiceReminder> getByAccountHolderId(int accountHolderId)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + InvoiceReminder.TABLE_INVOICE_REMINDER + " WHERE " + InvoiceReminder.COLUMN_ACC_HOLDER_ID +
                "=" + accountHolderId;

        invoiceReminders.clear();
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                invoiceReminders.add( new InvoiceReminder(rs.getInt(InvoiceReminder.INDEX_ID),rs.getInt(InvoiceReminder.INDEX_INVOICE_ID),
                        rs.getInt(InvoiceReminder.INDEX_ACC_HOLDER_ID),rs.getString(InvoiceReminder.INDEX_TYPE),
                        DBDateHelper.parseDate(rs.getString(InvoiceReminder.INDEX_DATE_CREATED))));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return invoiceReminders;
    }

    @Override
    public void save(InvoiceReminder invoiceReminder){

        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + InvoiceReminder.TABLE_INVOICE_REMINDER+"( "+InvoiceReminder.COLUMN_INVOICE_ID+","+
                InvoiceReminder.COLUMN_ACC_HOLDER_ID+","+InvoiceReminder.COLUMN_TYPE+","+InvoiceReminder.COLUMN_DATE_CREATED+")"+ " VALUES(?,?,?,?)";

        String[] values = {String.valueOf(invoiceReminder.getInvoiceId()),String.valueOf(invoiceReminder.getAccHolderId()),
                String.valueOf(invoiceReminder.getType()),String.valueOf(invoiceReminder.getDateCreated())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }
    @Override
    public void update(InvoiceReminder invoiceReminder){
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try{
            con.setAutoCommit(false);
        }catch(SQLException e){
            Log.write( "DAO: Failed to set auto commit to false ." );
            e.printStackTrace();
        }

        String sql = "UPDATE " + InvoiceReminder.TABLE_INVOICE_REMINDER + " SET "+InvoiceReminder.COLUMN_INVOICE_ID+" =?,"+
                InvoiceReminder.COLUMN_ACC_HOLDER_ID+" =?,"+InvoiceReminder.COLUMN_TYPE+" =?,"+
                InvoiceReminder.COLUMN_DATE_CREATED+" =?"+" WHERE " +InvoiceReminder.COLUMN_ID+ "=" +invoiceReminder.getId();

        String[] values = {String.valueOf(invoiceReminder.getInvoiceId()),String.valueOf(invoiceReminder.getAccHolderId()),
                String.valueOf(invoiceReminder.getType()),String.valueOf(invoiceReminder.getDateCreated())};

        connectivity.writePrepared(sql, con, values);
        connectivity.closeConnection(con);
    }

    @Override
    public void delete(InvoiceReminder invoiceReminder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + InvoiceReminder.TABLE_INVOICE_REMINDER + " WHERE " + InvoiceReminder.COLUMN_ID + "="
                + invoiceReminder.getId();
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

        String sql = "DELETE FROM " + InvoiceReminder.TABLE_INVOICE_REMINDER + " WHERE " + InvoiceReminder.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
