package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    protected List<String> taskList = new ArrayList<>();
    protected final int MaxSize = 7;


    public void addTask(String task) {
        if (taskList.size() < MaxSize) {
            if (!taskList.contains(task)) {
                taskList.add(task);
            }
        }
    }

    public void removeTask(String task) {
        taskList.remove(task);
    }

    public String getAllTasks() {
        return taskList
                .stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
