package database.domain.job;

import database.dao.DBHelper;
import database.dao.job.JobSheetDAO;
import database.dao.job.VehicleDAO;

import java.sql.Date;

public class Booking
{
    //  Table name and name of all columns.
    public static final String TABLE_BOOKING = "booking";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JOB_TYPE = "job_type";
    public static final String COLUMN_DATE_BOOKED = "date_booked";
    public static final String COLUMN_VEHICLE_REG = "vehicle_registration_number";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_CHECK_IN = "check_in";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_JOB_TYPE = 2;
    public static final int INDEX_DATE_BOOKED = 3;
    public static final int INDEX_VEHICLE_REG = 4;
    public static final int INDEX_FIRST_NAME = 5;
    public static final int INDEX_LAST_NAME = 6;
    public static final int INDEX_CHECK_IN = 7;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_BOOKING = "CREATE TABLE " + TABLE_BOOKING + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_JOB_TYPE + " varchar(10) NOT NULL, " + COLUMN_DATE_BOOKED + " date NOT NULL, " + COLUMN_VEHICLE_REG + " varchar(7) NOT NULL, " +
            COLUMN_FIRST_NAME + " varchar(50), " + COLUMN_LAST_NAME + " varchar(50), " + COLUMN_CHECK_IN + " varchar(10), " +
            "FOREIGN KEY(" + COLUMN_VEHICLE_REG + ") " + "REFERENCES " + Vehicle.TABLE_VEHICLE + "(" + Vehicle.COLUMN_VEHICLE_REG + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private String jobType;
    private Date dateBooked;
    private String vehicleRegistrationNumber;
    private String firstName;
    private String lastName;
    private String checkIn;
    private Vehicle vehicle;
    private JobSheet jobSheet;

    public Booking(){}

    public Booking(int id, String jobType, Date dateBooked, String vehicleRegistrationNumber, String firstName, String lastName, String checkIn) {
        this.id = id;
        this.jobType = jobType;
        this.dateBooked = dateBooked;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkIn = checkIn;


    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Date getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public Vehicle getVehicle() {
        vehicle = new VehicleDAO().getByRegNum(vehicleRegistrationNumber);
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public JobSheet getJobSheet()
    {
        jobSheet = new JobSheetDAO().getByBookingId(id);
        return jobSheet;
    }

    public void setJobSheet(JobSheet jobSheet) {
        this.jobSheet = jobSheet;
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

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
}
