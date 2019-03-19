package database.domain.discount;

import database.dao.DBHelper;
import database.domain.account.CustomerAcc;

public class DiscountPlan
{
    //  Table name and name of all columns.
    public static final String TABLE_DISCOUNT_PLAN = "discount_plan";
    public static final String COLUMN_ID = "discount_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_NI = "customer_national_number";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_TYPE = 2;
    public static final int INDEX_NI = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_DISCOUNT_PLAN = "CREATE TABLE " + TABLE_DISCOUNT_PLAN + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_TYPE + " varchar(8) NOT NULL, " + COLUMN_NI + " varchar(9) NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_NI + ") " +
            "REFERENCES " + CustomerAcc.TABLE_CUSTOMER_ACCOUNT + "(" + CustomerAcc.COLUMN_NI + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private String type;
    private String nationalInsurance;

    public DiscountPlan(){}

    public DiscountPlan(int id, String type, String nationalInsurance)
    {
        this.id = id;
        this.type = type;
        this.nationalInsurance = nationalInsurance;
    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }
}