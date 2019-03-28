package database.domain.job;

import database.dao.DBHelper;
import database.dao.job.VariableTaskDAO;
import database.dao.part.StockPartDAO;
import database.domain.part.StockPart;

import java.sql.Date;
import java.util.ArrayList;

public class Task
{
    //  Table name and name of all columns.
    public static final String TABLE_TASK = "task";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STOCK_PART_ID = "stock_part_id";
    public static final String COLUMN_JOB_NUM = "job_no";
    public static final String COLUMN_TASK_DESC = "task_desc";
    public static final String COLUMN_EST_DURATION = "est_duration";
    public static final String COLUMN_PARTS_QTY = "parts_qty";
    public static final String COLUMN_DATE_TASK_COMPLETE = "date_task_complete";

    //  Columns indexes.
    public static final int INDEX_ID = 1;
    public static final int INDEX_STOCK_PART_ID = 2;
    public static final int INDEX_JOB_NUM = 3;
    public static final int INDEX_TASK_DESC  = 4;
    public static final int INDEX_EST_DURATION = 5;
    public static final int INDEX_PARTS_QTY = 6;
    public static final int INDEX_DATE_TASK_COMPLETE = 7;

    //  Create Table SQL Statement.
    public static final String CREATE_TABLE_TASK = "CREATE TABLE " + TABLE_TASK + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY , " +
            "" + COLUMN_STOCK_PART_ID + " integer(10) NOT NULL, " + COLUMN_JOB_NUM + " integer(10) NOT NULL, " + COLUMN_TASK_DESC + " varchar(255) NOT NULL, " +
            "" + COLUMN_EST_DURATION + " integer(10) NOT NULL, " + COLUMN_PARTS_QTY + " integer(10) NOT NULL, " + COLUMN_DATE_TASK_COMPLETE + " date NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_STOCK_PART_ID + ") REFERENCES " + StockPart.TABLE_STOCK_PART + "(" + StockPart.COLUMN_PART_ID + "), FOREIGN KEY(" + COLUMN_JOB_NUM + ") " +
            "REFERENCES " + JobSheet.TABLE_JOB_SHEET + "(" + JobSheet.COLUMN_JOB_NUM + ")"+ DBHelper.ON_UPDATE+");";

    //  Properties
    private int id;
    private int stockPartId;
    private int jobNum;
    private String taskDesc;
    private int estDuration;
    private int partQty;
    private Date dateTaskComplete;
    private StockPart stockPart;
    private ArrayList<VariableTask> variableTasks;

    public Task(){}

    public Task(int id, int stockPartId, int jobNum, String taskDesc, int estDuration, int partQty, Date dateTaskComplete) {
        this.id = id;
        this.stockPartId = stockPartId;
        this.jobNum = jobNum;
        this.taskDesc = taskDesc;
        this.estDuration = estDuration;
        this.partQty = partQty;
        this.dateTaskComplete = dateTaskComplete;
        stockPart = new StockPartDAO().getByStockPart(stockPartId);
        variableTasks = new VariableTaskDAO().getByTaskId(id);
    }

    //--------Getters and Setters--------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockPartId() {
        return stockPartId;
    }

    public void setStockPartId(int stockPartId) {
        this.stockPartId = stockPartId;
    }

    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getEstDuration() {
        return estDuration;
    }

    public void setEstDuration(int estDuration) {
        this.estDuration = estDuration;
    }

    public int getPartQty() {
        return partQty;
    }

    public void setPartQty(int partQty) {
        this.partQty = partQty;
    }

    public Date getDateTaskComplete() {
        return dateTaskComplete;
    }

    public void setDateTaskComplete(Date dateTaskComplete) {
        this.dateTaskComplete = dateTaskComplete;
    }

    public StockPart getStockPart() {
        return stockPart;
    }

    public void setStockPart(StockPart stockPart) {
        this.stockPart = stockPart;
    }

    public ArrayList<VariableTask> getVariableTasks() {
        return variableTasks;
    }

    public void setVariableTasks(ArrayList<VariableTask> variableTasks) {
        this.variableTasks = variableTasks;
    }
}
