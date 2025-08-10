create database product_db;
use product_db;

create table product
(
    id       int primary key auto_increment,
    name     varchar(100)   not null,
    price    decimal(15, 2) not null,
    quantity int            not null,
    image    varchar(255)
);


insert into product (name, price, quantity, image)
values ('Bút bi Thiên Long', 5000.00, 100, 'images/pen.jpg'),
       ('Vở kẻ ngang Campus', 12000.00, 50, 'images/notebook.jpg'),
       ('Cặp da học sinh', 250000.00, 20, 'images/bag.jpg');

delimiter //

create procedure get_all_products()
begin
    select * from product;
end //

create procedure get_product_by_id(in p_id int)
begin
    select * from product where id = p_id;
end //

create procedure insert_product(
    in p_name varchar(100),
    in p_price decimal(15, 2),
    in p_quantity int,
    in p_image varchar(255)
)
begin
    insert into product(name, price, quantity, image)
    values (p_name, p_price, p_quantity, p_image);
end //

create procedure update_product(
    in p_id int,
    in p_name varchar(100),
    in p_price decimal(15, 2),
    in p_quantity int,
    in p_image varchar(255)
)
begin
    update product
    set name     = p_name,
        price    = p_price,
        quantity = p_quantity,
        image    = p_image
    where id = p_id;
end //

create procedure delete_product(in p_id int)
begin
    delete from product where id = p_id;
end //

delimiter ;
