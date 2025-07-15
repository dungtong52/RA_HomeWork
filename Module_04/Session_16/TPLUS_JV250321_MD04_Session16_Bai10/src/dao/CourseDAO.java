package dao;

import entity.Course;

public interface CourseDAO {
    boolean addCourse(Course course);

    boolean existsByTitle(String title);
}
