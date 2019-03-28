package database.domain.discount;

import database.dao.DBHelper;
import database.dao.account.AccountHolderDAO;
import database.dao.discount.FixedDiscountDAO;
import database.dao.discount.FlexibleDiscountDAO;
import database.dao.discount.VariableDiscountDAO;
import database.domain.account.AccountHolder;

public class DiscountPlan
{
    //  Table name and name of all columns.
    public static final String TABLE_DISCOUNT_PLAN = "discount_plan";
    public static final String COLUMN_ID = "discount_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_ACC_HOLDER_ID = "acc_holder_id";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_TYPE = 2;
    public static final int INDEX_ACC_HOLDER_ID = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_DISCOUNT_PLAN = "CREATE TABLE " + TABLE_DISCOUNT_PLAN + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_TYPE + " varchar(8) NOT NULL, " + COLUMN_ACC_HOLDER_ID + " integer(10) NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_ACC_HOLDER_ID + ") " +
            "REFERENCES " + AccountHolder.TABLE_ACCOUNT_HOLDER + "(" + AccountHolder.COLUMN_ID + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private String type;
    private int acc_holder_id;
    private AccountHolder accountHolder;
    private FixedDiscount fixedDiscount;
    private VariableDiscount variableDiscount;
    private FlexibleDiscount flexibleDiscount;

    public DiscountPlan(){}

    public DiscountPlan(int id, String type, int acc_holder_id)
    {
        this.id = id;
        this.type = type;
        this.acc_holder_id = acc_holder_id;
        accountHolder = new AccountHolderDAO().getById(acc_holder_id);
        flexibleDiscount = new FlexibleDiscountDAO().getByDiscountID(id);
        variableDiscount = new VariableDiscountDAO().getByDiscountId(id);
        fixedDiscount = new FixedDiscountDAO().getByDiscountId(id);
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

    public int getAcc_holder_id() {
        return acc_holder_id;
    }

    public void setAcc_holder_id(int acc_holder_id) {
        this.acc_holder_id = acc_holder_id;
    }

    public FixedDiscount getFixedDiscount() {
        return fixedDiscount;
    }

    public void setFixedDiscount(FixedDiscount fixedDiscount) {
        this.fixedDiscount = fixedDiscount;
    }

    public VariableDiscount getVariableDiscount() {
        return variableDiscount;
    }

    public void setVariableDiscount(VariableDiscount variableDiscount) {
        this.variableDiscount = variableDiscount;
    }

    public FlexibleDiscount getFlexibleDiscount() {
        return flexibleDiscount;
    }

    public void setFlexibleDiscount(FlexibleDiscount flexibleDiscount) {
        this.flexibleDiscount = flexibleDiscount;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}
