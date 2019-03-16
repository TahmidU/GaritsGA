package database.dao.contracts;

import database.domain.payment.OutstandingBalance;

import java.util.ArrayList;

public interface IOutstandingBalance
{
    ArrayList<OutstandingBalance> getAll();
    void save(OutstandingBalance outstandingBalance);
    void update(OutstandingBalance outstandingBalance);
    void delete(OutstandingBalance outstandingBalance);
    void delete(int id);
}
