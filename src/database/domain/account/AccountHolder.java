package database.domain.account;

import database.dao.DBHelper;
import database.dao.discount.DiscountPlanDAO;
import database.dao.payment.OutstandingBalanceDAO;
import database.dao.reminder.InvoiceReminderDAO;
import database.dao.reminder.MOTReminderDAO;
import database.domain.discount.DiscountPlan;
import database.domain.payment.OutstandingBalance;
import database.domain.reminder.InvoiceReminder;
import database.domain.reminder.MOTReminder;

import java.sql.Date;
import java.util.ArrayList;

public class AccountHolder extends CustomerAcc
{
    //  Table name and name of all columns.
    public static final String TABLE_ACCOUNT_HOLDER = "account_holder";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NI = "national_insurance_number";
    public static final String COLUMN_DATE_JOINED = "date_joined";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_NI = 2;
    public static final int INDEX_DATE_JOINED = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_ACCOUNT_HOLDER = "CREATE TABLE " + TABLE_ACCOUNT_HOLDER + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_NI + " varchar(9) NOT NULL, " + COLUMN_DATE_JOINED + " date NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_NI + ") REFERENCES " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + "(" + CustomerAcc.COLUMN_NI + ")" +
            DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private String nationalInsurance;
    private Date dateJoined;
    private ArrayList<MOTReminder> motReminders;
    private ArrayList<InvoiceReminder> invoiceReminders;
    private OutstandingBalance outstandingBalance;
    private DiscountPlan discountPlan;

    public AccountHolder(){}

    public AccountHolder(int id, String nationalInsurance, Date dateJoined)
    {
        this.id = id;
        this.nationalInsurance = nationalInsurance;
        this.dateJoined = dateJoined;
    }


    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNationalInsurance() {
        return nationalInsurance;
    }

    @Override
    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public ArrayList<MOTReminder> getMotReminders() {

        motReminders = new MOTReminderDAO().getByAccountHolderId(id);
        return motReminders;
    }

    public void setMotReminders(ArrayList<MOTReminder> motReminders) {
        this.motReminders = motReminders;
    }

    public ArrayList<InvoiceReminder> getInvoiceReminders() {

        invoiceReminders = new InvoiceReminderDAO().getByAccountHolderId(id);
        return invoiceReminders;
    }

    public void setInvoiceReminders(ArrayList<InvoiceReminder> invoiceReminders) {
        this.invoiceReminders = invoiceReminders;
    }

    public OutstandingBalance getOutstandingBalance() {

        outstandingBalance = new OutstandingBalanceDAO().getByAccHolderId(id);
        return outstandingBalance;
    }

    public void setOutstandingBalance(OutstandingBalance outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public DiscountPlan getDiscountPlan() {

        discountPlan = new DiscountPlanDAO().getByAccId(id);
        return discountPlan;
    }

    public void setDiscountPlan(DiscountPlan discountPlan) {
        this.discountPlan = discountPlan;
    }
}
