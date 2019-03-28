package database.domain.account;

import database.dao.account.AccountHolderDAO;
import database.dao.job.VehicleDAO;
import database.dao.payment.InvoiceDAO;
import database.domain.job.Vehicle;
import database.domain.payment.Invoice;

import java.util.ArrayList;

public class CustomerAcc
{

    //  Table name and name of all columns.
    public static final String TABLE_CUSTOMER_ACCOUNT = "customer_account";
    public static final String COLUMN_NI = "national_insurance_number";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_ADDRESS = "address_line";
    public static final String COLUMN_POSTCODE = "postcode";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone_number";

    //  Columns indexes.
    public static final int INDEX_NI = 1;
    public static final int INDEX_FIRST_NAME = 2;
    public static final int INDEX_LAST_NAME = 3;
    public static final int INDEX_ADDRESS = 4;
    public static final int INDEX_POSTCODE = 5;
    public static final int INDEX_EMAIL = 6;
    public static final int INDEX_PHONE = 7;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_CUSTOMER_ACC = "CREATE TABLE " + TABLE_CUSTOMER_ACCOUNT + " (" + COLUMN_NI + " varchar(9) NOT NULL," +
            " " + COLUMN_FIRST_NAME + " varchar(50) NOT NULL, " + COLUMN_LAST_NAME + " varchar(50) NOT NULL, " + COLUMN_ADDRESS + " varchar(100) NOT NULL," +
            " " + COLUMN_POSTCODE + " varchar(8) NOT NULL, " + COLUMN_EMAIL + " varchar(255) NOT NULL, " + COLUMN_PHONE + " varchar(11), PRIMARY KEY (" + COLUMN_NI + "));";


    //  Properties
    private String nationalInsurance;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String postCode;
    private String email;
    private String phoneNumber;
    private ArrayList<Invoice> invoices;
    private ArrayList<Vehicle> vehicles;
    private AccountHolder accountHolder;

    public CustomerAcc(){}

    public CustomerAcc(String nationalInsurance, String firstName, String lastName, String addressLine, String postCode,
                       String email, String phoneNumber)
    {
        this.nationalInsurance = nationalInsurance;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine = addressLine;
        this.postCode = postCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        invoices = new InvoiceDAO().getByNI(nationalInsurance);
        vehicles = new VehicleDAO().getByNI(nationalInsurance);
        accountHolder = new AccountHolderDAO().getByNI(nationalInsurance);
    }

    //--------Getters and Setters--------
    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}
