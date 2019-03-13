package database.dao;

import java.util.ArrayList;

public interface IDao<T>
{
    ArrayList<T> getAll();
    T getByPK(T PK);
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
}
