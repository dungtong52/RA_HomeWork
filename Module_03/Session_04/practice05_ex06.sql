CREATE DATABASE `exercise06_db`;
USE `exercise06_db`;

--
-- CREATE
--
CREATE TABLE `employees`(
	`id` INT,
    `name` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `salary` FLOAT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `departments`(
	`id` INT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `employee_department`(
    `employee_id` INT NOT NULL,
    `department_id` INT NOT NULL,
    PRIMARY KEY (`employee_id`, `department_id`),
    CONSTRAINT `employees_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employees`(`id`),
    CONSTRAINT `departments_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `departments`(`id`)
);
--
-- INSERT
--
INSERT INTO `employees`
VALUES
(1, 'Nguyễn Văn A', 30, 1000),
(2, 'Trần Thị B', 28, 900),
(3, 'Lê Văn C', 35, 2000),
(4, 'Phạm Thị D', 26, 900);

INSERT INTO `departments`
VALUES
(1, 'Kế toán'),
(2, 'Nhân sự'),
(3, 'Kỹ thuật'),
(4, 'Marketing');

INSERT INTO `employee_department`
VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 3),
(4, 4),
(4, 2);

-- Viết câu lệnh SQL để liệt kê tất cả các nhân viên trong bộ phận có tên là "Kế toán". 
-- Kết quả cần hiển thị mã nhân viên và tên nhân viên
SELECT e.`name`, d.`name`
FROM `employees` e
JOIN `employee_department` ed ON ed.`employee_id` = e.`id`
JOIN `departments` d ON d.`id` = ed.`department_id`
WHERE d.`name` = 'Kế toán';

SELECT e.`name`
FROM `employees` e
WHERE e.`id` IN (
	SELECT ed2.`employee_id`
    FROM `employee_department` ed2
    JOIN `departments` d2 ON d2.`id` = ed2.`department_id`
    WHERE d2.`name` = 'Kế toán'
);

-- Viết câu lệnh SQL để tìm các nhân viên có mức lương lớn hơn 50,000. Kết quả trả về
-- cần bao gồm mã nhân viên, tên nhân viên và mức lương
SELECT `id`, `name`, `salary`
FROM `employees` e
WHERE `salary` > 50000;

-- Viết câu lệnh SQL để hiển thị tất cả các bộ phận và số lượng nhân viên trong từng bộ
-- phận. Kết quả trả về cần bao gồm tên bộ phận và số lượng nhân viên
SELECT d.`name`, COUNT(de.`employee_id`) AS 'Số nhân viên'
FROM `departments` d
JOIN `employee_department` de ON de.`department_id` = d.`id`
GROUP BY d.`id`;

-- Viết câu lệnh SQL để tìm ra các thành viên có mức lương cao nhất theo từng bộ phận.
-- Kết quả trả về là một danh sách theo bất cứ thứ tự nào. Nếu có nhiều nhân viên bằng
-- lương nhau nhưng cũng là mức lương cao nhất thì hiển thị tất cả những nhân viên đó ra
SELECT d.`name` AS 'Tên bộ phận', e.`name` AS 'Tên nhân viên', e.`salary`
FROM `departments` d
JOIN `employee_department` ed ON d.`id` = ed.`department_id`
JOIN `employees` e ON e.`id` = ed.`employee_id`
WHERE (d.`id`, e.`salary`) IN (
	SELECT d2.`id`, MAX(e2.`salary`)
	FROM `departments` d2
	JOIN `employee_department` ed2 ON d2.`id` = ed2.`department_id`
	JOIN `employees` e2 ON e2.`id` = ed2.`employee_id`
	GROUP BY d2.`id`
);

-- Viết câu lệnh SQL để tìm các bộ phận có tổng mức lương của nhân viên vượt quá
-- 100,000 (hoặc một mức tùy chọn khác). Kết quả trả về bao gồm tên bộ phận và tổng
-- mức lương của bộ phận đó.
SELECT d.`name`, SUM(e.`salary`)
FROM `departments` d
JOIN `employee_department` de ON de.`department_id` = d.`id`
JOIN `employees` e ON e.`id` = de.`employee_id`
GROUP BY d.`id`
HAVING SUM(e.`salary`) > 150000;

-- Viết câu lệnh SQL để liệt kê tất cả các nhân viên làm việc trong hơn 2 bộ phận khác
-- nhau. Kết quả cần hiển thị mã nhân viên, tên nhân viên và số lượng bộ phận mà họ
-- tham gia
SELECT e.`id`, e.`name`, COUNT(d.`id`) AS 'số bộ phận' 
FROM `departments` d
JOIN `employee_department` de ON de.`department_id` = d.`id`
JOIN `employees` e ON e.`id` = de.`employee_id`
GROUP BY e.`id`
HAVING COUNT(d.`id`) >= 2;