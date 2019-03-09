package database.domain;

public class Vehicle
{
    //  Table name and name of all columns.
    public static final String TABLE_VEHICLE = "vehicle";
    public static final String COLUMN_VEHICLE_REG = "registration_number";
    public static final String COLUMN_NI = "national_insurance_number";
    public static final String COLUMN_MAKE = "make";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_ENGINE_SERIAL = "engine_serial";
    public static final String COLUMN_CHASSIS_NUM = "chassis_number";
    public static final String COLUMN_COLOR = "color";

    //  Columns indexes.
    public static final int INDEX_VEHICLE_REG = 1;
    public static final int INDEX_NI = 2;
    public static final int INDEX_MAKE = 3;
    public static final int INDEX_MODEL = 4;
    public static final int INDEX_ENGINE_SERIAL = 5;
    public static final int INDEX_CHASSIS_NUM = 6;
    public static final int INDEX_COLOR = 7;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_VEHICLE = "CREATE TABLE " + TABLE_VEHICLE + " (" + COLUMN_VEHICLE_REG + " varchar(7) NOT NULL, " +
        "" + COLUMN_NI + " varchar(9) NOT NULL, " + COLUMN_MAKE + " varchar(50) NOT NULL, " +
        "" + COLUMN_MODEL + " varchar(50) NOT NULL, " + COLUMN_ENGINE_SERIAL + " varchar(11) NOT NULL UNIQUE, " +
        "" + COLUMN_CHASSIS_NUM + " varchar(11) NOT NULL UNIQUE, " + COLUMN_COLOR + " varchar(10) NOT NULL, " +
        "PRIMARY KEY (" + COLUMN_VEHICLE_REG + "), FOREIGN KEY(" + COLUMN_NI + ") " +
        "REFERENCES " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + "(" + CustomerAcc.COLUMN_NI + "));";

    //  Properties
    private String vehicleRegistration;
    private String nationalInsurance;
    private String make;
    private String model;
    private String engineSerial;
    private String chassisNum;
    private String color;

    //--------Getters and Setters--------
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineSerial() {
        return engineSerial;
    }

    public void setEngineSerial(String engineSerial) {
        this.engineSerial = engineSerial;
    }

    public String getChassisNum() {
        return chassisNum;
    }

    public void setChassisNum(String chassisNum) {
        this.chassisNum = chassisNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
