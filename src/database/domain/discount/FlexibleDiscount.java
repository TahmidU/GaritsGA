package database.domain.discount;

import database.dao.DBHelper;
import database.dao.discount.DiscountPlanDAO;
import database.dao.discount.FlexDiscountBandFlexibleDiscountDAO;

import java.util.ArrayList;

public class FlexibleDiscount
{
    //  Table name and name of all columns.
    public static final String TABLE_FLEXIBLE_DISCOUNT = "flexible_discount";
    public static final String COLUMN_FLEX_DISCOUNT_ID = "id";

    //  Columns indexes.
    public static final int INDEX_FLEX_ID = 1;
    public static final int INDEX_DISCOUNT_ID = 2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FLEXIBLE_DISCOUNT = "CREATE TABLE " + TABLE_FLEXIBLE_DISCOUNT + " (" + COLUMN_FLEX_DISCOUNT_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + DiscountPlan.COLUMN_ID + " integer(10) NOT NULL, FOREIGN KEY(" + DiscountPlan.COLUMN_ID + ") REFERENCES " + DiscountPlan.TABLE_DISCOUNT_PLAN + "(" +
            DiscountPlan.COLUMN_ID + ")" + DBHelper.ON_UPDATE+");";

    //  Properties
    private int flexId;
    private int discountId;
    private DiscountPlan discountPlan;
    private ArrayList<FlexDiscountBandFlexibleDiscount> flexDiscountBandFlexibleDiscounts;

    public FlexibleDiscount(){}

    public FlexibleDiscount(int flexId, int discountId) {
        this.flexId = flexId;
        this.discountId = discountId;


    }

    //--------Getters and Setters--------
    public int getFlexId() {
        return flexId;
    }

    public void setFlexId(int flexId) {
        this.flexId = flexId;
    }

    public int getDiscountId() {

        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public DiscountPlan getDiscountPlan() {
        discountPlan = new DiscountPlanDAO().getByDiscountId(discountId);
        return discountPlan;
    }

    public void setDiscountPlan(DiscountPlan discountPlan) {
        this.discountPlan = discountPlan;
    }

    public ArrayList<FlexDiscountBandFlexibleDiscount> getFlexDiscountBandFlexibleDiscounts() {
        flexDiscountBandFlexibleDiscounts = new FlexDiscountBandFlexibleDiscountDAO().getByDiscountId(flexId);
        return flexDiscountBandFlexibleDiscounts;
    }

    public void setFlexDiscountBandFlexibleDiscounts(ArrayList<FlexDiscountBandFlexibleDiscount> flexDiscountBandFlexibleDiscounts) {
        this.flexDiscountBandFlexibleDiscounts = flexDiscountBandFlexibleDiscounts;
    }
}
