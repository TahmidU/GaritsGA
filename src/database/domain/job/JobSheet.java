package database.domain.job;

import database.domain.account.Staff;

import java.sql.Date;

public class JobSheet
{
    //  Table name and name of all columns.
    public static final String TABLE_JOB_SHEET = "job_sheet";
    public static final String COLUMN_JOB_NUM = "job_no";
    public static final String COLUMN_STAFF_ID = "staff_id";
    public static final String COLUMN_VEHICLE_REG = "vehicle_registration_number";
    public static final String COLUMN_BOOKING_ID = "booking_id";
    public static final String COLUMN_PROBLEM_DESC = "problem_desc";
    public static final String COLUMN_DATE_CREATED = "date_created";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_ALLOCATION_DATE = "allocation_date";
    public static final String COLUMN_DATE_COMPLETED = "date_completed";

    //  Columns indexes.
    public static final int INDEX_JOB_NUM = 1;
    public static final int INDEX_STAFF_ID = 2;
    public static final int INDEX_VEHICLE_REG = 3;
    public static final int INDEX_BOOKING_ID = 4;
    public static final int INDEX_PROBLEM_DESC = 5;
    public static final int INDEX_DATE_CREATED = 6;
    public static final int INDEX_STATUS = 7;
    public static final int INDEX_ALLOCATION_DATE = 8;
    public static final int INDEX_DATE_COMPLETED = 9;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_JOB_SHEET = "CREATE TABLE " + TABLE_JOB_SHEET + " (" + COLUMN_JOB_NUM + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "" + COLUMN_STAFF_ID + " integer(10), " + COLUMN_VEHICLE_REG + " varchar(7) NOT NULL, " +
            "" + COLUMN_BOOKING_ID + " integer(10) NOT NULL, " + COLUMN_PROBLEM_DESC + " varchar(1500) NOT NULL, " +
            "" + COLUMN_DATE_CREATED + " date NOT NULL, " + COLUMN_STATUS + " varchar(255) NOT NULL, " + COLUMN_ALLOCATION_DATE + " date, " +
            "" + COLUMN_DATE_COMPLETED + " date, FOREIGN KEY(" + COLUMN_VEHICLE_REG + ") " +
            "REFERENCES " + Vehicle.TABLE_VEHICLE + "(" + Vehicle.COLUMN_VEHICLE_REG + "), FOREIGN KEY(" + COLUMN_BOOKING_ID + ") " +
            "REFERENCES " + Booking.TABLE_BOOKING + "(" + Booking.COLUMN_ID + "), FOREIGN KEY(" + COLUMN_STAFF_ID + ") REFERENCES " + Staff.TABLE_STAFF + "(" + Staff.COLUMN_ID + "));";

    //  Properties
    private int jobNum;
    private int staffId;
    private String vehicleReg;
    private int bookingId;
    private String problemDesc;
    private Date dateCreated;
    private String status;
    private Date allocationDate;
    private Date dateCompleted;

    //--------Getters and Setters--------
    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
