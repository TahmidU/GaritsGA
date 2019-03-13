package database;

import com.sun.istack.internal.Nullable;
import util.Log;

import java.io.File;
import java.sql.*;

public class DBConnectivity implements IDBConnectivity
{
    private Statement batchStm;

    /**
     *
     * @param sql sql String to execute.
     * @param con connection to database.
     * @return ResultSet
     */
    @Override
    public ResultSet read(String sql, Connection con)
    {
        Statement mState = null;
        try {
            mState = con.createStatement();
            return mState.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.write("DBConnectivity: Failed to read database.");
        return null;
    }

    @Override
    public boolean write(String sql, Connection con)
    {
        try {
            Statement mState = con.createStatement();
            mState.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.write("DBConnectivity: Failed to write to database.");
        return false;
    }

    @Override
    public boolean writePrepared(String sql, Connection con, String[] sets)
    {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
        if(sets.length > 0)
        {
            for(int i = 0; i < sets.length; i++)
            {
                ps.setString(i+1, sets[i]);
            }
            ps.execute();
            Log.write("DBConnectivity: Statement was successfully executed.");
            return true;
        }else
            {
                Log.write("DBConnectivity: Sets is empty. Please use write instead for simple SQL writes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean writeBatch(Connection con)
    {
        if(batchStm != null)
        {
            try {
                batchStm.executeBatch();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Log.write("DBConnectivity: sql error in batch.");
        return false;
    }

    @Override
    public void addToBatch(String sql, @Nullable Connection con)
    {
        if(batchStm == null)
        {
            try {
                batchStm = con.createStatement();
            } catch (SQLException e) {
                Log.write("DBConnectivity: Failed to connect (addToBatch).");
                e.printStackTrace();
            }
        }


        try {
            batchStm.addBatch(sql);
        } catch (SQLException e) {
            Log.write("DBConnectivity: Failed to add to batch.");
            e.printStackTrace();
        }
    }

    @Override
    public void clearBatch()
    {
        if(batchStm != null) {
            try {
                batchStm.clearBatch();
            } catch (SQLException e) {
                Log.write("DBConnectivity: Failed to clear batch.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection connect(String con)
    {
        try {
            return DriverManager.getConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.write("DBConnectivity: Connection failed.");
        return null;
    }

    @Override
    public boolean closeConnection(Connection con)
    {
        try {
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.write("DBConnectivity: Failed to close connection with database.");
        return false;
    }

    @Override
    public boolean checkExists(String path)
    {
        Log.write("Checking if database exists...");
        File file = new File(path);

        return file.exists();
    }
}
