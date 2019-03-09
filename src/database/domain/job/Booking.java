package database.domain.job;

import java.sql.Date;

public class Booking
{
    //  Table name and name of all columns.
    public static final String TABLE_BOOKING = "booking";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JOB_TYPE = "job_type";
    public static final String COLUMN_DATE_BOOKED = "date_booked";
    public static final String COLUMN_VEHICLE_REG = "vehicle_registration_number";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_JOB_TYPE = 2;
    public static final int INDEX_DATE_BOOKED = 3;
    public static final int INDEX_VEHICLE_REG = 4;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_BOOKING = "CREATE TABLE " + TABLE_BOOKING + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "" + COLUMN_JOB_TYPE + " varchar(10) NOT NULL, " + COLUMN_DATE_BOOKED + " date NOT NULL, " + COLUMN_VEHICLE_REG + " varchar(7) NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_VEHICLE_REG + ") REFERENCES " + Vehicle.TABLE_VEHICLE + "(" + Vehicle.COLUMN_VEHICLE_REG + "));";

    //  Properties
    private int id;
    private String jobType;
    private Date dateBooked;
    private String vehicleRegistrationNumber;

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
}
