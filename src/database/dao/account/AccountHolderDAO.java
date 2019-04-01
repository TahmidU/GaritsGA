package database.dao.account;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IAccountHolder;
import database.domain.account.AccountHolder;
import util.DBDateHelper;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountHolderDAO implements IAccountHolder
{

    private ArrayList<AccountHolder> accountHolders;
    private AccountHolder accountHolder;

    private Connection con;
    private IDBConnectivity connectivity;

    public AccountHolderDAO()
    {
        accountHolders = new ArrayList<>();
        accountHolder = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<AccountHolder> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        accountHolders.clear();
        String sql = "SELECT * FROM " + AccountHolder.TABLE_ACCOUNT_HOLDER;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {

                accountHolders.add(new AccountHolder(rs.getInt(AccountHolder.INDEX_ID), rs.getString(AccountHolder.INDEX_NI),
                        DBDateHelper.parseDate(rs.getString(AccountHolder.INDEX_DATE_JOINED))));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return accountHolders;
    }

    @Override
    public AccountHolder getById(int id)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + AccountHolder.TABLE_ACCOUNT_HOLDER + " WHERE " + AccountHolder.COLUMN_ID +
                "=" + id;

        accountHolder = null;
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                accountHolder = new AccountHolder(rs.getInt(AccountHolder.INDEX_ID), rs.getString(AccountHolder.INDEX_NI),
                        DBDateHelper.parseDate(rs.getString(AccountHolder.INDEX_DATE_JOINED)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return accountHolder;
    }

    @Override
    public AccountHolder getByNI(String nI)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + AccountHolder.TABLE_ACCOUNT_HOLDER + " WHERE " + AccountHolder.COLUMN_NI +
                "='" + nI +"'";

        accountHolder = null;
        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                accountHolder = new AccountHolder(rs.getInt(AccountHolder.INDEX_ID), rs.getString(AccountHolder.INDEX_NI),
                        DBDateHelper.parseDate(rs.getString(AccountHolder.INDEX_DATE_JOINED)));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);
        return accountHolder;
    }

    @Override
    public void save(AccountHolder accountHolder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + AccountHolder.TABLE_ACCOUNT_HOLDER + "( " + AccountHolder.COLUMN_NI + "," + AccountHolder.COLUMN_DATE_JOINED + ")" + " VALUES(?,?)";
        String[] values = {accountHolder.getNationalInsurance(), String.valueOf(accountHolder.getDateJoined())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(AccountHolder accountHolder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + AccountHolder.TABLE_ACCOUNT_HOLDER + " SET " + AccountHolder.COLUMN_NI + " =?," +
                AccountHolder.COLUMN_DATE_JOINED + " =?" + " WHERE " + AccountHolder.COLUMN_ID +
                " =" + accountHolder.getId();

        String[] values = {Integer.toString(accountHolder.getId()), accountHolder.getNationalInsurance(),
                String.valueOf(accountHolder.getDateJoined())};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(AccountHolder accountHolder)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + AccountHolder.TABLE_ACCOUNT_HOLDER + " WHERE " + AccountHolder.COLUMN_ID + "="
                + accountHolder.getId();
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

        String sql = "DELETE FROM " + AccountHolder.TABLE_ACCOUNT_HOLDER + " WHERE " + AccountHolder.COLUMN_ID + "="
                + id;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
