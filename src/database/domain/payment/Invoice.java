package database.domain.payment;

import database.domain.account.CustomerAcc;
import database.domain.job.JobSheet;

import java.sql.Date;

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
    public static final String CREATE_TABLE_INVOICE = "CREATE TABLE " + TABLE_INVOICE + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "" + COLUMN_NI + " varchar(9) NOT NULL, " + COLUMN_DATE_CREATED + " date NOT NULL, " +
            "" + COLUMN_TOTAL + " float(10) NOT NULL, " + COLUMN_JOB_NUM + " integer(10) NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_NI + ") REFERENCES " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + "(" + CustomerAcc.COLUMN_NI + "), " +
            "FOREIGN KEY(" + COLUMN_JOB_NUM + ") REFERENCES " + JobSheet.TABLE_JOB_SHEET + " (" + JobSheet.COLUMN_JOB_NUM + "));";

    //  Properties
    private int id;
    private String nationalInsurance;
    private Date dateCreated;
    private float totalAmount;
    private int jobNum;

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
}
