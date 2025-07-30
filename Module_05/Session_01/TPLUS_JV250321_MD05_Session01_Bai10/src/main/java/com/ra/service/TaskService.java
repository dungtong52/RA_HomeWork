package com.ra.service;

import com.ra.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public static List<Task> taskList = new ArrayList<>();

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void updateTaskStatus(int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                task.setStatus(!task.isStatus());
                break;
            }
        }
    }


}
