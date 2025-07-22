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
    emp_id_auth    char(5),
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

create procedure get_account_to_login(
    in_user_name varchar(30),
    in_password varchar(30)
)
begin
    select acc_id, permission, emp_id
    from accounts
    where user_name = in_user_name
      and password = in_password;

end //

DELIMITER ;

# Procedure cho Product

DELIMITER //

create procedure get_list_product_by_key(
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
    where in_product_name is null
       or product_name like concat('%', in_product_name, '%')
    limit in_size offset v_offset;

    -- Tính tổng số trang
    select count(product_id)
    into v_total_product
    from products
    where in_product_name is null
       or product_name like concat('%', in_product_name, '%');
    set total_page = ceiling(v_total_product / in_size);
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

create procedure create_product(
    in_product_id char(5),
    in_product_name varchar(150),
    in_manufacturer varchar(200),
    in_batch smallint
)
begin
    insert into products(product_id, product_name, manufacturer, created, batch)
    values (in_product_id, in_product_name, in_manufacturer,
            current_date, in_batch);
end //

create procedure update_product(
    in_product_id char(5),
    in_product_name varchar(150),
    in_manufacturer varchar(200),
    in_created date,
    in_batch smallint
)
begin
    update products
    set product_name = in_product_name,
        manufacturer = in_manufacturer,
        created      = in_created,
        batch        = in_batch
    where product_id = in_product_id;
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

DELIMITER //

# Procedure cho Employee

create procedure get_list_employee_by_search_key(
    in_emp_name varchar(150),
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_offset int;
    declare v_total_employee int;

    -- Xuất list product
    set v_offset = (in_curr_page - 1) * in_size;

    select emp_id, emp_name, birth_of_date, email, phone, address, emp_status
    from employees
    where in_emp_name is null
       or emp_name like concat('%', in_emp_name, '%')
    order by emp_name
    limit in_size offset v_offset;

    -- Tính tổng số trang
    select count(emp_id)
    into v_total_employee
    from employees
    where in_emp_name is null
       or emp_name like concat('%', in_emp_name, '%');

    set total_page = ceiling(v_total_employee / in_size);
end //

create procedure create_employee(
    in_emp_id char(5),
    in_emp_name varchar(100),
    in_birth_of_date date,
    in_email varchar(100),
    in_phone varchar(100),
    in_address text,
    in_emp_status smallint
)
begin
    insert into employees(emp_id, emp_name, birth_of_date, email, phone, address, emp_status)
    values (in_emp_id, in_emp_name, in_birth_of_date, in_email,
            in_phone, in_address, in_emp_status);
end //

create procedure get_employee_by_id(
    in_emp_id char(5)
)
begin
    select emp_id, emp_name, birth_of_date, email, phone, address, emp_status
    from employees
    where emp_id = in_emp_id;
end //

create procedure check_exist_employee_name(
    in_emp_name varchar(100)
)
begin
    select emp_name from employees where emp_name = in_emp_name;
end //

create procedure update_employee(
    in_emp_id char(5),
    in_emp_name varchar(100),
    in_birth_of_date date,
    in_email varchar(100),
    in_phone varchar(100),
    in_address text
)
begin
    update employees
    set emp_name      = in_emp_name,
        birth_of_date = in_birth_of_date,
        email         = in_email,
        phone         = in_phone,
        address       = in_address
    where emp_id = in_emp_id;
end //

create procedure update_emp_status(
    in_emp_id char(5),
    in_status smallint
)
begin
    update employees
    set emp_status = in_status
    where emp_id = in_emp_id;

    if (in_status = 1 or in_status = 2) then
        update accounts ac
        set ac.acc_status = 0
        where ac.emp_id = in_emp_id;
    end if;

end //

DELIMITER ;
# Procedure cho Account

DELIMITER //

create procedure get_list_account_by_search_key(
    in_user_name varchar(30),
    in_emp_name varchar(100),
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_offset int;
    declare v_total_account int;

    -- Tính tổng số trang
    select count(acc_id) into v_total_account from accounts;
    set total_page = ceiling(v_total_account / in_size);

    -- Xuất list product
    set v_offset = (in_curr_page - 1) * in_size;

    select a.acc_id,
           a.user_name,
           a.password,
           a.permission,
           a.emp_id,
           e.emp_name,
           a.acc_status
    from accounts a
             join employees e on a.emp_id = e.emp_id
    where (in_user_name is null or a.user_name like concat('%', in_user_name, '%'))
       or (in_emp_name is null or e.emp_name like concat('%', in_emp_name, '%'))
    limit in_size offset v_offset;

end //

create procedure create_account(
    in_user_name varchar(30),
    in_password varchar(30),
    in_emp_id char(5)
)
begin
    insert into accounts(user_name, password, emp_id)
    values (in_user_name, in_password, in_emp_id);
end //

create procedure check_exist_account_name(in_name varchar(30))
begin
    select 1 from accounts where user_name = in_name;
end//

create procedure check_exist_emp_id(in_emp_id char(5))
begin
    select 1 from accounts where emp_id = in_emp_id;
end//

create procedure get_account_by_id(
    in_account_id int
)
begin
    select acc_id, user_name, password, permission, emp_id, acc_status
    from accounts
    where acc_id = in_account_id;
end //

create procedure update_account_status(
    in_acc_id int,
    in_status bit
)
begin
    update accounts
    set acc_status = in_status
    where acc_id = in_acc_id;
end //

DELIMITER ;

# Procedure cho Receipt

DELIMITER //

create procedure accept_receipt(
    in_bill_id bigint
)
begin
    update products p
        join bill_details bd on p.product_id = bd.product_id
    set p.quantity = p.quantity + bd.quantity
    where bd.bill_id = in_bill_id;

    update bills
    set bill_status = 2
    where bill_id = in_bill_id
      and bill_status = 0;

end //

DELIMITER ;

# Procedure cho Bill

DELIMITER //

create procedure accept_bill(
    in_bill_id bigint
)
begin
    declare v_count_stock int;

    -- Kiểm tra tồn kho
    select count(p.product_id)
    into v_count_stock
    from products p
             join bill_details bd on p.product_id = bd.product_id
    where bd.bill_id = in_bill_id
      and p.quantity < bd.quantity;

    if (v_count_stock > 0) then
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không đủ số lượng tồn kho';
    end if;

    -- Trừ quantity
    update products p
        join bill_details bd on p.product_id = bd.product_id
    set p.quantity = p.quantity - bd.quantity
    where bd.bill_id = in_bill_id;

    -- Cập nhật status
    update bills
    set bill_status = 2
    where bill_id = in_bill_id
      and bill_status = 0;

end //

DELIMITER ;

# Procedure chung cho Bill và Receipt

DELIMITER //

create procedure get_list_bill_by_search_key(
    in_bill_type bit,
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_total_receipt int;
    declare v_offset int;

    -- Tong so page
    select count(bill_id) into v_total_receipt from bills where bill_type = in_bill_type;
    set total_page = ceiling(v_total_receipt / in_size);

    -- Xuat toan bo phieu nhap
    set v_offset = (in_curr_page - 1) * in_size;
    select bill_id,
           bill_code,
           bill_type,
           emp_id_created,
           created,
           emp_id_auth,
           auth_date,
           bill_status
    from bills
    where bill_type = in_bill_type
    limit in_size offset v_offset;

end //

create procedure create_bill(
    in_bill_code varchar(10),
    in_bill_type bit,
    in_emp_id_created char(5)
)
begin
    insert into bills (bill_code, bill_type, emp_id_created, created, auth_date)
    values (in_bill_code, in_bill_type, in_emp_id_created, current_date, current_date);
end //

create procedure check_exist_bill_code(
    in_bill_code varchar(10),
    in_bill_type bit
)
begin
    select 1 from bills where bill_code = in_bill_code and bill_type = in_bill_type;
end //

create procedure check_exist_bill_id(
    in_bill_id bigint,
    in_bill_type bit
)
begin
    select 1 from bills where bill_id = in_bill_id and bill_type = in_bill_type;
end //

create procedure find_bill_by_code(in_bill_code varchar(10))
begin
    select bill_id,
           bill_code,
           bill_type,
           emp_id_created,
           created,
           emp_id_auth,
           auth_date,
           bill_status
    from bills
    where bill_code = in_bill_code
    limit 1;
end //

create procedure update_bill(
    in_bill_code varchar(10),
    in_emp_id_created char(5),
    in_created date,
    in_emp_id_auth char(5),
    in_auth_date date,
    in_bill_status smallint
)
begin
    update bills
    set emp_id_created = in_emp_id_created,
        created        = in_created,
        emp_id_auth    = in_emp_id_auth,
        auth_date      = in_auth_date,
        bill_status    = in_bill_status
    where bill_code = in_bill_code
      and (bill_status = 0 or bill_status = 1);
end //

DELIMITER ;

# Procedure cho Receipt / Bill Details

DELIMITER //

create procedure create_bill_detail(
    in_bill_id bigint,
    in_product_id char(5),
    in_quantity int,
    in_price float
)
begin
    insert into bill_details(bill_id, product_id, quantity, price)
    values (in_bill_id, in_product_id, in_quantity, in_price);
end //

create procedure get_bill_details_by_bill_id(
    in_bill_id bigint,
    in_size int,
    in_curr_page int,
    out total_pages int
)
begin
    declare v_total_details int;
    declare v_offset int;

    -- Lay pagination
    set v_offset = (in_curr_page - 1) * in_size;

    select bill_detail_id, bill_id, product_id, quantity, price
    from bill_details
    where bill_id = in_bill_id
    limit in_size offset v_offset;

    -- Lay tong so trang
    select count(bill_detail_id)
    into v_total_details
    from bill_details
    where bill_id = in_bill_id;

    set total_pages = ceiling(v_total_details / in_size);
end //

create procedure find_bill_detail_by_id(in_bill_detail_id bigint)
begin
    select bill_detail_id, bill_id, product_id, quantity, price
    from bill_details
    where bill_detail_id = in_bill_detail_id;
end //

create procedure update_bill_detail(
    in_bill_detail_id bigint,
    in_product_id char(5),
    in_quantity int,
    in_price float
)
begin
    update bill_details
    set product_id = in_product_id,
        quantity   = in_quantity,
        price      = in_price
    where bill_detail_id = in_bill_detail_id;
end //

DELIMITER ;

# Procedure cho Statistic (Làm gọn lại, ít procedure và DAO đi)

DELIMITER //

create procedure statistic_revenue_cost_by_date(
    in_bill_type bit
)
begin
    select b.created,
           sum(bd.quantity * bd.price) as `Tổng số tiền`
    from bills b
             join bill_details bd on b.bill_id = bd.bill_id
    where b.bill_type = in_bill_type
    group by b.created
    order by b.created;
end //

create procedure statistic_revenue_cost_by_month(in_bill_type bit)
begin
    select month(b.created)            as `Tháng`,
           year(b.created)             as `Năm`,
           sum(bd.quantity * bd.price) as `Tổng số tiền`
    from bills b
             join bill_details bd on b.bill_id = bd.bill_id
    where b.bill_type = in_bill_type
    group by month(b.created), year(b.created)
    order by month(b.created), year(b.created);
end //

create procedure statistic_revenue_cost_by_year(in_bill_type bit)
begin
    select year(b.created)             as `Năm`,
           sum(bd.quantity * bd.price) as `Tổng số tiền`
    from bills b
             join bill_details bd on b.bill_id = bd.bill_id
    where b.bill_type = in_bill_type
    group by year(b.created)
    order by year(b.created);
end //

create procedure statistic_revenue_cost_in_date_range(
    in_bill_type bit,
    in_start_date date,
    in_end_date date
)
begin
    select b.created,
           sum(bd.quantity * bd.price) as `Tổng số tiền`
    from bills b
             join bill_details bd on b.bill_id = bd.bill_id
    where b.bill_type = in_bill_type
      and b.created between in_start_date and in_end_date
    group by b.created
    order by b.created;
end //

create procedure statistic_employee_by_status()
begin
    select emp_status,
           count(emp_id) as `Số nhân viên`
    from employees e
    group by e.emp_status;
end //

create procedure statistic_product_max_trade_in_date_range(
    in_bill_type bit,
    in_start_date date,
    in_end_date date
)
begin
    select p.product_name,
           sum(bd.quantity) as `Số sản phẩm`
    from bills b
             join bill_details bd on b.bill_id = bd.bill_id
             join products p on p.product_id = bd.product_id
    where b.bill_type = in_bill_type
      and b.created between in_start_date and in_end_date
    group by bd.product_id
    order by sum(bd.quantity) desc
    limit 1;
end //

create procedure statistic_product_min_trade_in_date_range(
    in_bill_type bit,
    in_start_date date,
    in_end_date date
)
begin
    select p.product_name,
           sum(bd.quantity) as `Số sản phẩm`
    from bills b
             join bill_details bd on b.bill_id = bd.bill_id
             join products p on p.product_id = bd.product_id
    where b.bill_type = in_bill_type
      and b.created between in_start_date and in_end_date
    group by bd.product_id
    order by sum(bd.quantity)
    limit 1;
end //

DELIMITER ;

# Procedure cho USER

DELIMITER //

create procedure get_all_bill_for_user(
    in_bill_type bit,
    in_emp_id char(5),
    in_status smallint,
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_total_receipt int;
    declare v_offset int;

    -- Tinh tong so trang
    select count(bill_id)
    into v_total_receipt
    from bills
    where emp_id_created = in_emp_id
      and bill_type = in_bill_type
      and bill_status = in_status;

    set total_page = ceiling(v_total_receipt / in_size);

    -- Phan trang
    set v_offset = (in_curr_page - 1) * in_size;

    select bill_id,
           bill_code,
           bill_type,
           emp_id_created,
           created,
           emp_id_auth,
           auth_date,
           bill_status
    from bills
    where emp_id_created = in_emp_id
      and bill_type = in_bill_type
      and bill_status = in_status
    limit in_size offset v_offset;

end //

create procedure check_exist_bill_code_of_user(
    in_bill_code varchar(10),
    in_bill_type bit,
    in_emp_id_created char(5)
)
begin
    select 1
    from bills
    where bill_code = in_bill_code
      and bill_type = in_bill_type
      and emp_id_created = in_emp_id_created;
end //

create procedure update_bill_for_user(
    in_bill_code varchar(10),
    in_bill_type bit,
    in_emp_id_created char(5),
    in_created date,
    in_bill_status smallint
)
begin
    update bills
    set created     = in_created,
        bill_status = in_bill_status
    where bill_code = in_bill_code
      and emp_id_created = in_emp_id_created
      and bill_type = in_bill_type
      and (bill_status = 0 or bill_status = 1);
end //

create procedure find_bill_by_code_for_user(
    in_bill_code varchar(10),
    in_bill_type bit,
    in_emp_id_created char(5)
)
begin
    select bill_id,
           bill_code,
           bill_type,
           emp_id_created,
           created,
           emp_id_auth,
           auth_date,
           bill_status
    from bills
    where bill_code = in_bill_code
      and emp_id_created = in_emp_id_created
      and bill_type = in_bill_type
    limit 1;
end //

DELIMITER ;

INSERT INTO products (product_id, product_name, manufacturer, created, batch, quantity, product_status)
VALUES ('P001', 'Product 1', 'Manufacturer 1', '2023-03-15', 3, 150, 1),
       ('P002', 'Product 2', 'Manufacturer 2', '2022-07-20', 2, 90, 1),
       ('P003', 'Product 3', 'Manufacturer 3', '2024-01-11', 1, 120, 0),
       ('P004', 'Product 4', 'Manufacturer 1', '2023-12-05', 5, 80, 1),
       ('P005', 'Product 5', 'Manufacturer 2', '2023-05-25', 4, 60, 1),
       ('P006', 'Product 6', 'Manufacturer 3', '2022-09-18', 2, 200, 0),
       ('P007', 'Product 7', 'Manufacturer 1', '2023-10-01', 3, 75, 1),
       ('P008', 'Product 8', 'Manufacturer 2', '2024-03-03', 1, 130, 1),
       ('P009', 'Product 9', 'Manufacturer 3', '2022-12-29', 2, 55, 0),
       ('P010', 'Product 10', 'Manufacturer 1', '2023-08-08', 4, 100, 1);

INSERT INTO employees (emp_id, emp_name, birth_of_date, email, phone, address, emp_status)
VALUES ('E001', 'Employee 1', '1990-06-15', 'employee1@example.com', '0123456789', '123 Main St, District 1', 0),
       ('E002', 'Employee 2', '1988-11-22', 'employee2@example.com', '0987654321', '456 2nd Ave, District 3', 1),
       ('E003', 'Employee 3', '1995-03-05', 'employee3@example.com', '0901234567', '789 3rd Blvd, District 5', 0),
       ('E004', 'Employee 4', '1992-08-30', 'employee4@example.com', '0912345678', '234 4th St, District 7', 2),
       ('E005', 'Employee 5', '1985-01-19', 'employee5@example.com', '0934567890', '567 5th Ave, District 9', 0);

INSERT INTO accounts (acc_id, user_name, password, permission, emp_id, acc_status)
VALUES (1, 'user1', 'User111$', 0, 'E001', 1),
       (2, 'user2', 'User222$', 1, 'E002', 1),
       (3, 'user3', 'User333$', 1, 'E003', 1),
       (4, 'user4', 'User444$', 0, 'E004', 0),
       (5, 'user5', 'User555$', 1, 'E005', 1);

INSERT INTO bills (bill_id, bill_code, bill_type, emp_id_created, created, emp_id_auth, auth_date, bill_status)
VALUES (1, 'B001', 0, 'E001', '2024-05-10', 'E002', '2024-05-11', 2), -- phiếu nhập đã duyệt
       (2, 'B002', 1, 'E002', '2024-05-12', NULL, NULL, 0),           -- phiếu xuất chờ duyệt
       (3, 'B003', 0, 'E003', '2024-06-01', NULL, NULL, 0),           -- phiếu nhập chờ duyệt
       (4, 'B004', 1, 'E001', '2024-06-03', 'E003', '2024-06-04', 2), -- phiếu xuất đã duyệt
       (5, 'B005', 0, 'E002', '2024-06-10', NULL, NULL, 1); -- phiếu nhập đã hủy

INSERT INTO bill_details (bill_detail_id, bill_id, product_id, quantity, price)
VALUES
-- Chi tiết B001 - Nhập sản phẩm
(1, 1, 'P001', 50, 12000),
(2, 1, 'P002', 30, 15000),
(3, 1, 'P003', 20, 10000),

-- Chi tiết B002 - Xuất sản phẩm (chưa duyệt)
(4, 2, 'P001', 10, 15000),
(5, 2, 'P004', 5, 20000),

-- Chi tiết B003 - Nhập sản phẩm (chờ duyệt)
(6, 3, 'P005', 40, 11000),
(7, 3, 'P006', 25, 13000),

-- Chi tiết B004 - Xuất sản phẩm
(8, 4, 'P002', 15, 15000),
(9, 4, 'P007', 10, 18000),

-- Chi tiết B005 - Nhập sản phẩm (đã hủy)
(10, 5, 'P008', 30, 12500);
