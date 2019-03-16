package database.domain.discount;

public class FixedDiscount
{
    //  Table name and name of all columns.
    public static final String TABLE_FIXED_DISCOUNT = "fixed_discount";
    public static final String COLUMN_FIX_DISCOUNT_ID = "id";
    public static final String COLUMN_PERCENTAGE = "percentage";

    //  Columns indexes.
    public static final int INDEX_FIX_DISCOUNT_ID = 1;
    public static final int INDEX_DISCOUNT_PLAN_ID = 2;
    public static final int INDEX_PERCENTAGE = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FIXED_DISCOUNT = "CREATE TABLE " + TABLE_FIXED_DISCOUNT + " (" + COLUMN_FIX_DISCOUNT_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + DiscountPlan.COLUMN_ID + " integer(10) NOT NULL, " + COLUMN_PERCENTAGE + " float(10) NOT NULL, " +
            "FOREIGN KEY(" + DiscountPlan.COLUMN_ID + ") REFERENCES " + DiscountPlan.TABLE_DISCOUNT_PLAN + "(" + DiscountPlan.COLUMN_ID + "));";

    //  Properties
    private int fixedDiscountId;
    private int discountPlanId;
    private float percentage;

    public FixedDiscount(){}

    public FixedDiscount(int fixedDiscountId, int discountPlanId, float percentage)
    {
        this.fixedDiscountId = fixedDiscountId;
        this.percentage = percentage;
    }

    //--------Getters and Setters--------
    public int getFixedDiscountId() {
        return fixedDiscountId;
    }

    public void setFixedDiscountId(int fixedDiscountId) {
        this.fixedDiscountId = fixedDiscountId;
    }

    public int getDiscountPlanId() {
        return discountPlanId;
    }

    public void setDiscountPlanId(int discountPlanId) {
        this.discountPlanId = discountPlanId;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
