CREATE DATABASE company_db;
USE company_db;

CREATE TABLE Employee
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255)   NOT NULL,
    department VARCHAR(255)   NOT NULL,
    salary     DECIMAL(10, 2) NOT NULL
);
CREATE TABLE Project
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(255)   NOT NULL,
    budget DECIMAL(10, 2) NOT NULL
);
CREATE TABLE Assignment
(
    employee_id INT,
    project_id  INT,
    role        VARCHAR(255) NOT NULL,
    PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES Employee (id),
    FOREIGN KEY (project_id) REFERENCES Project (id)
);

-- Thêm nhân viên
DELIMITER $$
CREATE PROCEDURE sp_add_employee(
    IN emp_name VARCHAR(255),
    IN dept VARCHAR(255),
    IN salary DECIMAL(10,2)
)
BEGIN
    INSERT INTO Employee(name, department, salary)
    VALUES (emp_name, dept, salary);
END$$
DELIMITER ;

# EMPLOYEE
-- Tìm nhân viên theo tên
DELIMITER $$
CREATE PROCEDURE sp_find_employee_by_name(IN emp_name VARCHAR(255))
BEGIN
    SELECT * FROM Employee WHERE name = emp_name;
END$$
DELIMITER ;

-- Tìm nhân viên theo ID
DELIMITER $$
CREATE PROCEDURE sp_find_employee_by_id(IN emp_id INT)
BEGIN
    SELECT * FROM Employee WHERE id = emp_id;
END$$
DELIMITER ;

-- Cập nhật lương
DELIMITER $$
CREATE PROCEDURE sp_update_employee_salary(
    IN emp_id INT,
    IN new_salary DECIMAL(10,2)
)
BEGIN
    UPDATE Employee
    SET salary = new_salary
    WHERE id = emp_id;
END$$
DELIMITER ;

-- Lấy tất cả nhân viên
DELIMITER $$
CREATE PROCEDURE sp_get_all_employees()
BEGIN
    SELECT * FROM Employee;
END$$
DELIMITER ;

# PROJECT
-- Thêm dự án
DELIMITER $$
CREATE PROCEDURE sp_add_project(
    IN p_name VARCHAR(255),
    IN p_budget DECIMAL(10,2)
)
BEGIN
    INSERT INTO Project(name, budget)
    VALUES (p_name, p_budget);
END$$
DELIMITER ;

-- Tìm dự án theo tên
DELIMITER $$
CREATE PROCEDURE sp_find_project_by_name(IN p_name VARCHAR(255))
BEGIN
    SELECT * FROM Project WHERE name = p_name;
END$$
DELIMITER ;

-- Tìm dự án theo ID
DELIMITER $$
CREATE PROCEDURE sp_find_project_by_id(IN p_id INT)
BEGIN
    SELECT * FROM Project WHERE id = p_id;
END$$
DELIMITER ;

-- Lấy tất cả dự án
DELIMITER $$
CREATE PROCEDURE sp_get_all_projects()
BEGIN
    SELECT * FROM Project;
END$$
DELIMITER ;

# ASSIGNMENT
-- Gán nhân viên vào dự án
DELIMITER $$
CREATE PROCEDURE sp_assign_employee_to_project(
    IN emp_id INT,
    IN proj_id INT,
    IN role_name VARCHAR(255)
)
BEGIN
    INSERT INTO Assignment(employee_id, project_id, role)
    VALUES (emp_id, proj_id, role_name);
END$$
DELIMITER ;

-- Lấy tất cả các assignment
DELIMITER $$
CREATE PROCEDURE sp_get_all_assignments()
BEGIN
    SELECT * FROM Assignment;
END$$
DELIMITER ;

INSERT INTO Employee(name, department, salary)
VALUES
    ('Nguyễn Văn A', 'Kỹ thuật', 12000000.00),
    ('Trần Thị B', 'Kế toán', 9000000.00),
    ('Lê Văn C', 'Nhân sự', 10500000.00);

INSERT INTO Project(name, budget)
VALUES
    ('Dự án A', 10000000.00),
    ('Dự án B', 5000000.00),
    ('Dự án C', 7500000.00);

INSERT INTO Assignment(employee_id, project_id, role)
VALUES
    (1, 1, 'Leader'),
    (2, 2, 'Tester'),
    (3, 1, 'Developer'),
    (1, 3, 'Advisor');