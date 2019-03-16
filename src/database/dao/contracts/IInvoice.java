package database.dao.contracts;

import database.domain.payment.Invoice;

import java.util.ArrayList;

public interface IInvoice
{
    ArrayList<Invoice> getAll();
    void save(Invoice invoice);
    void update(Invoice invoice);
    void delete(Invoice invoice);
    void delete(int id);
}
