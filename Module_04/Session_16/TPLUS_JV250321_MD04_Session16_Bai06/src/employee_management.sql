create database employee_management_db;
use employee_management_db;

create table employees (
    id int auto_increment primary key,
    name varchar(255) not null
);

create table projects (
    id int auto_increment primary key,
    project_name varchar(255) not null
);

create table employee_projects (
    employee_id int,
    project_id int,
    primary key (employee_id, project_id),
    foreign key (employee_id) references employees(id) on delete cascade,
    foreign key (project_id) references projects(id) on delete cascade
);

delimiter //
create procedure assign_employee_to_project(
    emp_id int,
    proj_id int
)
begin
    -- kiểm tra đã tồn tại bản ghi chưa
    if exists (
        select 1 from employee_projects
        where employee_id = emp_id and project_id = proj_id
    )
    then
        signal sqlstate '45000'
        set message_text = 'nhân viên đã được gán vào dự án này rồi.';
    else
        insert into employee_projects (employee_id, project_id)
        values (emp_id, proj_id);
    end if;
end //
delimiter ;

-- nhân viên
insert into employees (name) values ('nguyễn văn a');
insert into employees (name) values ('trần thị b');
insert into employees (name) values ('lê văn c');

-- dự án
insert into projects (project_name) values ('dự án xây dựng');
insert into projects (project_name) values ('dự án phần mềm');
insert into projects (project_name) values ('dự án marketing');
