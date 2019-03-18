package database.dao.contracts;

import database.domain.reminder.InvoiceReminder;

import java.util.ArrayList;

public interface IInvoiceReminder
{
    ArrayList<InvoiceReminder> getAll();
    InvoiceReminder getById(int id);
    ArrayList<InvoiceReminder> getByInvoiceId(int invoiceId);
    ArrayList<InvoiceReminder> getByAccountHolderId(int accountHolderId);
    void save(InvoiceReminder invoiceReminder);
    void update(InvoiceReminder invoiceReminder);
    void delete(InvoiceReminder invoiceReminder);
    void delete(int id);
}
