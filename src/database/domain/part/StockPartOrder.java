package database.domain.part;

import database.dao.DBHelper;
import database.dao.part.PartOrderDAO;
import database.dao.part.StockPartDAO;

public class StockPartOrder
{
    //  Table name and name of all columns.
    public static final String TABLE_STOCK_PART_ORDER = "stock_part_order";
    public static final String COLUMN_PART_ORDER_NUM = "part_order_number";
    public static final String COLUMN_STOCK_PART_ID = "stock_part_id";


    //  Columns indexes.
    public static final int INDEX_PART_ORDER_NUM = 1;
    public static final int INDEX_STOCK_PART_ID = 2;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_STOCK_PART_ORDER = "CREATE TABLE " + TABLE_STOCK_PART_ORDER + " (" + COLUMN_PART_ORDER_NUM + " varchar(30) NOT NULL, " +
            "" + COLUMN_STOCK_PART_ID + " integer(10) NOT NULL, PRIMARY KEY (" + COLUMN_PART_ORDER_NUM + ", " + COLUMN_STOCK_PART_ID + "), " +
            "FOREIGN KEY(" + COLUMN_PART_ORDER_NUM + ") REFERENCES " + PartOrder.TABLE_PART_ORDER + "(" + PartOrder.COLUMN_ORDER_NUM + "), " +
            "FOREIGN KEY(" + COLUMN_STOCK_PART_ID + ") REFERENCES " + StockPart.TABLE_STOCK_PART + "(" + StockPart.COLUMN_PART_ID + ")" + DBHelper.ON_UPDATE + DBHelper.ON_DELETE+");";

    //  Properties
    private String partOrderNum;
    private int stockPartId;
    private StockPart stockPart;
    private PartOrder partOrder;

    public StockPartOrder(){}

    public StockPartOrder(String partOrderNum, int stockPartId) {
        this.partOrderNum = partOrderNum;
        this.stockPartId = stockPartId;


    }

    //--------Getters and Setters--------

    public String getPartOrderNum() {
        return partOrderNum;
    }

    public void setPartOrderNum(String partOrderNum) {
        this.partOrderNum = partOrderNum;
    }

    public int getStockPartId() {
        return stockPartId;
    }

    public void setStockPartId(int stockPartId) {
        this.stockPartId = stockPartId;
    }

    public StockPart getStockPart() {
        stockPart = new StockPartDAO().getByStockPart(stockPartId);
        return stockPart;
    }

    public void setStockPart(StockPart stockPart) {
        this.stockPart = stockPart;
    }

    public PartOrder getPartOrder() {
        partOrder = new PartOrderDAO().getByOrderNum(partOrderNum);
        return partOrder;
    }

    public void setPartOrder(PartOrder partOrder) {
        this.partOrder = partOrder;
    }
}
