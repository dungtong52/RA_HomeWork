create database db_booking;
use db_booking;

create table customers
(
    id           bigint auto_increment primary key,
    full_name    varchar(100),
    phone_number varchar(20) unique,
    email        varchar(100) unique,
    password     varchar(100)               not null,
    address      text                       not null,
    role         enum ('ADMIN', 'CUSTOMER') not null
);

DELIMITER //
create procedure is_login(
    in_string varchar(100),
    in_password varchar(100)
)
begin
    select id, full_name, phone_number, email, address, role
    from customers
    where (phone_number = in_string
        or email = in_string)
      and password = in_password;
end //

create procedure get_all_customers(
    in_size int,
    in_current_page int,
    OUT out_total_page int
)
begin
    declare v_offset int;
    declare v_total_row int;

    -- Tìm tổng số trang
    select count(id) into v_total_row from customers;
    set out_total_page = ceiling(v_total_row / in_size);

    -- Hiển thị trang hiện tại
    set v_offset = (in_current_page - 1) * in_size;
    select id, full_name, phone_number, email, address, role
    from customers
    limit in_size offset v_offset;
end //

create procedure is_phone_number_unique(
    in_id bigint,
    in_phone_number varchar(20)
)
begin
    select count(*) as cnt
    from customers
    where phone_number = in_phone_number
      and id <> in_id;
end //

create procedure is_email_unique(
    in_id bigint,
    in_email varchar(20)
)
begin
    select count(*) as cnt
    from customers
    where email = in_email
      and id <> in_id;
end //

create procedure save_customer(
    in_full_name varchar(100),
    in_phone_number varchar(20),
    in_email varchar(100),
    in_password varchar(100),
    in_address text,
    in_role varchar(100)
)
begin
    insert into customers(full_name, phone_number, email, password, address, role)
    values (in_full_name, in_phone_number, in_email,
            in_password, in_address, in_role);
end //

DELIMITER ;

