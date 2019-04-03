package report;

import database.domain.account.CustomerAcc;
import database.domain.job.JobSheet;
import database.domain.payment.Invoice;
import report.invoice.InvoiceReminderHTML;
import report.invoice.InvoiceReminderPDF;
import report.invoice.InvoiceSheetHTML;
import report.invoice.InvoiceSheetPDF;
import report.job.CustomerCardHTML;
import report.job.CustomerCardPDF;
import report.job.JobSheetHTML;
import report.job.JobSheetPDF;
import report.parts.StockLedgerHTML;
import report.parts.StockLedgerPDF;
import util.Log;

public class Generate
{

    public static void generateInvoiceReminderHTML(Invoice invoice)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new InvoiceReminderHTML(invoice));
        t1.start();
    }

    public static void generateCustomerCardHTML(CustomerAcc customerAcc)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new CustomerCardHTML(customerAcc));
        t1.start();
    }

    public static void generateJobSheetHTML(JobSheet jobSheet)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new JobSheetHTML(jobSheet));
        t1.start();
    }

    public static void generateStockLedgerHTML()
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new StockLedgerHTML());
        t1.start();
    }

    public static void generateInvoiceHTML(Invoice invoice)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new InvoiceSheetHTML(invoice));
        t1.start();
    }

    public static void generateCustomerCardPDF(CustomerAcc customerAcc)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new CustomerCardPDF(customerAcc));
        t1.start();
    }

    public static void generateJobSheetPDF(JobSheet jobSheet)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new JobSheetPDF(jobSheet));
        t1.start();
    }

    public static void generateStockLedgerPDF()
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new StockLedgerPDF());
        t1.start();
    }

    public static void generateInvoiceReminderPDF(Invoice invoice)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new InvoiceReminderPDF(invoice));
        t1.start();
    }

    public static void generateInvoicePDF(Invoice invoice)
    {
        Log.write("Generating PDF...");
        Thread t1 = new Thread(new InvoiceSheetPDF(invoice));
        t1.start();
    }
}
