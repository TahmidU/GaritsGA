import database.dao.DBHelper;
import database.dao.account.AccountHolderDAO;
import database.dao.account.CustomerAccDAO;
import database.dao.account.LoginDetailDAO;
import database.dao.account.StaffDAO;
import database.dao.contracts.*;
import database.dao.discount.*;
import database.dao.job.*;
import database.dao.part.PartOrderDAO;
import database.dao.part.StockPartDAO;
import database.dao.part.StockPartOrderDAO;
import database.dao.payment.InvoiceDAO;
import database.dao.payment.OutstandingBalanceDAO;
import database.dao.reminder.InvoiceReminderDAO;
import database.dao.reminder.MOTReminderDAO;
import database.domain.account.AccountHolder;
import database.domain.account.CustomerAcc;
import database.domain.account.LoginDetail;
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
import util.DBDateHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] arg)
    {

        //DBHelper dbHelper = new DBHelper();
        //dbHelper.createDB();

        //Date in SQLite only accepts this format. Can be reformatted to UK version if we want.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

/*
        Staff s = new Staff(1, "Tahmid", "Uddin", "07464081609", "tahmiduddin@hotmail.co.uk", "Admin");
        LoginDetail ld = new LoginDetail("TahmidU", 1, "j");
        CustomerAcc ca = new CustomerAcc("AAAA2D", "Kawthar", "Uddin", "10, Radley Rd", "N17 2AR", "KU@gmail.com", "07128372185");
        AccountHolder ah = new AccountHolder(1,"AAAA2D", Date.valueOf("2019-02-10"));
        OutstandingBalance ob = new OutstandingBalance(-1, 1, 1, DBDateHelper.parseCurrentDate());
        MOTReminder mot = new MOTReminder(-1, 1, Date.valueOf("2019-01-05"));
        Vehicle v = new Vehicle("AAAA","AAAA2D","chicken","coop", "2SAD", "AS2DA","PINK");
        Booking b = new Booking(1,"REPAIR",DBDateHelper.parseCurrentDate(), "AAAA");
        JobSheet js = new JobSheet(1,1,"AAAA",1,"Arrow to the knee",DBDateHelper.parseCurrentDate(), "happening", DBDateHelper.parseCurrentDate(),DBDateHelper.parseCurrentDate());
        Invoice i = new Invoice(1, "AAAA2D", DBDateHelper.parseCurrentDate(), 23000.15f, 1);
        InvoiceReminder ir = new InvoiceReminder(1,1,1,"second", DBDateHelper.parseCurrentDate());
        DiscountPlan dp = new DiscountPlan(1,"Fix","AAAA2D");
        FixedDiscount fp = new FixedDiscount(1,1,35f);
        FlexibleDiscount fd = new FlexibleDiscount(1,1);
        FlexDiscountBand fsb = new FlexDiscountBand("A",25f);
        FlexDiscountBandFlexibleDiscount fdbfd = new FlexDiscountBandFlexibleDiscount(1,"A");
        VariableDiscount vd = new VariableDiscount(1,1);
        StockPart sp = new StockPart(1,"Engine",2300f,30,"Toyota", "Coop","2018","2020");
        Task t = new Task(1,1,1,"blah",200,10,DBDateHelper.parseCurrentDate());
        PartOrder po = new PartOrder("aaa","stuff",50,130000f,DBDateHelper.parseCurrentDate(),"Toyo","10, stuff", DBDateHelper.parseCurrentDate(), "0727277","0737377");
        StockPartOrder spo = new StockPartOrder("aaa",1);
        VariableTask vt = new VariableTask(1,1,20f);


        IStaff test = new StaffDAO();
        ILoginDetail test2 = new LoginDetailDAO();
        ICustomerAcc test3 = new CustomerAccDAO();
        IAccountHolder test4 = new AccountHolderDAO();
        IOutstandingBalance test5 = new OutstandingBalanceDAO();
        IMOTReminder test6 = new MOTReminderDAO();
        IVehicle test7 = new VehicleDAO();
        IBooking test8 = new BookingDAO();
        IJobSheet test9 = new JobSheetDAO();
        IInvoice test10 = new InvoiceDAO();
        IInvoiceReminder test11 = new InvoiceReminderDAO();
        IDiscountPlan test12 = new DiscountPlanDAO();
        IFixedDiscount test13 = new FixedDiscountDAO();
        IFlexibleDiscount test14 = new FlexibleDiscountDAO();
        IFlexDiscountBand test15 = new FlexDiscountBandDAO();
        IFlexDiscountBandFlexibleDiscount test16 = new FlexDiscountBandFlexibleDiscountDAO();
        IVariableDiscount test17 = new VariableDiscountDAO();
        IPartOrder test18 = new PartOrderDAO();
        IStockPart test19 = new StockPartDAO();
        IStockPartOrder test20 = new StockPartOrderDAO();
        ITask test21 = new TaskDAO();
        IVariableTask test22 = new VariableTaskDAO();



        test.save(s);
        test2.save(ld);
        test3.save(ca);
        test4.save(ah);
        test5.save(ob);
        test6.save(mot);
        test7.save(v);
        test8.save(b);
        test9.save(js);
        test8.save(b);
        test10.save(i);
        test11.save(ir);
        test12.save(dp);
        test13.save(fp);
        test14.save(fd);
        test15.save(fsb);
        test16.save(fdbfd);
        test17.save(vd);
        test18.save(po);
        test19.save(sp);
        test20.save(spo);
        test21.save(t);
        test22.save(vt);
*/
        //test20.save(spo);

/*
        Booking b = new Booking(1,"MOT",DBDateHelper.parseCurrentDate(), "AAAA");
        IBooking test5 = new BookingDAO();
        test5.update(b);
*/
        /*
        ArrayList<StockPartOrder> spos;
        spos = test20.getAll();

        for(StockPartOrder n: spos)
        {
            System.out.println(n.getPartOrderNum()+"\t"+n.getStockPartId());
        }
        */

        DBHelper dbHelper = new DBHelper();
        dbHelper.restoreDB("D:\\Uni Projects\\Year 2\\GaritsGA\\backup\\18032019\\220800backupGA.db");

    }
}
