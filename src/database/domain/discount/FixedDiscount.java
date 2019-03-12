package database.domain.discount;

public class FixedDiscount extends DiscountPlan
{
    //  Table name and name of all columns.
    public static final String TABLE_FIXED_DISCOUNT = "fixed_discount";
    public static final String COLUMN_FIX_DISCOUNT_ID = "id";
    public static final String COLUMN_PERCENTAGE = "percentage";

    //  Columns indexes.
    public static final int INDEX_FIX_DISCOUNT_ID = 1;
    public static final int INDEX_PERCENTAGE = 2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FIXED_DISCOUNT = "CREATE TABLE " + TABLE_FIXED_DISCOUNT + " (" + COLUMN_FIX_DISCOUNT_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_ID + " integer(10) NOT NULL, " + COLUMN_PERCENTAGE + " float(10) NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_ID + ") REFERENCES " + TABLE_DISCOUNT_PLAN + "(" + COLUMN_ID + "));";

    //  Properties
    private int fixedDiscountId;
    private float percentage;

    //--------Getters and Setters--------
    public int getFixedDiscountId() {
        return fixedDiscountId;
    }

    public void setFixedDiscountId(int fixedDiscountId) {
        this.fixedDiscountId = fixedDiscountId;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
