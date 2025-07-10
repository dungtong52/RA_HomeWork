CREATE DATABASE `student_db`;
USE `student_db`;

CREATE TABLE `students`(
	`student_id` INT AUTO_INCREMENT,
    `full_name` VARCHAR(100) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `email` VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY (`student_id`)
);

DELIMITER //
CREATE PROCEDURE add_student(
	IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
	INSERT INTO students(`full_name`, `date_of_birth`, `email`)
    VALUES (in_full_name, in_date_of_birth, in_email);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_students()
BEGIN
	SELECT * FROM students;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_student(
	IN in_student_id INT,
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
	UPDATE students
    SET `full_name` = in_full_name,
		`date_of_birth` = in_date_of_birth,
        `email` = in_email
	WHERE `student_id` = in_student_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE find_student_by_id(
	IN in_student_id INT
)
BEGIN
	SELECT * FROM students
    WHERE `student_id` = in_student_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_student(
	IN in_student_id INT
)
BEGIN
	DELETE FROM students
    WHERE `student_id` = in_student_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_student(
	IN in_name VARCHAR(100)
)
BEGIN
	SELECT * FROM students
    WHERE `full_name`LIKE CONCAT("%", in_name, "%");
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE check_email_exist(
	IN in_email VARCHAR(100)
)
BEGIN
	SELECT COUNT(*) AS count_email
    FROM students
    WHERE `email` = in_email;
END //
DELIMITER ;

CALL add_student('Nguyễn Văn A', '2007-05-20', 'vana@example.com');

CALL get_all_students();

CALL find_student_by_id(1);

CALL update_student(1, 'Nguyễn Văn B', '2001-06-10', 'vanb@example.com');

CALL delete_student(1);

truncate table students;