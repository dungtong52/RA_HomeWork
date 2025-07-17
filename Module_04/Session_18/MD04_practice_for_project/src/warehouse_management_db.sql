create database warehouse_management_db;
use warehouse_management_db;

create table products
(
    product_id     char(5) primary key,
    product_name   varchar(150) not null unique,
    manufacturer   varchar(200) not null,
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

# Chức năng kiểm tra tài khoản khi đăng nhập

DELIMITER //

create procedure get_account_by_user_name(
    in_user_name varchar(30),
    in_user_password varchar(30)
)
begin
    select acc_id, permission
    from accounts
    where user_name = in_user_name
      and password = in_user_password;

end //

DELIMITER ;

# Procedure cho Product

DELIMITER //

create procedure get_all_product_pagination(
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_offset int;
    declare v_total_product int;

    -- Tính tổng số trang
    select count(product_id) into v_total_product from products;
    set total_page = v_total_product / in_size + 1;

    -- Xuất list product
    set v_offset = (in_curr_page - 1) * in_size;

    select product_id, product_name, manufacturer, created, batch, quantity, product_status
    from products pr
    limit in_size offset v_offset;
end //

create procedure create_product(
    in_product_id char(5),
    in_product_name varchar(150),
    in_manufacturer varchar(200),
    in_created date,
    in_batch smallint,
    in_quantity int
)
begin
    insert into products(product_id, product_name, manufacturer, created, batch, quantity)
    values (in_product_id, in_product_name, in_manufacturer,
            in_created, in_batch, in_quantity);
end //

create procedure get_product_by_id(
    in_product_id char(5)
)
begin
    select product_id, product_name, manufacturer, created, batch, quantity, product_status
    from products
    where product_id = in_product_id;
end //

create procedure check_exist_product_name(
    in_product_name varchar(150)
)
begin
    select product_name from products where product_id = in_product_name;
end //

create procedure update_product(
    in_product_id char(5),
    in_product_name varchar(150),
    in_manufacturer varchar(200),
    in_created date,
    in_batch smallint,
    in_quantity int
)
begin
    update products
    set product_name = in_product_name,
        manufacturer = in_manufacturer,
        created      = in_created,
        batch        = in_batch,
        quantity     = in_quantity
    where product_id = in_product_id;
end //

create procedure get_product_by_name(
    in_product_name varchar(150)
)
begin
    select product_id, product_name, manufacturer, created, batch, quantity, product_status
    from products
    where product_name like concat('%', in_product_name, '%');
end //

create procedure update_product_status(
    in_product_id char(5),
    in_status bit
)
begin
    update products
    set product_status = in_status
    where product_id = in_product_id;
end //

DELIMITER ;