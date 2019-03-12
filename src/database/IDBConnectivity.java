package database;

import com.sun.istack.internal.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDBConnectivity
{
    ResultSet read(String sql, Connection con);
    boolean write(String sql, Connection con);
    boolean writeBatch(Connection con);
    void addToBatch(String sql, @Nullable Connection con);
    void clearBatch();
    Connection connect(String con);
    boolean closeConnection(Connection con);
    boolean checkExists(String path);
}
