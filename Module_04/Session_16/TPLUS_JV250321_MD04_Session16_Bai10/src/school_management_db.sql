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
    grade decimal(5, 2),
    primary key (student_id, course_id),
    foreign key (student_id) references student(id) on delete cascade,
    foreign key (course_id) references course(id) on delete cascade
);
