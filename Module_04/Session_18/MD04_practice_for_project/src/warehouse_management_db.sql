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
    in_password varchar(30)
)
begin
    select acc_id, permission
    from accounts
    where user_name = in_user_name
      and password = in_password;

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
    set total_page = ceiling(v_total_product / in_size);

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
    select product_name from products where product_name = in_product_name;
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
    in_product_name varchar(150),
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_offset int;
    declare v_total_product int;

    -- Xuất list product
    set v_offset = (in_curr_page - 1) * in_size;

    select product_id, product_name, manufacturer, created, batch, quantity, product_status
    from products
    where product_name like concat('%', in_product_name, '%')
    limit in_size offset v_offset;

    -- Tính tổng số trang
    select count(product_id)
    into v_total_product
    from products
    where product_name like concat('%', in_product_name, '%');
    set total_page = ceiling(v_total_product / in_size);
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

INSERT INTO products
VALUES ('P0001', 'Bánh mì Pháp', 'ABC Foods', '2023-01-01', 1, 100, TRUE),
       ('P0002', 'Sữa tươi Vinamilk', 'Vinamilk', '2023-02-01', 1, 50, TRUE),
       ('P0003', 'Mì Hảo Hảo', 'Acecook', '2023-03-01', 2, 200, TRUE),
       ('P0004', 'Bánh Oreo', 'Mondelez', '2023-04-01', 2, 300, FALSE),
       ('P0005', 'Sữa chua', 'TH True Milk', '2023-05-01', 1, 100, TRUE),
       ('P0006', 'Bánh quy AFC', 'Bibica', '2023-06-01', 1, 180, TRUE),
       ('P0007', 'Trà xanh 0 độ', 'URC', '2023-07-01', 3, 500, TRUE),
       ('P0008', 'Nước ngọt Pepsi', 'PepsiCo', '2023-08-01', 1, 1000, TRUE),
       ('P0009', 'Sữa tươi Mộc Châu', 'Mộc Châu Milk', '2023-09-01', 2, 150, TRUE),
       ('P0010', 'Bánh tráng trộn', 'Nội địa VN', '2023-10-01', 2, 250, TRUE),
       ('P0011', 'Mì Omachi', 'Acecook', '2023-11-01', 1, 120, TRUE),
       ('P0012', 'Sữa đậu nành Fami', 'Vinasoy', '2023-12-01', 3, 300, TRUE),
       ('P0013', 'Bánh ngọt Solite', 'Kinh Đô', '2024-01-01', 1, 190, TRUE),
       ('P0014', 'Sữa Milo', 'Nestlé', '2024-02-01', 2, 220, TRUE),
       ('P0015', 'Nước suối Lavie', 'Nestlé', '2024-03-01', 1, 400, TRUE);

INSERT INTO employees (emp_id, emp_name, birth_of_date, email, phone, address, emp_status)
VALUES ('E0001', 'Nguyễn Văn A', '1990-01-01', 'a@gmail.com', '0900000001', 'Hà Nội', 1),
       ('E0002', 'Trần Thị B', '1992-02-02', 'b@gmail.com', '0900000002', 'Đà Nẵng', 1),
       ('E0003', 'Lê Văn C', '1993-03-03', 'c@gmail.com', '0900000003', 'TP.HCM', 1),
       ('E0004', 'Phạm Thị D', '1994-04-04', 'd@gmail.com', '0900000004', 'Cần Thơ', 1);

INSERT INTO accounts (user_name, password, permission, emp_id, acc_status)
VALUES ('admin01', 'adminpass', 1, 'E0001', 1), -- Admin
       ('user01', 'userpass1', 0, 'E0002', 1),  -- User
       ('user02', 'userpass2', 0, 'E0003', 1),  -- User
       ('blocked01', 'nopass', 0, 'E0004', 0); -- Tài khoản bị khóa
