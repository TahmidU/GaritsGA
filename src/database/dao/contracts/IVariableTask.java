package database.dao.contracts;

import database.domain.job.VariableTask;

import java.util.ArrayList;

public interface IVariableTask
{
    ArrayList<VariableTask> getAll();
    ArrayList<VariableTask> getByTaskId(int taskId);
    ArrayList<VariableTask> getByVariableDiscountId(int variableDiscountId);
    void save(VariableTask variableTask);
    void update(VariableTask variableTask);
}
