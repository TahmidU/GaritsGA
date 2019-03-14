package database.dao.account;

import database.DBConnectivity;
import database.IDBConnectivity;
import database.dao.DBHelper;
import database.dao.contracts.ILoginDetail;
import database.domain.account.LoginDetail;
import util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDetailDAO implements ILoginDetail
{

    private ArrayList<LoginDetail> loginDetails;

    private Connection con;
    private IDBConnectivity connectivity;

    public LoginDetailDAO()
    {
        loginDetails = new ArrayList<>();
        connectivity = new DBConnectivity();
    }

    @Override
    public ArrayList<LoginDetail> getAll()
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);

        String sql = "SELECT * FROM " + LoginDetail.TABLE_LOGIN_DETAIL;
        ResultSet rs = connectivity.read(sql,con);
        try {
            while (rs.next()) {
                loginDetails.add(new LoginDetail(rs.getString(LoginDetail.INDEX_USER_NAME), rs.getInt(LoginDetail.INDEX_STAFF_ID),
                        rs.getString(LoginDetail.INDEX_PASSWORD)));
            }
            Log.write("DAO: Query successful.");
        }catch (SQLException e)
        {
            Log.write("DAO: Failed to retrieve data from database.");
            e.printStackTrace();
        }

        connectivity.closeConnection(con);

        return loginDetails;
    }

    @Override
    public void save(LoginDetail loginDetail)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);

        String sql = "INSERT INTO " + LoginDetail.TABLE_LOGIN_DETAIL + "( " + LoginDetail.COLUMN_USER_NAME + ","
                + LoginDetail.COLUMN_STAFF_ID + "," + LoginDetail.COLUMN_PASSWORD + ")" + " VALUES(?,?,?)";
        String[] values = {loginDetail.getUserName(), Integer.toString(loginDetail.getStaffID()), loginDetail.getPassword()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void update(LoginDetail loginDetail)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);

        String sql = "UPDATE " + LoginDetail.TABLE_LOGIN_DETAIL + " SET " + LoginDetail.COLUMN_USER_NAME + " =?," + LoginDetail.COLUMN_STAFF_ID + " =?," +
                LoginDetail.COLUMN_PASSWORD + " =?" + " WHERE " + LoginDetail.COLUMN_USER_NAME +
                " =" + loginDetail.getUserName();

        String[] values = {loginDetail.getUserName(), Integer.toString(loginDetail.getStaffID()), loginDetail.getPassword()};

        connectivity.writePrepared(sql, con, values);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(LoginDetail loginDetail)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);

        String sql = "DELETE FROM " + LoginDetail.TABLE_LOGIN_DETAIL + " WHERE " + LoginDetail.COLUMN_USER_NAME + "="
                + loginDetail.getUserName();
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }

    @Override
    public void delete(String username)
    {
        con = connectivity.connect(DBHelper.DB_DRIVER);

        String sql = "DELETE FROM " + LoginDetail.TABLE_LOGIN_DETAIL + " WHERE " + LoginDetail.COLUMN_USER_NAME + "="
                + username;
        connectivity.write(sql, con);

        connectivity.closeConnection(con);
    }
}
