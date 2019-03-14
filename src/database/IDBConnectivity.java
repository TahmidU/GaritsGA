package database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDBConnectivity
{
    ResultSet read(String sql, Connection con);
    boolean write(String sql, Connection con);
    boolean writePrepared(String sql, Connection con, String[] sets);
    boolean writeBatch(Connection con);
    void addToBatch(String sql, Connection con);
    void clearBatch();
    Connection connect(String con);
    boolean closeConnection(Connection con);
    boolean checkExists(String path);
}
