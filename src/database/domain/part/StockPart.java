package database.domain.part;

import database.dao.DBHelper;

public class StockPart
{
    //  Table name and name of all columns.
    public static final String TABLE_STOCK_PART = "stock_part";
    public static final String COLUMN_PART_ID = "part_id";
    public static final String COLUMN_PART_NAME = "part_name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_LOW_THRES = "low_threshold";
    public static final String COLUMN_MANUFACTURER = "manufacturer";
    public static final String COLUMN_VEHICLE_TYPE = "vehicle_type";
    public static final String COLUMN_START_YEAR = "start_year";
    public static final String COLUMN_END_YEAR = "end_year";

    //  Columns indexes.
    public static final int INDEX_PART_ID = 1;
    public static final int INDEX_PART_NAME = 2;
    public static final int INDEX_PRICE = 3;
    public static final int INDEX_LOW_THRES = 4;
    public static final int INDEX_MANUFACTURER = 5;
    public static final int INDEX_VEHICLE_TYPE = 6;
    public static final int INDEX_START_YEAR = 7;
    public static final int INDEX_END_YEAR = 8;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_STOCK_PART = "CREATE TABLE " + TABLE_STOCK_PART + " (" + COLUMN_PART_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_PART_NAME + " varchar(50) NOT NULL, " + COLUMN_PRICE + " float(10) NOT NULL, " + COLUMN_LOW_THRES + " integer(10) NOT NULL, " +
            "" + COLUMN_MANUFACTURER + " varchar(50) NOT NULL, " + COLUMN_VEHICLE_TYPE + " varchar(50) NOT NULL, " +
            "" + COLUMN_START_YEAR + " varchar(4) NOT NULL, " + COLUMN_END_YEAR + " varchar(4) NOT NULL);";

    //  Properties
    private int partId;
    private String partName;
    private float price;
    private int threshold;
    private String manufacturer;
    private String vehicleType;
    private String startYr;
    private String endYr;

    public StockPart(){}

    public StockPart(int partId, String partName, float price, int threshold, String manufacturer, String vehicleType, String startYr, String endYr) {
        this.partId = partId;
        this.partName = partName;
        this.price = price;
        this.threshold = threshold;
        this.manufacturer = manufacturer;
        this.vehicleType = vehicleType;
        this.startYr = startYr;
        this.endYr = endYr;
    }

    //--------Getters and Setters--------
    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getStartYr() {
        return startYr;
    }

    public void setStartYr(String startYr) {
        this.startYr = startYr;
    }

    public String getEndYr() {
        return endYr;
    }

    public void setEndYr(String endYr) {
        this.endYr = endYr;
    }
}
