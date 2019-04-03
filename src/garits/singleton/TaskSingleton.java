package garits.singleton;

import database.domain.job.Task;

public class TaskSingleton
{
    private static TaskSingleton instance = null;
    private static Task task;

    public static TaskSingleton getInstance()
    {
        if(instance == null)
            instance = new TaskSingleton();

        return instance;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        TaskSingleton.task = task;
    }
}