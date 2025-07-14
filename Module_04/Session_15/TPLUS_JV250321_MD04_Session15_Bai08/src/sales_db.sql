create database sales_db;
use sales_db;

create table product
(
    id    int auto_increment primary key,
    name  varchar(255)   not null,
    price decimal(10, 2) not null
);

create table customer
(
    id    int auto_increment primary key,
    name  varchar(255) not null,
    email varchar(255) unique
);

create table `order`
(
    id           int auto_increment primary key,
    customer_id  int,
    order_date   date           not null,
    total_amount decimal(10, 2) not null,
    foreign key (customer_id) references customer (id)
);

# PROCEDURE FOR PRODUCT

DELIMITER //

CREATE PROCEDURE add_product(
    IN p_name VARCHAR(255),
    IN p_price DECIMAL(10, 2)
)
BEGIN
    INSERT INTO product(name, price) VALUES (p_name, p_price);
END //

CREATE PROCEDURE find_product_by_name(
    IN p_name VARCHAR(255)
)
BEGIN
    SELECT * FROM product WHERE name = p_name;
END //

CREATE PROCEDURE get_all_products()
BEGIN
    SELECT * FROM product;
END //

DELIMITER ;

# PROCEDURE FOR CUSTOMER

DELIMITER //

CREATE PROCEDURE update_customer(
    IN p_id INT,
    IN p_name VARCHAR(255),
    IN p_email VARCHAR(255)
)
BEGIN
    UPDATE customer
    SET name  = p_name,
        email = p_email
    WHERE id = p_id;
END //

CREATE PROCEDURE find_customer_by_id(
    IN p_id INT
)
BEGIN
    SELECT * FROM customer WHERE id = p_id;
END //

CREATE PROCEDURE get_all_customers()
BEGIN
    SELECT * FROM customer;
END //

DELIMITER ;

DELIMITER //

# PROCEDURE FOR ORDER

CREATE PROCEDURE create_order(
    IN p_customer_id INT,
    IN p_order_date DATE,
    IN p_total_amount DECIMAL(10, 2)
)
BEGIN
    INSERT INTO `order`(customer_id, order_date, total_amount)
    VALUES (p_customer_id, p_order_date, p_total_amount);
END //

CREATE PROCEDURE get_all_orders()
BEGIN
    SELECT * FROM `order`;
END //

CREATE PROCEDURE get_orders_by_customer(
    IN p_customer_id INT
)
BEGIN
    SELECT * FROM `order` WHERE customer_id = p_customer_id;
END //

DELIMITER ;

INSERT INTO customer (name, email)
VALUES ('Nguyễn Văn A', 'vana@gmail.com'),
       ('Trần Thị B', 'thib@gmail.com'),
       ('Lê Văn C', 'lec@gmail.com');
