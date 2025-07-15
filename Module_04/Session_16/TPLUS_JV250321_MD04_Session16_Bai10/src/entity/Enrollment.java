package entity;

public class Enrollment {
    private int studentId;
    private int courseId;
    private double grade;

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{studentId=" + studentId + ", courseId=" + courseId + ", grade=" + grade + "}";
    }
}
