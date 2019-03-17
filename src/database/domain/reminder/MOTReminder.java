package database.domain.reminder;

import database.dao.DBHelper;
import database.domain.account.AccountHolder;

import java.sql.Date;

public class MOTReminder
{
    //  Table name and name of all columns.
    public static final String TABLE_MOT_REMINDERS = "mot_reminder";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACCOUNT_HOLDER_ID = "account_holder_id";
    public static final String COLUMN_RENEWAL_DATE = "renewal_date";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_ACCOUNT_HOLDER = 2;
    public static final int INDEX_RENEWAL_DATE = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_MOT_REMINDER = "CREATE TABLE " + TABLE_MOT_REMINDERS + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_ACCOUNT_HOLDER_ID + " integer(10) NOT NULL, " + COLUMN_RENEWAL_DATE +" date NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_ACCOUNT_HOLDER_ID + ") REFERENCES " + AccountHolder.TABLE_ACCOUNT_HOLDER + "(" + AccountHolder.COLUMN_ID + ")"+
            DBHelper.ON_UPDATE + DBHelper.ON_DELETE+");";

    //  Properties
    private int id;
    private int accountHolderId;
    private Date renewalDate;

    public MOTReminder(){}

    public MOTReminder(int id, int accountHolderId, Date renewalDate) {
        this.id = id;
        this.accountHolderId = accountHolderId;
        this.renewalDate = renewalDate;
    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(int accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }
}
