package database.domain.discount;

import database.dao.DBHelper;
import database.dao.discount.FlexDiscountBandDAO;
import database.dao.discount.FlexibleDiscountDAO;

public class FlexDiscountBandFlexibleDiscount
{
    //  Table name and name of all columns.
    public static final String TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT = "flex_discount_band_flexible_discount";
    public static final String COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND = "flex_discount_band_valuation_band";
    public static final String COLUMN_FLEXIBLE_DISCOUNT_ID = "flexible_discount_id";

    //  Columns indexes.
    public static final int INDEX_FLEX_DISCOUNT_BAND_VALUATION_BAND = 1;
    public static final int INDEX_FLEXIBLE_DISCOUNT_ID = 2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FLEX_DISCOUNT_BAND = "CREATE TABLE " + TABLE_FLEX_DISCOUNT_BAND_FLEX_DISCOUNT + " " +
            "(" + COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND + " varchar(2) NOT NULL, " + COLUMN_FLEXIBLE_DISCOUNT_ID + " integer(10) NOT NULL, " +
            "PRIMARY KEY (" + COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND + ", " + COLUMN_FLEXIBLE_DISCOUNT_ID + "), FOREIGN KEY(" + COLUMN_FLEX_DISCOUNT_BAND_VALUATION_BAND + ") " +
            "REFERENCES " + FlexDiscountBand.TABLE_FLEX_DISCOUNT_BAND + "(" + FlexDiscountBand.COLUMN_BAND + "), " +
            "FOREIGN KEY(" + COLUMN_FLEXIBLE_DISCOUNT_ID + ") REFERENCES " + FlexibleDiscount.TABLE_FLEXIBLE_DISCOUNT + "(" + FlexibleDiscount.COLUMN_FLEX_DISCOUNT_ID + ")"
             + DBHelper.ON_UPDATE + DBHelper.ON_DELETE+");";
    //  Properties
    private int flexDiscountBandFlexDiscount;
    private String flexDiscountBandValuationBand;
    private FlexDiscountBand flexDiscountBand;
    private FlexibleDiscount flexibleDiscount;

    public FlexDiscountBandFlexibleDiscount(){}

    public FlexDiscountBandFlexibleDiscount(int flexDiscountBandFlexDiscount, String flexDiscountBandValuationBand)
    {
        this.flexDiscountBandFlexDiscount = flexDiscountBandFlexDiscount;
        this.flexDiscountBandValuationBand = flexDiscountBandValuationBand;


    }

    //--------Getters and Setters--------
    public int getFlexDiscountBandFlexDiscount() {
        return flexDiscountBandFlexDiscount;
    }

    public void setFlexDiscountBandFlexDiscount(int flexDiscountBandFlexDiscount) {
        this.flexDiscountBandFlexDiscount = flexDiscountBandFlexDiscount;
    }

    public String getFlexDiscountBandValuationBand() {
        return flexDiscountBandValuationBand;
    }

    public void setFlexDiscountBandValuationBand(String flexDiscountBandValuationBand) {
        this.flexDiscountBandValuationBand = flexDiscountBandValuationBand;
    }

    public FlexDiscountBand getFlexDiscountBand() {
        flexDiscountBand = new FlexDiscountBandDAO().getByValuationBand(flexDiscountBandValuationBand);
        return flexDiscountBand;
    }

    public void setFlexDiscountBand(FlexDiscountBand flexDiscountBand) {
        this.flexDiscountBand = flexDiscountBand;
    }

    public FlexibleDiscount getFlexibleDiscount() {
        flexibleDiscount = new FlexibleDiscountDAO().getById(flexDiscountBandFlexDiscount);
        return flexibleDiscount;
    }

    public void setFlexibleDiscount(FlexibleDiscount flexibleDiscount) {
        this.flexibleDiscount = flexibleDiscount;
    }
}
