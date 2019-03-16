package database.domain.discount;

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
            DiscountPlan.COLUMN_ID + "));";

    //  Properties
    private int flexId;
    private int discountId;

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
}
