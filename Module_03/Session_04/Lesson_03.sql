CREATE DATABASE `session04_db`;
USE `session04_db`;

-- CREATE
CREATE TABLE `employeeSalaries`(
	`employee_id` INT,
    `department_id` INT NOT NULL,
    `salary` DECIMAL(10, 2),
    PRIMARY KEY (`employee_id`)
);

--  INSERT
INSERT INTO `employeeSalaries`
VALUES
(1, 101, 15000.00),
(2, 102, 13000.00),
(3, 103, 17000.00),
(4, 101, 16000.00);

-- QUERY
SELECT `department_id`, SUM(`salary`), AVG(`salary`)
FROM `employeeSalaries`
GROUP BY `department_id`;