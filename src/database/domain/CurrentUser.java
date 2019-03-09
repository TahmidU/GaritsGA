package database.domain;

//  Related to the login_detail table within the database.

public class CurrentUser
{
    //  Table name and name of all columns.
    public static final String TABLE_LOGIN_DETAIL = "login_detail";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_STAFF_ID = "staff_id";
    public static final String COLUMN_PASSWORD = "password";


    //  Columns indexes.
    public static final int INDEX_USER_NAME = 1;
    public static final int INDEX_STAFF_ID = 2;
    public static final int INDEX_PASSWORD = 3;


    //  Create Table SQL Statement.
    public static final String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_LOGIN_DETAIL + " (" + COLUMN_USER_NAME + " varchar(15) NOT NULL," +
            " " + COLUMN_STAFF_ID + " integer(10) NOT NULL, " + COLUMN_PASSWORD + " varchar(15) NOT NULL," +
            " PRIMARY KEY (" + COLUMN_USER_NAME + "), FOREIGN KEY(" + COLUMN_STAFF_ID + ") REFERENCES " + Staff.TABLE_STAFF + "(" + Staff.COLUMN_ID + "));";

    //  Properties
    private String userName;
    private String staffID;
    private String password;

    //--------Getters and Setters--------
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
