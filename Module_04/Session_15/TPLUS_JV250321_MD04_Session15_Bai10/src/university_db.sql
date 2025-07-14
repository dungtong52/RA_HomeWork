CREATE DATABASE university_db;
USE university_db;

-- Bảng Student
CREATE TABLE Student
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(255)        NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Bảng Course
CREATE TABLE Course
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    title   VARCHAR(255) NOT NULL,
    credits INT          NOT NULL
);

-- Bảng Enrollment
CREATE TABLE Enrollment
(
    student_id INT,
    course_id  INT,
    grade      DECIMAL(5, 2),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id)
        REFERENCES Student (id),
    FOREIGN KEY (course_id)
        REFERENCES Course (id)
);

# Student Procedures
DELIMITER $$
CREATE PROCEDURE sp_add_student(
    IN p_name VARCHAR(255),
    IN p_email VARCHAR(255)
)
BEGIN
    INSERT INTO Student(name, email)
    VALUES (p_name, p_email);
END$$

CREATE PROCEDURE sp_find_student_by_email(IN p_email VARCHAR(255))
BEGIN
    SELECT * FROM Student WHERE email = p_email;
END$$

CREATE PROCEDURE sp_find_student_by_id(IN p_id INT)
BEGIN
    SELECT * FROM Student WHERE id = p_id;
END$$

CREATE PROCEDURE sp_get_all_students()
BEGIN
    SELECT * FROM Student;
END$$
DELIMITER ;

# Course Procedures

DELIMITER $$
CREATE PROCEDURE sp_add_course(
    IN p_title VARCHAR(255),
    IN p_credits INT
)
BEGIN
    INSERT INTO Course(title, credits) VALUES (p_title, p_credits);
END$$

CREATE PROCEDURE sp_find_course_by_title(IN p_title VARCHAR(255))
BEGIN
    SELECT * FROM Course WHERE title = p_title;
END$$

CREATE PROCEDURE sp_find_course_by_id(IN p_id INT)
BEGIN
    SELECT * FROM Course WHERE id = p_id;
END$$

CREATE PROCEDURE sp_get_all_courses()
BEGIN
    SELECT * FROM Course;
END$$
DELIMITER ;

# Enrollment Procedures
DELIMITER $$
CREATE PROCEDURE sp_enroll_student(
    IN p_student_id INT,
    IN p_course_id INT
)
BEGIN
    INSERT INTO Enrollment(student_id, course_id)
    VALUES (p_student_id, p_course_id);
END$$

CREATE PROCEDURE sp_get_all_enrollments()
BEGIN
    SELECT s.name AS student_name, c.title AS course_title, e.grade
    FROM Enrollment e
             JOIN Student s ON e.student_id = s.id
             JOIN Course c ON e.course_id = c.id;
END$$

CREATE PROCEDURE sp_update_grade(
    IN p_student_id INT,
    IN p_course_id INT,
    IN p_grade DECIMAL(5, 2)
)
BEGIN
    UPDATE Enrollment
    SET grade = p_grade
    WHERE student_id = p_student_id
      AND course_id = p_course_id;
END$$
DELIMITER ;
