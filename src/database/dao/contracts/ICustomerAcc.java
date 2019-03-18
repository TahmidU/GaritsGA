package database.dao.contracts;

import database.domain.account.CustomerAcc;

import java.util.ArrayList;

public interface ICustomerAcc
{
    ArrayList<CustomerAcc> getAll();
    CustomerAcc getByNI(String nI);
    void save(CustomerAcc customerAcc);
    void update(CustomerAcc customerAcc);
    void delete(CustomerAcc customerAcc);
    void delete(String nationalInsuranceNumber);
}
