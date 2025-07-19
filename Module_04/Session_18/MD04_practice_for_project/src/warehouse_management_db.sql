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
    in_batch smallint
)
begin
    insert into products(product_id, product_name, manufacturer, created, batch)
    values (in_product_id, in_product_name, in_manufacturer,
            current_date, in_batch);
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
       ('blocked01', 'nopass', 0, 'E0004', 0);

DELIMITER //

# Procedure cho Employee

create procedure get_all_employee_pagination_ASC(
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_offset int;
    declare v_total_employee int;

    -- Tính tổng số trang
    select count(emp_id) into v_total_employee from employees;
    set total_page = ceiling(v_total_employee / in_size);

    -- Xuất list product
    set v_offset = (in_curr_page - 1) * in_size;

    select emp_id, emp_name, birth_of_date, email, phone, address, emp_status
    from employees
    order by emp_name
    limit in_size offset v_offset;
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

create procedure get_employee_by_name(
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
    where emp_name like concat('%', in_emp_name, '%')
    limit in_size offset v_offset;

    -- Tính tổng số trang
    select count(emp_id)
    into v_total_employee
    from employees
    where emp_name like concat('%', in_emp_name, '%');

    set total_page = ceiling(v_total_employee / in_size);
end //

create procedure update_emp_status(
    in_emp_id char(5),
    in_status bit
)
begin
    declare exit handler for sqlexception
        begin
            rollback ;
        end;
    start transaction ;

    update employees
    set emp_status = in_status
    where emp_id = in_emp_id;

    if (in_status = 1 || 2) then
        update accounts ac
        set ac.acc_status = 0
        where ac.emp_id = in_emp_id;
    end if;
    commit;
end //

DELIMITER ;
# Procedure cho Account

DELIMITER //

create procedure get_all_account_pagination(
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

    select acc_id, user_name, password, permission, emp_id, acc_status
    from accounts
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

create procedure get_account_by_name(
    in_user_name varchar(30),
    in_size int,
    in_curr_page int,
    out total_pages int
)
begin
    declare v_total_acc int;
    declare v_offset int;

    set v_offset = (in_curr_page - 1) * in_size;

    -- Tra ve danh sach
    select acc_id, user_name, password, permission, emp_id, acc_status
    from accounts
    where user_name like concat('%', in_user_name, '%')
    limit in_size offset v_offset;

    -- Tra ve tong so trang
    select count(acc_id)
    into v_total_acc
    from accounts
    where user_name like concat('%', in_user_name, '%');

    set total_pages = ceiling(v_total_acc / in_size);

end //

DELIMITER ;

# Procedure cho Receipt
DELIMITER //

create procedure get_all_receipt_pagination(
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_total_receipt int;
    declare v_offset int;

    -- Tong so page
    select count(bill_id) into v_total_receipt from bills where bill_type = 1;
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
    where bill_type = 1
    limit in_size offset v_offset;

end //

create procedure create_receipt(
    in_bill_code varchar(10),
    in_bill_type bit,
    in_emp_id_created char(5),
    out out_bill_id bigint
)
begin
    insert into bills (bill_code, bill_type, emp_id_created, created)
    values (in_bill_code, in_bill_type, in_emp_id_created, current_date);

    set out_bill_id = last_insert_id();
end //

create procedure check_exist_bill_code(in_bill_code varchar(10))
begin
    select 1 from bills where bill_code = in_bill_code;
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
    where bill_code = in_bill_code;
end //

create procedure update_receipt(
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

# Procedure cho Receipt Details

DELIMITER //

create procedure create_receipt_detail(
    in_bill_id bigint,
    in_product_id char(5),
    in_quantity int,
    in_price float
)
begin
    insert into bill_details(bill_id, product_id, quantity, price)
    values (in_bill_id, in_product_id, in_quantity, in_price);
end //

create procedure get_receipt_details_by_bill_id(
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

create procedure update_receipt_detail(
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

# Procedure cho Bill
DELIMITER //

create procedure get_all_bill_pagination(
    in_size int,
    in_curr_page int,
    out total_page int
)
begin
    declare v_total_bill int;
    declare v_offset int;

    -- Tong so page
    select count(bill_id) into v_total_bill from bills where bill_type = 0;
    set total_page = ceiling(v_total_bill / in_size);

    -- Xuat toan bo phieu xuat
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
    where bill_type = 0
    limit in_size offset v_offset;

end //

DELIMITER ;


# declare exit handler for sqlexception
# begin
# rollback ;
# end;
#
# start transaction ;
#
# -- Thêm receipt_detail
# insert into bill_details(bill_id, product_id, quantity, price)
# values (in_bill_id, in_product_id, in_quantity, in_price);
#
# -- Cập nhật số lượng trong products
# update products
# set quantity = quantity + in_quantity
# where product_id = in_product_id;
# commit;