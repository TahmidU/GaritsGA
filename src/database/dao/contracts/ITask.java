package database.dao.contracts;

import database.domain.job.Task;

import java.util.ArrayList;

public interface ITask
{
    ArrayList<Task> getAll();
    void save(Task task);
    void update(Task task);
    void delete(Task task);
    void delete(int id);
}
