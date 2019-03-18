package database.dao.contracts;

import database.domain.reminder.MOTReminder;

import java.util.ArrayList;

public interface IMOTReminder
{
    ArrayList<MOTReminder> getAll();
    MOTReminder getById(int id);
    ArrayList<MOTReminder> getByAccountHolderId(int id);
    void save(MOTReminder motReminder);
    void update(MOTReminder motReminder);
    void delete(MOTReminder motReminder);
    void delete(int id);
}
