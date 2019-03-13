package database.domain.account;

import java.sql.Date;

public class AccountHolder extends CustomerAcc
{
    //  Table name and name of all columns.
    public static final String TABLE_ACCOUNT_HOLDER = "account_holder";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NI = "national_insurance_number";
    public static final String COLUMN_DATE_JOINED = "date_joined";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_NI = 2;
    public static final int INDEX_DATE_JOINED = 3;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_ACCOUNT_HOLDER = "CREATE TABLE " + TABLE_ACCOUNT_HOLDER + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_NI + " varchar(9) NOT NULL, " + COLUMN_DATE_JOINED + " date NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_NI + ") REFERENCES " + AccountHolder.TABLE_ACCOUNT_HOLDER + "(" + AccountHolder.COLUMN_NI + "));";

    //  Properties
    private int id;
    private String nationalInsurance;
    private Date dateJoined;

    public AccountHolder(){}

    public AccountHolder(int id, String nationalInsurance, Date dateJoined)
    {
        this.id = id;
        this.nationalInsurance = nationalInsurance;
        this.dateJoined = dateJoined;
    }


    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNationalInsurance() {
        return nationalInsurance;
    }

    @Override
    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
}
