package database.domain.job;

import database.dao.DBHelper;
import database.dao.account.StaffDAO;
import database.dao.job.BookingDAO;
import database.dao.job.TaskDAO;
import database.dao.payment.InvoiceDAO;
import database.domain.account.Staff;
import database.domain.part.StockPart;
import database.domain.payment.Invoice;

import java.sql.Date;
import java.util.ArrayList;

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
    public static final String COLUMN_DATE_COMPLETED = "date_completed";
    public static final String COLUMN_PART_ID = "part_id";
    public static final String COLUMN_PART_QTY = "part_qty";

    //  Columns indexes.
    public static final int INDEX_JOB_NUM = 1;
    public static final int INDEX_STAFF_ID = 2;
    public static final int INDEX_VEHICLE_REG = 3;
    public static final int INDEX_BOOKING_ID = 4;
    public static final int INDEX_PROBLEM_DESC = 5;
    public static final int INDEX_DATE_CREATED = 6;
    public static final int INDEX_STATUS = 7;
    public static final int INDEX_DATE_COMPLETED = 8;
    public static final int INDEX_PART_ID = 9;
    public static final int INDEX_PART_QTY = 10;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_JOB_SHEET = "CREATE TABLE " + TABLE_JOB_SHEET + " (" + COLUMN_JOB_NUM + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_STAFF_ID + " integer(10), " + COLUMN_VEHICLE_REG + " varchar(7) NOT NULL, " +
            "" + COLUMN_BOOKING_ID + " integer(10) NOT NULL, " + COLUMN_PROBLEM_DESC + " varchar(1500) NOT NULL, " +
            "" + COLUMN_DATE_CREATED + " date NOT NULL, " + COLUMN_STATUS + " varchar(255) NOT NULL, " +
            "" + COLUMN_DATE_COMPLETED + " date, " + COLUMN_PART_ID + " integer(10)," + COLUMN_PART_QTY + " integer(10)," +
            " FOREIGN KEY(" + COLUMN_PART_ID + ") REFERENCES " + StockPart.TABLE_STOCK_PART+"("+StockPart.COLUMN_PART_ID+"),"
            + " FOREIGN KEY(" + COLUMN_VEHICLE_REG + ") REFERENCES " + Vehicle.TABLE_VEHICLE + "(" + Vehicle.COLUMN_VEHICLE_REG + "), FOREIGN KEY(" + COLUMN_BOOKING_ID + ") " +
            "REFERENCES " + Booking.TABLE_BOOKING + "(" + Booking.COLUMN_ID + "), FOREIGN KEY(" + COLUMN_STAFF_ID + ") REFERENCES " + Staff.TABLE_STAFF + "(" + Staff.COLUMN_ID + ")"
            + DBHelper.ON_UPDATE+");";

    //  Properties
    private int jobNum;
    private int staffId;
    private String vehicleReg;
    private int bookingId;
    private String problemDesc;
    private Date dateCreated;
    private String status;
    private Date dateCompleted;
    private Staff staff;
    private Booking booking;
    private Invoice invoice;
    private Vehicle vehicle;
    private int partId;
    private int partQty;
    private ArrayList<Task> tasks;


    public JobSheet(){}

    public JobSheet(int jobNum, int staffId, String vehicleReg, int bookingId, String problemDesc,
                    Date dateCreated, String status, Date dateCompleted, int partId, int partQty) {
        this.jobNum = jobNum;
        this.staffId = staffId;
        this.vehicleReg = vehicleReg;
        this.bookingId = bookingId;
        this.problemDesc = problemDesc;
        this.dateCreated = dateCreated;
        this.status = status;
        this.dateCompleted = dateCompleted;
        this.partId = partId;
        this.partQty = partQty;
    }

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

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Staff getStaff() {
        staff = new StaffDAO().getById(staffId);
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Booking getBooking() {
        booking = new BookingDAO().getById(bookingId);
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Invoice getInvoice() {
        invoice = new InvoiceDAO().getByJobNum(jobNum);
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ArrayList<Task> getTasks() {
        tasks = new TaskDAO().getByJobNum(jobNum);
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getPartQty() {
        return partQty;
    }

    public void setPartQty(int partQty) {
        this.partQty = partQty;
    }
}
