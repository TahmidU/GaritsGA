package database.dao.contracts;

import database.domain.backup.BackUp;

import java.util.ArrayList;

public interface IBackUp
{
    ArrayList<BackUp> getAll();
    BackUp getById(String dir);
    void save(BackUp backUp);
    void update(BackUp backUp);
    void delete(BackUp backUp);
    void delete(String dir);
}