INSERT INTO customers (full_name, phone_number, email, address, role, password)
VALUES ('Nguyễn Văn A', '0901234567', 'nguyenvana@example.com', '123 Lê Lợi, Hà Nội', 'ADMIN', '123456'),
       ('Trần Thị B', '0912345678', 'tranthib@example.com', '45 Nguyễn Huệ, TP.HCM', 'CUSTOMER', '123456'),
       ('Lê Văn C', '0923456789', 'levanc@example.com', '78 Lạch Tray, Hải Phòng', 'CUSTOMER', '123456'),
       ('Phạm Thị D', '0934567890', 'phamthid@example.com', '12 Trần Phú, Đà Nẵng', 'ADMIN', '123456'),
       ('Hoàng Văn E', '0945678901', 'hoangvane@example.com', '56 Nguyễn Văn Cừ, Cần Thơ', 'ADMIN', '123456'),
       ('Ngô Thị F', '0956789012', 'ngothif@example.com', '101 Hùng Vương, Huế', 'CUSTOMER', '123456'),
       ('Đinh Văn G', '0967890123', 'dinhvang@example.com', '23 Bạch Đằng, Đà Nẵng', 'CUSTOMER', '123456'),
       ('Bùi Thị H', '0978901234', 'buithih@example.com', '9 Lý Thường Kiệt, Hà Nội', 'ADMIN', '123456'),
       ('Vũ Văn I', '0989012345', 'vuvani@example.com', '67 Pasteur, TP.HCM', 'CUSTOMER', '123456'),
       ('Phan Thị J', '0990123456', 'phanthij@example.com', '34 Võ Văn Tần, Cần Thơ', 'CUSTOMER', '123456'),

       ('Trịnh Văn K', '0902345678', 'trinhvank@example.com', '88 Hai Bà Trưng, Hà Nội', 'CUSTOMER', '123456'),
       ('Tạ Thị L', '0913456789', 'tathil@example.com', '12 Điện Biên Phủ, Huế', 'ADMIN', '123456'),
       ('Nguyễn Văn M', '0924567890', 'nguyenvanm@example.com', '54 Nguyễn Trãi, Hải Phòng', 'CUSTOMER', '123456'),
       ('Đỗ Thị N', '0935678901', 'dothin@example.com', '29 Trần Hưng Đạo, TP.HCM', 'CUSTOMER', '123456'),
       ('Phạm Văn O', '0946789012', 'phamvano@example.com', '90 Cách Mạng Tháng 8, Đà Nẵng', 'ADMIN', '123456'),
       ('Lý Thị P', '0957890123', 'lythip@example.com', '4 Hoàng Diệu, Cần Thơ', 'CUSTOMER', '123456'),
       ('Hoàng Văn Q', '0968901234', 'hoangvanq@example.com', '76 Nguyễn Du, Hà Nội', 'CUSTOMER', '123456'),
       ('Ngô Thị R', '0979012345', 'ngothir@example.com', '55 Lý Tự Trọng, TP.HCM', 'ADMIN', '123456'),
       ('Bùi Văn S', '0980123456', 'buivans@example.com', '15 Quang Trung, Huế', 'CUSTOMER', '123456'),
       ('Vũ Thị T', '0991234567', 'vuthit@example.com', '48 Trần Cao Vân, Đà Nẵng', 'CUSTOMER', '123456'),

       ('Phan Văn U', '0903456789', 'phanvanu@example.com', '82 Nguyễn Văn Linh, Hà Nội', 'CUSTOMER', '123456'),
       ('Trịnh Thị V', '0914567890', 'trinhthiv@example.com', '23 Nguyễn Đình Chiểu, TP.HCM', 'ADMIN', '123456'),
       ('Tạ Văn W', '0925678901', 'tavanw@example.com', '10 Bùi Thị Xuân, Huế', 'CUSTOMER', '123456'),
       ('Nguyễn Thị X', '0936789012', 'nguyenthix@example.com', '61 Lê Quang Định, Hải Phòng', 'CUSTOMER', '123456'),
       ('Đỗ Văn Y', '0947890123', 'dovany@example.com', '39 Nguyễn Công Trứ, Đà Nẵng', 'ADMIN', '123456'),
       ('Phạm Thị Z', '0958901234', 'phamthiz@example.com', '77 Trần Khánh Dư, Cần Thơ', 'CUSTOMER', '123456'),
       ('Lý Văn AA', '0969012345', 'lyvanaa@example.com', '5 Hàng Bài, Hà Nội', 'CUSTOMER', '123456'),
       ('Hoàng Thị AB', '0970123456', 'hoangthiab@example.com', '98 Trần Hữu Trang, TP.HCM', 'ADMIN', '123456'),
       ('Ngô Văn AC', '0981234567', 'ngovanac@example.com', '26 Điện Biên, Huế', 'CUSTOMER', '123456'),
       ('Bùi Thị AD', '0992345678', 'buithiad@example.com', '33 Bạch Mai, Hà Nội', 'CUSTOMER', '123456');

create table rooms
(
    id        bigint primary key auto_increment,
    room_name varchar(255)             not null unique,
    room_type varchar(50)              not null,
    status    enum ('PLACED', 'EMPTY') not null default 'EMPTY',
    is_delete boolean                  not null default false,
    price     double                   not null check (price >= 1),
    image     varchar(255)             not null
);

DELIMITER //

create procedure add_room(
    in p_room_name varchar(255),
    in p_room_type varchar(50),
    in p_status enum ('PLACED', 'EMPTY'),
    in p_price double,
    in p_image varchar(255)
)
begin
    insert into rooms(room_name, room_type, status, price, image)
    values (p_room_name, p_room_type, p_status, p_price, p_image);
end//

create procedure update_room(
    in p_id bigint,
    in p_room_name varchar(255),
    in p_room_type varchar(50),
    in p_status enum ('PLACED', 'EMPTY'),
    in p_price double,
    in p_image varchar(255)
)
begin
    update rooms
    set room_name = p_room_name,
        room_type = p_room_type,
        status    = p_status,
        price     = p_price,
        image     = p_image
    where id = p_id
      and is_delete = false;
end//

create procedure delete_room(
    in p_id bigint
)
begin
    update rooms
    set is_delete = true
    where id = p_id;
end//

create procedure update_room_status(
    in p_id bigint,
    in p_status enum ('PLACED', 'EMPTY')
)
begin
    update rooms
    set status = p_status
    where id = p_id
      and is_delete = false;
end//

create procedure get_rooms(
    in p_limit int,
    in p_page int,
    out p_total_pages int
)
begin
    declare v_total_records int;
    declare v_offset int;

    select count(*)
    into v_total_records
    from rooms
    where is_delete = false;

    set p_total_pages = ceil(v_total_records / p_limit);
    set v_offset = (p_page - 1) * p_limit;

    select *
    from rooms
    where is_delete = false
    limit p_limit offset v_offset;
