package database.domain.part;

import java.sql.Date;

public class PartOrder
{
    //  Table name and name of all columns.
    public static final String TABLE_PART_ORDER = "part_order";
    public static final String COLUMN_ORDER_NUM = "order_number";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_COMPANY_NAME = "company_name";
    public static final String COLUMN_ADDRESS_LINE = "address_line";
    public static final String COLUMN_DAY_DELIVERED = "day_delivered";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_FAX = "fax";

    //  Columns indexes.
    public static final int INDEX_ORDER_NUM = 1;
    public static final int INDEX_DESC = 2;
    public static final int INDEX_QUANTITY = 3;
    public static final int INDEX_PRICE = 4;
    public static final int INDEX_DATE = 5;
    public static final int INDEX_COMPANY_NAME = 6;
    public static final int INDEX_ADDRESS_LINE = 7;
    public static final int INDEX_DAY_DELIVERED = 8;
    public static final int INDEX_TELEPHONE = 9;
    public static final int INDEX_FAX = 10;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_PART_ORDER= "CREATE TABLE " + TABLE_PART_ORDER + " (" + COLUMN_ORDER_NUM + " varchar(30) NOT NULL, " +
            "" + COLUMN_DESC + " varchar(50) NOT NULL, " + COLUMN_QUANTITY + " integer(10) NOT NULL, " + COLUMN_PRICE + " float(10) NOT NULL, " +
            "" + COLUMN_DATE + " date NOT NULL, " + COLUMN_COMPANY_NAME + " varchar(50) NOT NULL, " + COLUMN_ADDRESS_LINE + " varchar(80) NOT NULL, " +
            "" + COLUMN_DAY_DELIVERED + " date, " + COLUMN_TELEPHONE + " varchar(11), " + COLUMN_FAX + " varchar(11), PRIMARY KEY (" + COLUMN_ORDER_NUM + "));";

    //  Properties
    private String orderNum;
    private String desc;
    private int quantity;
    private float price;
    private Date date;
    private String companyName;
    private String addressLine;
    private Date dayDelivered;
    private String telephone;
    private String fax;

    public PartOrder(){}

    public PartOrder(String orderNum, String desc, int quantity, float price, Date date, String companyName, String addressLine, Date dayDelivered, String telephone, String fax) {
        this.orderNum = orderNum;
        this.desc = desc;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.companyName = companyName;
        this.addressLine = addressLine;
        this.dayDelivered = dayDelivered;
        this.telephone = telephone;
        this.fax = fax;
    }

    //--------Getters and Setters--------
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Date getDayDelivered() {
        return dayDelivered;
    }

    public void setDayDelivered(Date dayDelivered) {
        this.dayDelivered = dayDelivered;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
