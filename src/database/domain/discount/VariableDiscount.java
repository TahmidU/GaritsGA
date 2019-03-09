package database.domain.discount;

public class VariableDiscount extends DiscountPlan
{
    //  Table name and name of all columns.
    public static final String TABLE_VARIABLE_DISCOUNT = "variable_discount";
    public static final String COLUMN_VAR_ID = "id";


    //  Columns indexes.
    public static final int INDEX_VAR_ID = 1;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_VARIABLE_DISCOUNT = "CREATE TABLE " + TABLE_VARIABLE_DISCOUNT + " (" + COLUMN_VAR_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "" + COLUMN_ID + " integer(10) NOT NULL, FOREIGN KEY(" + COLUMN_ID + ") REFERENCES " + DiscountPlan.TABLE_DISCOUNT_PLAN + "(" + COLUMN_ID + "));";

    //  Properties
    private int varId;

    //--------Getters and Setters--------
    public int getVarId() {
        return varId;
    }

    public void setVarId(int varId) {
        this.varId = varId;
    }
}
