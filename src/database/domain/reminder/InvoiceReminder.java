package database.domain.reminder;

import database.domain.payment.Invoice;
import database.domain.account.AccountHolder;

public class InvoiceReminder
{
    //  Table name and name of all columns.
    public static final String TABLE_INVOICE_REMINDER = "invoice_reminder";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_INVOICE_ID = "invoice_id";
    public static final String COLUMN_ACC_HOLDER_ID = "account_holder_id";
    public static final String COLUMN_REMINDERS_SENT = "reminders_sent";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_INVOICE_ID = 2;
    public static final int INDEX_ACC_HOLDER_ID = 3;
    public static final int INDEX_REMINDERS_SENT = 4;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_INVOICE_REMINDER = "CREATE TABLE " + TABLE_INVOICE_REMINDER + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_INVOICE_ID + " integer(10) NOT NULL, " + COLUMN_ACC_HOLDER_ID + " integer(10) NOT NULL, " +
            "" + COLUMN_REMINDERS_SENT + " integer(1) NOT NULL, FOREIGN KEY(" + COLUMN_ACC_HOLDER_ID + ") " +
            "REFERENCES " + AccountHolder.TABLE_ACCOUNT_HOLDER + "(" + AccountHolder.COLUMN_ID + "), FOREIGN KEY(" + COLUMN_INVOICE_ID + ") REFERENCES " + Invoice.TABLE_INVOICE + "(" + Invoice.COLUMN_ID + "));";

    //  Properties
    private int id;
    private int invoiceId;
    private int accHolderId;
    private int remindersSent;

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getAccHolderId() {
        return accHolderId;
    }

    public void setAccHolderId(int accHolderId) {
        this.accHolderId = accHolderId;
    }

    public int getRemindersSent() {
        return remindersSent;
    }

    public void setRemindersSent(int remindersSent) {
        this.remindersSent = remindersSent;
    }
}
