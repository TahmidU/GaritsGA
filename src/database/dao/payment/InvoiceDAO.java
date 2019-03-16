package database.dao.payment;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IInvoice;
import database.domain.payment.Invoice;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDAO implements IInvoice {
    private ArrayList<Invoice> invoices;

    private Connection con;
    private IDBConnectivity connectivity;

    public InvoiceDAO() {
        invoices = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<Invoice> getAll() {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + Invoice.TABLE_INVOICE;
        ResultSet rs = connectivity.read(sql, con);
        try {
            while (rs.next()) {
                invoices.add(new Invoice(rs.getInt(Invoice.INDEX_ID), rs.getString(Invoice.INDEX_NI), DBDateHelper.parseDate(rs.getString(Invoice.INDEX_DATE_CREATED)),
                        rs.getFloat(Invoice.INDEX_TOTAL), rs.getInt(Invoice.INDEX_JOB_NUM)));
            }
            Log.write("DAO: Query successful.");
        } catch (SQLException e) {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return invoices;
    }

    @Override
    public void save(Invoice invoice) {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + Invoice.TABLE_INVOICE + "( " + Invoice.COLUMN_NI + ","
                + Invoice.COLUMN_DATE_CREATED + "," + Invoice.COLUMN_TOTAL + "," + Invoice.COLUMN_JOB_NUM + ")" + " VALUES(?,?,?,?)";
        String[] values = {invoice.getNationalInsurance(), String.valueOf(invoice.getDateCreated()), String.valueOf(invoice.getTotalAmount()),
                String.valueOf(invoice.getJobNum())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(Invoice invoice) {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + Invoice.TABLE_INVOICE + " SET " + Invoice.COLUMN_NI + " =?," + Invoice.COLUMN_DATE_CREATED + " =?," +
                Invoice.COLUMN_TOTAL + " =?," + Invoice.COLUMN_JOB_NUM + " =?" + " WHERE " + Invoice.COLUMN_ID +
                " =" + invoice.getId();

        String[] values = {invoice.getNationalInsurance(), String.valueOf(invoice.getDateCreated()), String.valueOf(invoice.getTotalAmount()),
                String.valueOf(invoice.getJobNum())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(Invoice invoice) {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Invoice.TABLE_INVOICE + " WHERE " + Invoice.COLUMN_ID + "="
                + invoice.getId();
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(int id) {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + Invoice.TABLE_INVOICE + " WHERE " + Invoice.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
