create database warehouse_management_db;
use warehouse_management_db;

create table products
(
    product_id     char(5) primary key,
    product_name   varchar(150) not null unique,
    Manufacturer   varchar(200) not null,
    created        date,
    batch          smallint     not null,
    quantity       int          not null default 0,
    product_status bit                   default 1
);

create table employees
(
    emp_id        char(5) primary key,
    emp_name      varchar(100) not null unique,
    birth_of_date date,
    email         varchar(100) not null,
    phone         varchar(100) not null,
    address       text         not null,
    emp_status    smallint     not null
);

create table accounts
(
    acc_id     int primary key auto_increment,
    user_name  varchar(30) not null unique,
    password   varchar(30) not null,
    permission bit default 1,
    emp_id     char(5)     not null unique,
    acc_status bit default 1,
    foreign key (emp_id) references employees (emp_id)
);

create table bills
(
    bill_id        bigint primary key auto_increment,
    bill_code      varchar(10) not null,
    bill_type      bit         not null,
    emp_id_created char(5)     not null,
    created        date,
    emp_id_auth    char(5)     not null,
    auth_date      date,
    bill_status    smallint    not null default 0,
    foreign key (emp_id_created) references employees (emp_id),
    foreign key (emp_id_auth) references employees (emp_id)
);

create table bill_details
(
    bill_detail_id bigint primary key auto_increment,
    bill_id        bigint  not null,
    product_id     char(5) not null,
    quantity       int     not null check ( quantity > 0 ),
    price          float   not null check ( price > 0 ),
    foreign key (bill_id) references bills (bill_id),
    foreign key (product_id) references products (product_id)
);

delimiter //

create procedure get_account_by_user_name(in_user_name varchar(30))
begin
    select ac.user_name, ac.password, ac.permission
    from accounts ac
    where ac.user_name = in_user_name;
end //

delimiter ;
