package database.domain.discount;

public class FlexDiscountBand
{
    //  Table name and name of all columns.
    public static final String TABLE_FLEX_DISCOUNT_BAND = "flex_discount_band";
    public static final String COLUMN_BAND = "valuation_band";
    public static final String COLUMN_BAND_DISCOUNT = "band_discount";

    //  Columns indexes.
    public static final int INDEX_BAND = 1;
    public static final int INDEX_BAND_DISCOUNT = 2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FLEX_DISCOUNT_BAND = "CREATE TABLE " + TABLE_FLEX_DISCOUNT_BAND + " (" + COLUMN_BAND + " varchar(2) NOT NULL, " +
            "" + COLUMN_BAND_DISCOUNT + " float(10) NOT NULL, PRIMARY KEY (" + COLUMN_BAND + "));";

    //  Properties
    private String flexBandId;
    private float bandDiscount;

    public FlexDiscountBand(){}

    public FlexDiscountBand(String flexBandId, float bandDiscount) {
        this.flexBandId = flexBandId;
        this.bandDiscount = bandDiscount;
    }

    //--------Getters and Setters--------
    public String getFlexBandId() {
        return flexBandId;
    }

    public void setFlexBandId(String flexBandId) {
        this.flexBandId = flexBandId;
    }

    public float getBandDiscount() {
        return bandDiscount;
    }

    public void setBandDiscount(float bandDiscount) {
        this.bandDiscount = bandDiscount;
    }
}
