package business.imp;

import business.CourseBusiness;
import dao.CourseDAO;
import dao.imp.CourseDAOImp;
import entity.Course;

import java.util.List;
import java.util.Scanner;

public class CourseBusinessImp implements CourseBusiness {
    private final CourseDAO courseDAO = new CourseDAOImp();

    @Override
    public void addCourse(Scanner scanner) {
        Course course = new Course();
        course.inputData(scanner);

        if (courseDAO.findByTitle(course.getTitle()) != null) {
            System.err.println("Khóa học đã tồn tại.");
            return;
        }

        boolean result = courseDAO.addCourse(course);
        if (result) {
            System.out.println("Thêm khóa học thành công.");
        } else {
            System.err.println("Thêm khóa học thất bại.");
        }
    }

    @Override
    public void showAllCourses() {
        List<Course> list = courseDAO.getAllCourses();
        if (list.isEmpty()) {
            System.out.println("Chưa có khóa học nào.");
            return;
        }
        list.forEach(System.out::println);
    }
}
