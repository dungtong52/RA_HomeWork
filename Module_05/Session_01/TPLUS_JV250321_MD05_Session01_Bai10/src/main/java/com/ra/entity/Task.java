package com.ra.entity;

import static com.ra.service.TaskService.taskList;

public class Task {
    private int id;
    private String taskName;
    private boolean status;

    public Task() {
    }

    public Task(String taskName, boolean status) {
        this.id = getTaskId();
        this.taskName = taskName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTaskId() {
        int max = 0;
        for (Task task : taskList) {
            if (task.getId() > max) {
                max = task.getId();
            }
        }
        return max + 1;
    }
}
