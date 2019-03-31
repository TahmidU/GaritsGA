package database.domain.payment;

import database.dao.DBHelper;
import database.dao.account.CustomerAccDAO;
import database.dao.job.JobSheetDAO;
import database.dao.reminder.InvoiceReminderDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.JobSheet;
import database.domain.reminder.InvoiceReminder;

import java.sql.Date;
import java.util.ArrayList;

public class Invoice
{
    //  Table name and name of all columns.
    public static final String TABLE_INVOICE = "invoice";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NI = "national_insurance_number";
    public static final String COLUMN_DATE_CREATED = "date_created";
    public static final String COLUMN_TOTAL = "total_amount";
    public static final String COLUMN_JOB_NUM = "job_no";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_NI = 2;
    public static final int INDEX_DATE_CREATED = 3;
    public static final int INDEX_TOTAL = 4;
    public static final int INDEX_JOB_NUM = 5;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_INVOICE = "CREATE TABLE " + TABLE_INVOICE + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_NI + " varchar(9) NOT NULL, " + COLUMN_DATE_CREATED + " date NOT NULL, " +
            "" + COLUMN_TOTAL + " float(10) NOT NULL, " + COLUMN_JOB_NUM + " integer(10) NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_NI + ") REFERENCES " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + "(" + CustomerAcc.COLUMN_NI + "), " +
            "FOREIGN KEY(" + COLUMN_JOB_NUM + ") REFERENCES " + JobSheet.TABLE_JOB_SHEET + " (" + JobSheet.COLUMN_JOB_NUM + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private String nationalInsurance;
    private Date dateCreated;
    private float totalAmount;
    private int jobNum;
    private JobSheet jobSheet;
    private CustomerAcc customerAcc;
    private ArrayList<InvoiceReminder> invoiceReminders;

    public Invoice(){}

    public Invoice(int id, String nationalInsurance, Date dateCreated, float totalAmount, int jobNum) {
        this.id = id;
        this.nationalInsurance = nationalInsurance;
        this.dateCreated = dateCreated;
        this.totalAmount = totalAmount;
        this.jobNum = jobNum;



    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public JobSheet getJobSheet() {
        jobSheet = new JobSheetDAO().getByJobNum(jobNum);
        return jobSheet;
    }

    public void setJobSheet(JobSheet jobSheet) {
        this.jobSheet = jobSheet;
    }

    public CustomerAcc getCustomerAcc() {
        customerAcc = new CustomerAccDAO().getByNI(nationalInsurance);
        return customerAcc;
    }

    public void setCustomerAcc(CustomerAcc customerAcc) {
        this.customerAcc = customerAcc;
    }

    public ArrayList<InvoiceReminder> getInvoiceReminders() {
        invoiceReminders = new InvoiceReminderDAO().getByInvoiceId(id);
        return invoiceReminders;
    }

    public void setInvoiceReminders(ArrayList<InvoiceReminder> invoiceReminders) {
        this.invoiceReminders = invoiceReminders;
    }
}
