package database.dao.contracts;

import database.domain.job.Task;

import java.util.ArrayList;

public interface ITask
{
    ArrayList<Task> getAll();
    Task getById(int id);
    ArrayList<Task> getByJobNum(int jobNum);
    void save(Task task);
    void update(Task task);
    void delete(Task task);
    void delete(int id);
}
