package database.domain.discount;

import database.dao.DBHelper;
import database.dao.discount.DiscountPlanDAO;
import database.dao.job.VariableTaskDAO;
import database.domain.job.VariableTask;

import java.util.ArrayList;

public class VariableDiscount
{
    //  Table name and name of all columns.
    public static final String TABLE_VARIABLE_DISCOUNT = "variable_discount";
    public static final String COLUMN_VAR_ID = "id";


    //  Columns indexes.
    public static final int INDEX_VAR_ID = 1;
    public static final int INDEX_DISCOUNT_ID =2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_VARIABLE_DISCOUNT = "CREATE TABLE " + TABLE_VARIABLE_DISCOUNT + " (" + COLUMN_VAR_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + DiscountPlan.COLUMN_ID + " integer(10) NOT NULL, FOREIGN KEY(" + DiscountPlan.COLUMN_ID + ") REFERENCES " +
            DiscountPlan.TABLE_DISCOUNT_PLAN + "(" + DiscountPlan.COLUMN_ID + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int varId;
    private int discountId;
    private DiscountPlan discountPlan;
    private ArrayList<VariableTask> variableTasks;

    public VariableDiscount(){}

    public VariableDiscount(int varId, int discountId) {
        this.varId = varId;
        this.discountId = discountId;
        discountPlan = new DiscountPlanDAO().getByDiscountId(discountId);
        variableTasks = new VariableTaskDAO().getByVariableDiscountId(varId);
    }

    //--------Getters and Setters--------
    public int getVarId() {
        return varId;
    }

    public void setVarId(int varId) {
        this.varId = varId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public DiscountPlan getDiscountPlan() {
        return discountPlan;
    }

    public void setDiscountPlan(DiscountPlan discountPlan) {
        this.discountPlan = discountPlan;
    }

    public ArrayList<VariableTask> getVariableTasks() {
        return variableTasks;
    }

    public void setVariableTasks(ArrayList<VariableTask> variableTasks) {
        this.variableTasks = variableTasks;
    }
}
