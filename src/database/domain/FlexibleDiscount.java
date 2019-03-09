package database.domain;

public class FlexibleDiscount extends DiscountPlan
{
    //  Table name and name of all columns.
    public static final String TABLE_FLEXIBLE_DISCOUNT = "flexible_discount";
    public static final String COLUMN_FLEX_DISCOUNT_ID = "id";

    //  Columns indexes.
    public static final int INDEX_FLEX_ID = 1;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_FLEXIBLE_DISCOUNT = "CREATE TABLE " + TABLE_FLEXIBLE_DISCOUNT + " (" + COLUMN_FLEX_DISCOUNT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "" + COLUMN_ID + " integer(10) NOT NULL, FOREIGN KEY(" + COLUMN_ID + ") REFERENCES " + TABLE_DISCOUNT_PLAN + "(" + COLUMN_ID + "));";

    //  Properties
    private int flexId;

    //--------Getters and Setters--------
    public int getFlexId() {
        return flexId;
    }

    public void setFlexId(int flexId) {
        this.flexId = flexId;
    }
}
