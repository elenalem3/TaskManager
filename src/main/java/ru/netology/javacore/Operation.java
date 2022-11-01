package ru.netology.javacore;

public class Operation {
    protected TaskType type;
    protected String task;

    public Operation(TaskType type, String task) {
        this.type = type;
        this.task = task;
    }

    public TaskType getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}
