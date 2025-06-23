-- LESSON_01
CREATE DATABASE `CompanyDB`;
USE `CompanyDB`;

CREATE TABLE `Employees`(
	`EmployeeID` INT NOT NULL,
    `FirstName` VARCHAR(100),
    `LastName` VARCHAR(100) NOT NULL,
    `HireDate` DATE NOT NULL,
    `Salary` FLOAT NOT NULL,
    PRIMARY KEY (`EmployeeID`)
);

ALTER TABLE `Employees`
ADD `Department` VARCHAR(100);

ALTER TABLE `Employees`
MODIFY COLUMN `Salary` DECIMAL(10, 2);



