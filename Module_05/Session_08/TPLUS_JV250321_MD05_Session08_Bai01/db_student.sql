create database db_student;
use db_student;

create table students
(
    id    bigint auto_increment primary key,
    name  varchar(100) not null,
    email varchar(100) not null unique,
    dob   date
);

DELIMITER //
create procedure get_all_student()
begin
    select * from students;
end //

create procedure get_student_by_id(in_id bigint)
begin
    select * from students where id = in_id;
end //

create procedure is_exist_email(in_email varchar(100))
begin
    select 1 from students where email = in_email;
end //

create procedure save_student(
    in_name varchar(100),
    in_email varchar(100),
    in_dob date
)
begin
    insert into students(name, email, dob)
    values (in_name, in_email, in_dob);
end //

create procedure update_student(
    in_id bigint,
    in_name varchar(100),
    in_email varchar(100),
    in_dob date
)
begin
    update students
    set name  = in_name,
        email = in_email,
        dob   = in_dob
    where id = in_id;
end //

create procedure delete_student(
    in_id bigint
)
begin
    delete from students where id = in_id;
end //

DELIMITER ;

INSERT INTO students (name, email, dob)
VALUES ('Nguyen Van A', 'a.nguyen@example.com', '2000-01-15'),
       ('Tran Thi B', 'b.tran@example.com', '1999-05-22'),
       ('Le Van C', 'c.le@example.com', '2001-07-30'),
       ('Pham Thi D', 'd.pham@example.com', '1998-12-10'),
       ('Hoang Van E', 'e.hoang@example.com', '2002-03-05');