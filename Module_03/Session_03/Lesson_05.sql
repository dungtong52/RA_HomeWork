DROP DATABASE `session03_db`;
CREATE DATABASE `session03_db`;
USE `session03_db`;

CREATE TABLE `students`(
	`studentID` INT,
    `studentName` VARCHAR(50) NOT NULL,
    `major` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`studentID`)
);

CREATE TABLE `courses`(
	`courseID` INT,
    `courseName` VARCHAR(100) NOT NULL,
    `instructor` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`courseID`)
);

-- INSERT 
INSERT INTO `students`
VALUES
(1, 'Nguyễn Thị A', 'Khoa học Máy tính'),
(2, 'Trần Thị B', 'Quản trị Kinh doanh'),
(3, 'Phạm Thị C', 'Kỹ thuật Điện tử'),
(4, 'Lê Thị D', 'Xây dựng dân dụng');

INSERT INTO `courses`
VALUES
(1, 'Pháp luật', 'Mrs.Thảo'),
(2, 'Sinh học', 'Mrs.Hiền'),
(3, 'Thể dục', 'Mrs.Hạnh');
-- UPDATE
UPDATE `courses` SET `courseName` = 'Advanced Mathematics' WHERE `courseID` = 2;
UPDATE `students` SET `major` = 'Engineering' WHERE `studentID` = 3;
-- DELETE
DELETE FROM `students` WHERE `studentID` = 1;
DELETE FROM `courses` WHERE `courseID` = 3;
-- TRUY VẤN
SELECT * FROM `students`;
SELECT * FROM `courses`;