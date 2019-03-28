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
    public static final String COLUMN_NAME = "name_column";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_JOB_TYPE = 2;
    public static final int INDEX_DATE_BOOKED = 3;
    public static final int INDEX_VEHICLE_REG = 4;
    public static final int INDEX_NAME = 5;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_BOOKING = "CREATE TABLE " + TABLE_BOOKING + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_JOB_TYPE + " varchar(10) NOT NULL, " + COLUMN_DATE_BOOKED + " date NOT NULL, " + COLUMN_VEHICLE_REG + " varchar(7) NOT NULL, " +
            COLUMN_NAME + " varchar(50) NOT NULL, " + "FOREIGN KEY(" + COLUMN_VEHICLE_REG + ") " +
            "REFERENCES " + Vehicle.TABLE_VEHICLE + "(" + Vehicle.COLUMN_VEHICLE_REG + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private String jobType;
    private Date dateBooked;
    private String vehicleRegistrationNumber;
    private String name;
    private Vehicle vehicle;
    private JobSheet jobSheet;

    public Booking(){}

    public Booking(int id, String jobType, Date dateBooked, String vehicleRegistrationNumber, String name) {
        this.id = id;
        this.jobType = jobType;
        this.dateBooked = dateBooked;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.name = name;
        vehicle = new VehicleDAO().getByRegNum(vehicleRegistrationNumber);
        jobSheet = new JobSheetDAO().getByBookingId(id);
    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public JobSheet getJobSheet() {
        return jobSheet;
    }

    public void setJobSheet(JobSheet jobSheet) {
        this.jobSheet = jobSheet;
    }
}