end //

create procedure is_room_name_unique(
    in p_id bigint,
    in p_room_name varchar(255)
)
begin
    select count(*) as cnt
    from rooms
    where room_name = p_room_name
      and (p_id is null or id <> p_id)
      and is_delete = false;
end//

create procedure get_room_by_id(in p_id bigint)
begin
    select *
    from rooms
    where id = p_id
      and is_delete = 0;
end //
DELIMITER ;

insert into rooms (room_name, room_type, status, price, image)
values ('Phòng 101', 'đơn', 'EMPTY', 300000, 'room101.jpg'),
       ('Phòng 102', 'đơn', 'PLACED', 300000, 'room102.jpg'),
       ('Phòng 103', 'đơn', 'EMPTY', 320000, 'room103.jpg'),
       ('Phòng 104', 'đơn', 'EMPTY', 320000, 'room104.jpg'),
       ('Phòng 105', 'đơn', 'PLACED', 350000, 'room105.jpg'),
       ('Phòng 201', 'đôi', 'EMPTY', 500000, 'room201.jpg'),
       ('Phòng 202', 'đôi', 'PLACED', 520000, 'room202.jpg'),
       ('Phòng 203', 'đôi', 'EMPTY', 500000, 'room203.jpg'),
       ('Phòng 204', 'đôi', 'EMPTY', 520000, 'room204.jpg'),
       ('Phòng 205', 'đôi', 'PLACED', 550000, 'room205.jpg'),
       ('Phòng 301', 'suite', 'EMPTY', 1000000, 'room301.jpg'),
       ('Phòng 302', 'suite', 'PLACED', 1050000, 'room302.jpg'),
       ('Phòng 303', 'suite', 'EMPTY', 1020000, 'room303.jpg'),
       ('Phòng 304', 'suite', 'EMPTY', 1040000, 'room304.jpg'),
       ('Phòng 305', 'suite', 'PLACED', 1100000, 'room305.jpg'),
       ('Phòng 401', 'đơn', 'EMPTY', 350000, 'room401.jpg'),
       ('Phòng 402', 'đơn', 'EMPTY', 360000, 'room402.jpg'),
       ('Phòng 403', 'đôi', 'PLACED', 550000, 'room403.jpg'),
       ('Phòng 404', 'suite', 'EMPTY', 1080000, 'room404.jpg'),
       ('Phòng 405', 'suite', 'PLACED', 1120000, 'room405.jpg');

create table booking
(
    id           bigint auto_increment primary key,
    customer_id  bigint not null,
    room_id      bigint not null,
    booking_date date   not null,
    price        double not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp on update current_timestamp,
    foreign key (customer_id) references customers (id),
    foreign key (room_id) references rooms (id)
);

DELIMITER //

create procedure add_booking(
    in p_customer_id bigint,
    in p_room_id bigint,
    in p_booking_date date,
    in p_price double
)
begin
    insert into booking (customer_id, room_id, booking_date, price)
    values (p_customer_id, p_room_id, p_booking_date, p_price);
end //

DELIMITER ;

DELIMITER //

-- đếm số lượng khách hàng
create procedure count_customers(out total_customers int)
begin
    select count(*) into total_customers from customers;
end //

-- doanh thu theo tháng hiện tại
create procedure get_revenue_current_month(out revenue double)
begin
    select sum(price)
    into revenue
    from booking
    where month(booking_date) = month(curdate())
      and year(booking_date) = year(curdate());
end //

-- top 3 phòng được đặt nhiều nhất
create procedure get_top3_rooms()
begin
    select r.id, r.room_name, count(b.id) as booking_count
    from rooms r
             join booking b on r.id = b.room_id
    group by r.id, r.room_name
    order by booking_count desc
    limit 3;
end //

-- top 5 khách hàng đặt phòng nhiều nhất
create procedure get_top5_customers()
begin
    select c.id, c.full_name, count(b.id) as booking_count
    from customers c
             join booking b on c.id = b.customer_id
    group by c.id, c.full_name
    order by booking_count desc
    limit 5;
end //

DELIMITER ;

insert into booking (customer_id, room_id, booking_date, price)
values (1, 2, '2025-08-12', 500000),
       (2, 3, '2025-08-13', 750000),
       (1, 1, '2025-08-15', 600000),
       (3, 2, '2025-08-16', 550000),
       (4, 4, '2025-08-17', 800000);
