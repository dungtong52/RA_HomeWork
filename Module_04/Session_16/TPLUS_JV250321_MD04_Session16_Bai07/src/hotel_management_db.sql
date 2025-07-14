create database hotel_management_db;
use hotel_management_db;

create table room
(
    id          int auto_increment primary key,
    room_number varchar(10)    not null,
    type        varchar(50)    not null,
    price       decimal(10, 2) not null,
    is_booked   boolean default false
);

create table booking
(
    id            int auto_increment primary key,
    room_id       int,
    customer_name varchar(255) not null,
    start_date    date         not null,
    end_date      date         not null,
    foreign key (room_id) references room (id) on delete cascade
);

delimiter //
create procedure book_room(
    in_room_id int,
    in_customer_name varchar(255),
    in_start_date date,
    in_end_date date
)
begin
    declare room_status boolean;

    -- kiểm tra phòng có tồn tại và chưa được đặt
    select is_booked into room_status
    from room
    where id = in_room_id;

    if room_status = false then
        -- thêm vào bảng booking
        insert into booking (room_id, customer_name, start_date, end_date)
        values (in_room_id, in_customer_name, in_start_date, in_end_date);

        -- cập nhật trạng thái phòng
        update room
        set is_booked = true
        where id = in_room_id;
    else
        signal sqlstate '45000'
            set message_text = 'phòng đã được đặt!';
    end if;
end //
delimiter ;

delimiter //
create procedure cancel_booking(
    in_booking_id int
)
begin
    declare r_room_id int;

    -- lấy id phòng từ booking
    select room_id into r_room_id
    from booking
    where id = in_booking_id;

    -- xóa booking
    delete from booking
    where id = in_booking_id;

    -- cập nhật trạng thái phòng thành chưa đặt
    update room
    set is_booked = false
    where id = r_room_id;
end //
delimiter ;

delimiter //
create procedure list_available_rooms()
begin
    select * from room
    where is_booked = false;
end //
delimiter ;