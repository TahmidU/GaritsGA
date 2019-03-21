package database.domain.job;

import database.dao.DBHelper;
import database.domain.discount.VariableDiscount;
import database.domain.job.Task;

public class VariableTask
{
    //  Table name and name of all columns.
    public static final String TABLE_VARIABLE_TASK = "variable_task";
    public static final String COLUMN_TASK_ID = "task_id";
    public static final String COLUMN_VAR_DISCOUNT_ID = "variable_discount_id";
    public static final String COLUMN_DISCOUNT_VAL = "discount_value";

    //  Columns indexes.
    public static final int INDEX_TASK_ID = 1;
    public static final int INDEX_VAR_DISCOUNT_ID = 2;
    public static final int INDEX_DISCOUNT_VAL = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_VARIABLE_TASK = "CREATE TABLE " + TABLE_VARIABLE_TASK + " (" + COLUMN_TASK_ID + " integer(10) NOT NULL, " +
            "" + COLUMN_VAR_DISCOUNT_ID + " integer(10) NOT NULL, " + COLUMN_DISCOUNT_VAL + " float(10) NOT NULL, " +
            "PRIMARY KEY (" + COLUMN_TASK_ID + ", " + COLUMN_DISCOUNT_VAL + "), FOREIGN KEY(" + COLUMN_TASK_ID + ") REFERENCES " + Task.TABLE_TASK + "(" + Task.COLUMN_ID + "), " +
            "FOREIGN KEY(" + COLUMN_VAR_DISCOUNT_ID + ") REFERENCES " + VariableDiscount.TABLE_VARIABLE_DISCOUNT + "(" + VariableDiscount.COLUMN_VAR_ID + ")"+ DBHelper.ON_UPDATE + DBHelper.ON_DELETE+");";

    //  Properties
    private int taskId;
    private int varDiscountId;
    private float discountVal;

    public VariableTask(){}

    public VariableTask(int taskId, int varDiscountId, float discountVal) {
        this.taskId = taskId;
        this.varDiscountId = varDiscountId;
        this.discountVal = discountVal;
    }

    //--------Getters and Setters--------
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getVarDiscountId() {
        return varDiscountId;
    }

    public void setVarDiscountId(int varDiscountId) {
        this.varDiscountId = varDiscountId;
    }

    public float getDiscountVal() {
        return discountVal;
    }

    public void setDiscountVal(float discountVal) {
        this.discountVal = discountVal;
    }
}
