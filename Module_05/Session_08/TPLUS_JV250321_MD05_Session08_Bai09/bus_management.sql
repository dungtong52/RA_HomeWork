create database if not exists bus_management;
use bus_management;

create table bus
(
    id            int auto_increment primary key,
    license_plate varchar(20)                      not null unique,
    bus_type      enum ('VIP', 'LUXURY', 'NORMAL') not null,
    row_seat      int                              not null,
    col_seat      int                              not null,
    total_seat    int generated always as (row_seat * col_seat) stored,
    image         varchar(255)
);

delimiter //

create procedure add_bus(
    in p_license_plate varchar(20),
    in p_bus_type enum ('VIP', 'LUXURY', 'NORMAL'),
    in p_row_seat int,
    in p_col_seat int,
    in p_image varchar(255)
)
begin
    insert into bus (license_plate, bus_type, row_seat, col_seat, image)
    values (p_license_plate, p_bus_type, p_row_seat, p_col_seat, p_image);
end //

create procedure update_bus(
    in p_id int,
    in p_license_plate varchar(20),
    in p_bus_type enum ('VIP', 'LUXURY', 'NORMAL'),
    in p_row_seat int,
    in p_col_seat int,
    in p_image varchar(255)
)
begin
    update bus
    set license_plate = p_license_plate,
        bus_type      = p_bus_type,
        row_seat      = p_row_seat,
        col_seat      = p_col_seat,
        image         = p_image
    where id = p_id;
end //

create procedure delete_bus(in p_id int)
begin
    delete from bus where id = p_id;
end //

create procedure get_all_bus()
begin
    select * from bus;
end //

create procedure get_bus_by_id(in p_id int)
begin
    select * from bus where id = p_id;
end //

create procedure check_license_plate_unique(
    in p_id int,
    in p_license_plate varchar(20)
)
begin
    select count(*) as cnt
    from bus
    where license_plate = p_license_plate
      and id <> p_id;
end //

delimiter ;

insert into bus (license_plate, bus_type, row_seat, col_seat, image)
values ('29a-12345', 'vip', 10, 4, 'https://example.com/vip1.jpg'),
       ('30b-67890', 'luxury', 12, 4, 'https://example.com/luxury1.jpg'),
       ('31c-11111', 'normal', 8, 4, 'https://example.com/normal1.jpg');

