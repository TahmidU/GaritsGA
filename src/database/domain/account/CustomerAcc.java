package database.domain.account;

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
    public static final String CREATE_CUSTOMER_ACC_TABLE = "CREATE TABLE " + TABLE_CUSTOMER_ACCOUNT + " (" + COLUMN_NI + " varchar(9) NOT NULL," +
            " " + COLUMN_FIRST_NAME + " varchar(50) NOT NULL, " + COLUMN_LAST_NAME + " varchar(50) NOT NULL, " + COLUMN_ADDRESS + " varchar(100) NOT NULL," +
            " " + COLUMN_POSTCODE + " varchar(8) NOT NULL, " + COLUMN_EMAIL + " varchar(255) NOT NULL, " + COLUMN_PHONE + " integer(11), PRIMARY KEY (" + COLUMN_NI + "));";

    //  Properties
    private String nationalInsurance;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String postCode;
    private String email;
    private int phoneNumber;

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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
