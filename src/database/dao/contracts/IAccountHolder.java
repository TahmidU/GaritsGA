package database.dao.contracts;

import database.domain.account.AccountHolder;

import java.util.ArrayList;

public interface IAccountHolder
{
    ArrayList<AccountHolder> getAll();
    AccountHolder getById(int id);
    AccountHolder getByNI(String nI);
    void save(AccountHolder accountHolder);
    void update(AccountHolder accountHolder);
    void delete(AccountHolder accountHolder);
    void delete(int id);
}
