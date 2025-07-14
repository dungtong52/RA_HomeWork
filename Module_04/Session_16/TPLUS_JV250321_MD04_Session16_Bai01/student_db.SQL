CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age INT
);

DELIMITER $$

CREATE PROCEDURE add_students (
    IN p_name VARCHAR(255),
    IN p_age INT
)
BEGIN
    INSERT INTO students (name, age)
    VALUES (p_name, p_age);
END $$

CREATE PROCEDURE update_student (
    IN p_id INT,
    IN p_name VARCHAR(255),
    IN p_age INT
)
BEGIN
    UPDATE students
    SET name = p_name,
        age = p_age
    WHERE id = p_id;
END $$

CREATE PROCEDURE delete_students_by_age (
    IN p_age_threshold INT
)
BEGIN
    DELETE FROM students
    WHERE age < p_age_threshold;
END $$

DELIMITER ;
