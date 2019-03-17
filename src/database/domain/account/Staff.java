package database.domain.account;

import database.dao.DBHelper;

public class Staff
{
    //  Table name and name of all columns.
    public static final String TABLE_STAFF = "staff";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUM = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_TYPE = "type";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_FIRST_NAME = 2;
    public static final int INDEX_LAST_NAME = 3;
    public static final int INDEX_PHONE_NUM = 4;
    public static final int INDEX_EMAIL = 5;
    public static final int INDEX_TYPE = 6;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_STAFF = "CREATE TABLE " + TABLE_STAFF + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY ," +
            " " + COLUMN_FIRST_NAME + " varchar(30) NOT NULL, " + COLUMN_LAST_NAME + " varchar(30) NOT NULL, " + COLUMN_PHONE_NUM + " varchar(11), " +
            "" + COLUMN_EMAIL + " varchar(255) NOT NULL, " + COLUMN_TYPE + " varchar(15) NOT NULL);";

    //  Properties
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String type;

    public Staff(){}

    public Staff(int id, String firstName, String lastName, String phoneNum, String email, String type)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.type = type;
    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
