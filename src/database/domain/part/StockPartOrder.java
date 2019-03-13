package database.domain.part;

public class StockPartOrder
{
    //  Table name and name of all columns.
    public static final String TABLE_STOCK_PART_ORDER = "stock_part_order";
    public static final String COLUMN_PART_ORDER_NUM = "part_order_number";
    public static final String COLUMN_STOCK_PART_ID = "stock_part_id";


    //  Columns indexes.
    public static final int INDEX_PART_ORDER_NUM = 1;
    public static final int INDEX_STOCK_PART_ID = 2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_STOCK_PART_ORDER = "CREATE TABLE " + TABLE_STOCK_PART_ORDER + " (" + COLUMN_PART_ORDER_NUM + " varchar(30) NOT NULL, " +
            "" + COLUMN_STOCK_PART_ID + " integer(10) NOT NULL, PRIMARY KEY (" + COLUMN_PART_ORDER_NUM + ", " + COLUMN_STOCK_PART_ID + "), " +
            "FOREIGN KEY(" + COLUMN_PART_ORDER_NUM + ") REFERENCES " + PartOrder.TABLE_PART_ORDER + "(" + PartOrder.COLUMN_ORDER_NUM + "), " +
            "FOREIGN KEY(" + COLUMN_STOCK_PART_ID + ") REFERENCES " + StockPart.TABLE_STOCK_PART + "(" + StockPart.COLUMN_PART_ID + "));";

    //  Properties
    private int partId;
    private String partName;
    private float price;
    private int threshold;
    private String manufacturer;
    private String vehicleType;
    private String startYr;
    private String endYr;

    public StockPartOrder(){}

    public StockPartOrder(int partId, String partName, float price, int threshold, String manufacturer, String vehicleType, String startYr, String endYr) {
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
