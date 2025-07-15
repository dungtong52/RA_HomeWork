package business.imp;

import business.CourseBusiness;
import dao.CourseDAO;
import dao.imp.CourseDAOImp;
import entity.Course;

public class CourseBusinessImp implements CourseBusiness {
    private final CourseDAO courseDAO = new CourseDAOImp();
    @Override
    public boolean addCourse(Course course) {
        if (courseDAO.existsByTitle(course.getTitle())) {
            System.out.println("Khóa học đã tồn tại!");
            return false;
        }
        return courseDAO.addCourse(course);
    }
}
