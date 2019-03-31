package database.domain.reminder;

import database.dao.DBHelper;
import database.dao.account.AccountHolderDAO;
import database.dao.payment.InvoiceDAO;
import database.domain.payment.Invoice;
import database.domain.account.AccountHolder;

import java.sql.Date;

public class InvoiceReminder
{
    //  Table name and name of all columns.
    public static final String TABLE_INVOICE_REMINDER = "invoice_reminder";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_INVOICE_ID = "invoice_id";
    public static final String COLUMN_ACC_HOLDER_ID = "account_holder_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DATE_CREATED = "date_created";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_INVOICE_ID = 2;
    public static final int INDEX_ACC_HOLDER_ID = 3;
    public static final int INDEX_TYPE = 4;
    public static final int INDEX_DATE_CREATED = 5;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_INVOICE_REMINDER = "CREATE TABLE " + TABLE_INVOICE_REMINDER + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, " +
            "" + COLUMN_INVOICE_ID + " integer(10) NOT NULL, " + COLUMN_ACC_HOLDER_ID + " integer(10) NOT NULL, " + COLUMN_TYPE + " varchar(10) NOT NULL, " +
            "" + COLUMN_DATE_CREATED + " date NOT NULL, FOREIGN KEY(" + COLUMN_ACC_HOLDER_ID + ") " +
            "REFERENCES " + AccountHolder.TABLE_ACCOUNT_HOLDER + "(" + AccountHolder.COLUMN_ID + "), FOREIGN KEY(" + COLUMN_INVOICE_ID + ") " +
            "REFERENCES " + Invoice.TABLE_INVOICE + "(" + Invoice.COLUMN_ID + ")"+ DBHelper.ON_UPDATE + DBHelper.ON_DELETE+");";

    //  Properties
    private int id;
    private int invoiceId;
    private int accHolderId;
    private String type;
    private Date dateCreated;
    private Invoice invoice;
    private AccountHolder accountHolder;

    public InvoiceReminder(){}

    public InvoiceReminder(int id, int invoiceId, int accHolderId, String type, Date dateCreated) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.accHolderId = accHolderId;
        this.type = type;
        this.dateCreated = dateCreated;


    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Invoice getInvoice() {
        invoice = new InvoiceDAO().getById(invoiceId);
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public AccountHolder getAccountHolder() {
        accountHolder = new AccountHolderDAO().getById(accHolderId);
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}
