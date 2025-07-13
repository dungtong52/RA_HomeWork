package dao;

import entity.Assignment;
import entity.Employee;

import java.util.List;

public interface AssignmentDAO {
    boolean assignEmployeeToProject(Assignment assignment);
    List<Assignment> getAllAssignments();
}
