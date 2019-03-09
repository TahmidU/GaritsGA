package database.domain;

public class FlexDiscountBand
{
    //  Table name and name of all columns.
    public static final String TABLE_FLEX_DISCOUNT_BAND = "flex_discount_band";
    public static final String COLUMN_FLEX_DISCOUNT_BAND_ID = "id";
    public static final String COLUMN_FLEX_ID = "flexible_discount_id";
    public static final String COLUMN_BAND = "band";
    public static final String COLUMN_BAND_DISCOUNT = "band_discount";

    //  Columns indexes.
    public static final int INDEX_FLEX_BAND_ID = 1;
    public static final int INDEX_FLEX_ID = 2;
    public static final int INDEX_BAND = 3;
    public static final int INDEX_BAND_DISCOUNT = 4;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FLEX_DISCOUNT_BAND = "CREATE TABLE " + TABLE_FLEX_DISCOUNT_BAND + " (" + COLUMN_FLEX_DISCOUNT_BAND_ID + " " +
            "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "" + COLUMN_FLEX_ID + " integer(10) NOT NULL, " + COLUMN_BAND + " varchar(2) NOT NULL UNIQUE, " +
            "" + COLUMN_BAND_DISCOUNT + " float(10) NOT NULL, FOREIGN KEY(" + COLUMN_FLEX_ID + ") " +
            "REFERENCES " + FlexibleDiscount.TABLE_DISCOUNT_PLAN + "(" + FlexibleDiscount.COLUMN_FLEX_DISCOUNT_ID + "));";

    //  Properties
    private int flexBandId;
    private int flexId;
    private String band;
    private float bandDiscount;

    //--------Getters and Setters--------
    public int getFlexBandId() {
        return flexBandId;
    }

    public void setFlexBandId(int flexBandId) {
        this.flexBandId = flexBandId;
    }

    public int getFlexId() {
        return flexId;
    }

    public void setFlexId(int flexId) {
        this.flexId = flexId;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public float getBandDiscount() {
        return bandDiscount;
    }

    public void setBandDiscount(float bandDiscount) {
        this.bandDiscount = bandDiscount;
    }
}
