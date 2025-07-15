create database school_management_db;
use school_management_db;

create table student (
    id int auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) unique
);

create table course (
    id int auto_increment primary key,
    title varchar(255) not null
);

create table enrollment (
    student_id int,
    course_id int,
    grade decimal(5,2),
    primary key (student_id, course_id),
    foreign key (student_id) references student(id) on delete cascade,
    foreign key (course_id) references course(id) on delete cascade
);

delimiter //

create procedure get_all_students()
begin
    select * from student;
end //

create procedure add_student(
    in p_name varchar(255),
    in p_email varchar(255)
)
begin
    insert into student(name, email)
    values (p_name, p_email);
end //

create procedure check_student_exists_by_email(
    in p_email varchar(255),
    out p_exists boolean
)
begin
    declare v_count int;

    select count(s.id)
    into v_count
    from student s
    where email = p_email;

    set p_exists = v_count > 0;
end //

create procedure add_course(
    in p_title varchar(255)
)
begin
    insert into course(title)
    values (p_title);
end //s

create procedure check_course_exists_by_title(
    in p_title varchar(255),
    out p_exists boolean
)
begin
    declare v_count int;
    select count(*) into v_count from course where title = p_title;
    set p_exists = v_count > 0;
end //

create procedure update_enrollment_grade(
    in p_student_id int,
    in p_course_id int,
    in p_grade decimal(5,2)
)
begin
    update enrollment
    set grade = p_grade
    where student_id = p_student_id
      and course_id = p_course_id;
end //

delimiter ;
