create database sales_management_db;
use sales_management_db;

create table product (
    id int auto_increment primary key,
    name varchar(255) not null,
    price decimal(10, 2) not null,
    stock_quantity int not null
);

create table `order` (
    id int auto_increment primary key,
    customer_name varchar(255) not null,
    order_date date not null
);

create table order_detail (
    id int auto_increment primary key,
    order_id int,
    product_id int,
    quantity int not null,
    foreign key (order_id) references `order`(id) on delete cascade,
    foreign key (product_id) references product(id) on delete cascade
);
