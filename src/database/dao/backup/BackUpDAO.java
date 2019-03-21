package database.dao.backup;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.IBackUp;
import database.domain.account.Staff;
import database.domain.backup.BackUp;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BackUpDAO implements IBackUp
{

    private ArrayList<BackUp> backUps;
    private BackUp backUp;

    private Connection con;
    private IDBConnectivity connectivity;

    public BackUpDAO()
    {
        backUps = new ArrayList<>();
        backUp = null;
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<BackUp> getAll()
    {
        con = connectivity.connect(DBHelper.DB_BACKUP_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        backUps.clear();
        String sql = "SELECT * FROM " + BackUp.TABLE_BACKUP;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                backUps.add(new BackUp(rs.getString(BackUp.INDEX_DIR), rs.getString(BackUp.INDEX_FILENAME),
                        rs.getString(BackUp.INDEX_DATE_CREATED), rs.getString(BackUp.INDEX_TIME_CREATED)));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return backUps;
    }

    @Override
    public BackUp getById(String dir)
    {
        con = connectivity.connect(DBHelper.DB_BACKUP_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "SELECT * FROM " + BackUp.TABLE_BACKUP + " WHERE " + Staff.COLUMN_ID +
                "='" + dir + "'";


        try{
            ResultSet rs = connectivity.read(sql, con);
            while(rs.next())
            {
                backUp = new BackUp(rs.getString(BackUp.INDEX_DIR), rs.getString(BackUp.INDEX_FILENAME),
                        rs.getString(BackUp.INDEX_DATE_CREATED), rs.getString(BackUp.INDEX_TIME_CREATED));
            }
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return backUp;
    }

    @Override
    public void save(BackUp backUp)
    {
        con = connectivity.connect(DBHelper.DB_BACKUP_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "INSERT INTO " + BackUp.TABLE_BACKUP + "( "+ BackUp.COLUMN_DIR + ", " + BackUp.COLUMN_FILENAME + ", " +
                BackUp.COLUMN_DATE_CREATED + ", " + BackUp.COLUMN_TIME_CREATED + ")" + " VALUES(?,?,?,?)";

        String[] values = {backUp.getDir(), backUp.getFileName(), backUp.getDateCreated(), backUp.getTimeCreated()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(BackUp backUp)
    {
        con = connectivity.connect(DBHelper.DB_BACKUP_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "UPDATE " + BackUp.TABLE_BACKUP + " SET " + BackUp.COLUMN_DIR + " =?, " + BackUp.COLUMN_FILENAME + " =?, "
                + BackUp.COLUMN_DATE_CREATED + " =?," + BackUp.COLUMN_TIME_CREATED + " =?" + " WHERE " + BackUp.COLUMN_DIR +
                " ='" + backUp.getDir() + "'";

        String[] values = {backUp.getDir(), backUp.getFileName(), backUp.getDateCreated(), backUp.getTimeCreated()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(BackUp backUp)
    {
        con = connectivity.connect(DBHelper.DB_BACKUP_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + BackUp.TABLE_BACKUP + " WHERE " + BackUp.COLUMN_DIR + "='" + backUp.getDir() +"'";

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(String dir)
    {
        con = connectivity.connect(DBHelper.DB_BACKUP_DRIVER);
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Log.write("DAO: Failed to set auto commit to false.");
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + BackUp.TABLE_BACKUP + " WHERE " + BackUp.COLUMN_DIR + "='" + dir +"'";

        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
