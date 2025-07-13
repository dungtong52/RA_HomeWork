package dao;

import entity.Course;

import java.util.List;

public interface CourseDAO {
    boolean addCourse(Course course);
    Course findByTitle(String title);
    Course findById(int id);
    List<Course> getAllCourses();
}
