create database sales_management_db;
use sales_management_db;

create table product
(
    id             int auto_increment primary key,
    name           varchar(255)   not null,
    price          decimal(10, 2) not null,
    stock_quantity int            not null
);

create table `order`
(
    id            int auto_increment primary key,
    customer_name varchar(255) not null,
    order_date    date         not null
);

create table order_detail
(
    id         int auto_increment primary key,
    order_id   int,
    product_id int,
    quantity   int not null,
    foreign key (order_id) references `order` (id) on delete cascade,
    foreign key (product_id) references product (id) on delete cascade
);

delimiter //
create procedure create_order_detail(
    in_order_id int,
    in_product_id int,
    in_quantity int
)
begin
    insert into order_detail(order_id, product_id, quantity)
    values (in_order_id, in_product_id, in_quantity);
end;

create procedure create_order(
    in_customer_name varchar(255),
    in_order_date date,
    OUT out_order_id INT
)
begin
    insert into `order`(customer_name, order_date)
    values (in_customer_name, in_order_date);

    SET out_order_id = LAST_INSERT_ID();
end //

create procedure check_stock(
    in_product_id int,
    in_quantity int
)
begin
    select 1 from product where id = in_product_id AND stock_quantity >= in_quantity;
end //

create procedure update_product_stock(
    in_product_id int,
    in_quantity int
)
begin
    declare o_stock int;
    set o_stock = (select p.stock_quantity from product p where id = in_product_id);

    update product
    set stock_quantity = stock_quantity - in_quantity
    where id = in_product_id;

end //

create procedure get_all_products()
begin
    select * from product;
end;

delimiter ;

INSERT INTO product (name, price, stock_quantity)
VALUES ('Laptop Dell XPS 13', 25000000.00, 10),
       ('Chuột Logitech M331', 500000.00, 100),
       ('Bàn phím cơ AKKO', 1200000.00, 50),
       ('Màn hình Samsung 24"', 3500000.00, 20),
       ('Tai nghe Sony WH-1000XM4', 5500000.00, 15);