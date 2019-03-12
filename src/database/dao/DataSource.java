package database.dao;

import database.DBConnectivity;
import database.domain.account.AccountHolder;
import database.domain.account.CurrentUser;
import database.domain.account.CustomerAcc;
import database.domain.account.Staff;
import database.domain.discount.*;
import database.domain.job.*;
import database.domain.part.PartOrder;
import database.domain.part.StockPart;
import database.domain.part.StockPartOrder;
import database.domain.payment.Invoice;
import database.domain.payment.OutstandingBalance;
import database.domain.reminder.InvoiceReminder;
import database.domain.reminder.MOTReminder;
import util.Log;

import java.sql.Connection;

public class DataSource
{
    public static final String DB_NAME = "GaritsGA.db";
    public static final String DB_DRIVER =  "jdbc:sqlite:"+DB_NAME;

    private DBConnectivity connectivity;
    private Connection conn;

    public DataSource()
    {
        connectivity = new DBConnectivity();
    }

    public boolean createDB()
    {
        conn = connectivity.connect(DB_DRIVER);

        if(connectivity.checkExists(DB_NAME))
        {
            connectivity.addToBatch(Staff.CREATE_TABLE_STAFF, conn);
            connectivity.addToBatch(CurrentUser.CREATE_TABLE_LOGIN, null);
            connectivity.addToBatch(CustomerAcc.CREATE_TABLE_CUSTOMER_ACC, null);
            connectivity.addToBatch(AccountHolder.CREATE_TABLE_ACCOUNT_HOLDER, null);
            connectivity.addToBatch(Invoice.CREATE_TABLE_INVOICE, null);
            connectivity.addToBatch(InvoiceReminder.CREATE_TABLE_INVOICE_REMINDER, null);
            connectivity.addToBatch(PartOrder.CREATE_TABLE_PART_ORDER, null);
            connectivity.addToBatch(Vehicle.CREATE_TABLE_VEHICLE, null);
            connectivity.addToBatch(JobSheet.CREATE_TABLE_JOB_SHEET, null);
            connectivity.addToBatch(StockPartOrder.CREATE_TABLE_STOCK_PART_ORDER, null);
            connectivity.addToBatch(OutstandingBalance.CREATE_TABLE_OUTSTANDING_BALANCE, null);
            connectivity.addToBatch(Task.CREATE_TABLE_TASK, null);
            connectivity.addToBatch(StockPart.CREATE_TABLE_STOCK_PART, null);
            connectivity.addToBatch(Booking.CREATE_TABLE_BOOKING, null);
            connectivity.addToBatch(MOTReminder.CREATE_TABLE_MOT_REMINDER, null);
            connectivity.addToBatch(DiscountPlan.CREATE_TABLE_DISCOUNT_PLAN, null);
            connectivity.addToBatch(FixedDiscount.CREATE_TABLE_FIXED_DISCOUNT, null);
            connectivity.addToBatch(VariableDiscount.CREATE_TABLE_VARIABLE_DISCOUNT, null);
            connectivity.addToBatch(FlexibleDiscount.CREATE_TABLE_FLEXIBLE_DISCOUNT, null);
            connectivity.addToBatch(FlexDiscountBand.CREATE_TABLE_FLEX_DISCOUNT_BAND, null);
            connectivity.addToBatch(VariableTask.CREATE_TABLE_VARIABLE_TASK, null);
            connectivity.addToBatch(FlexDiscountBandFlexibleDiscount.CREATE_TABLE_FLEX_DISCOUNT_BAND, null);

            if(connectivity.writeBatch(conn))
            {
                Log.write("DataSource: Database successfully created.");
            }else
                {
                    Log.write("DataSource: Error while creating the database.");
                }
            connectivity.closeConnection(conn);
            return true;
        }

        connectivity.closeConnection(conn);
        return false;
    }


}
