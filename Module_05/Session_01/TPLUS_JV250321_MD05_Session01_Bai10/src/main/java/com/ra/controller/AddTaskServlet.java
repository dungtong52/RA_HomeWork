package com.ra.controller;

import com.ra.entity.Task;
import com.ra.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private final TaskService taskService = new TaskService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("taskName");

        Task task = new Task(taskName, false);
        taskService.addTask(task);

        resp.sendRedirect("TaskListServlet");
    }
}
