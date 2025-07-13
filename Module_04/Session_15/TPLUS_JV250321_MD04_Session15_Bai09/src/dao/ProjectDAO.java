package dao;

import entity.Project;

import java.util.List;

public interface ProjectDAO {
    boolean addProject(Project project);
    Project findByName(String name);
    Project findById(int id);
    List<Project> getAllProjects();
}
