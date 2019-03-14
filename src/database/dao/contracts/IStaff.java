package database.dao.contracts;

import database.domain.account.Staff;

import java.util.ArrayList;

public interface IStaff
{
    ArrayList<Staff> getAll();
    void save(Staff staff);
    void update(Staff staff);
    void delete(Staff staff);
    void delete(int id);
}
