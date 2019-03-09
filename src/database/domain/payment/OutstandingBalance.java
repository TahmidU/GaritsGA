package database.domain.payment;

import database.domain.account.AccountHolder;
import database.domain.account.Staff;

import java.sql.Date;

public class OutstandingBalance
{
    //  Table name and name of all columns.
    public static final String TABLE_OUTSTANDING_BALANCE = "outstanding_balance";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STAFF_ID = "staff_id";
    public static final String COLUMN_ACCOUNT_HOLDER_ID = "account_holder_id";
    public static final String COLUMN_DATE_AUTHORISED = "date_authorised";


    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_STAFF_ID = 2;
    public static final int INDEX_ACCOUNT_HOLDER_ID = 3;
    public static final int INDEX_DATE_AUTHORISED = 4;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_OUTSTANDING_BALANCE = "CREATE TABLE " + TABLE_OUTSTANDING_BALANCE + " " +
            "(" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            ""+ COLUMN_STAFF_ID + " integer(10) NOT NULL, " + COLUMN_ACCOUNT_HOLDER_ID + " integer(10) NOT NULL, " +
            "" + COLUMN_DATE_AUTHORISED + " date NOT NULL, FOREIGN KEY(" + COLUMN_STAFF_ID + ") REFERENCES " + Staff.TABLE_STAFF + "(" + Staff.COLUMN_ID + "), " +
            "FOREIGN KEY(" + COLUMN_ID + ") REFERENCES " + AccountHolder.TABLE_ACCOUNT_HOLDER + "(" + AccountHolder.COLUMN_ID + "));";

    //  Properties
    private int id;
    private int staffId;
    private int accHolderId;
    private Date dateAuthorised;

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getAccHolderId() {
        return accHolderId;
    }

    public void setAccHolderId(int accHolderId) {
        this.accHolderId = accHolderId;
    }

    public Date getDateAuthorised() {
        return dateAuthorised;
    }

    public void setDateAuthorised(Date dateAuthorised) {
        this.dateAuthorised = dateAuthorised;
    }
}
