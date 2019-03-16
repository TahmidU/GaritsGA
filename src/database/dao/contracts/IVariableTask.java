package database.dao.contracts;

import database.domain.job.VariableTask;

import java.util.ArrayList;

public interface IVariableTask
{
    ArrayList<VariableTask> getAll();
    void save(VariableTask variableTask);
    void update(VariableTask variableTask);
}
