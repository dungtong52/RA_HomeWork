DROP DATABASE `session03_db`;
CREATE DATABASE `session03_db`;
USE `session03_db`;

CREATE TABLE `Students`(
	`studentID` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `major` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`studentID`)
);

INSERT INTO `Students`
VALUES
(1, 'Alice', 20, 'Computer Science'),
(2, 'Bob', 22, 'Mathematics'),
(3, 'Charlie', 21, 'Physics');

DELETE FROM `Students`
WHERE `StudentID` = 1;
