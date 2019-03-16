package database.dao.contracts;

import database.domain.account.AccountHolder;

import java.util.ArrayList;

public interface IAccountHolder
{
    ArrayList<AccountHolder> getAll();
    void save(AccountHolder accountHolder);
    void update(AccountHolder accountHolder);
    void delete(AccountHolder accountHolder);
    void delete(int id);
}
