create database cinema_management_db;
use cinema_management_db;

create table movies
(
    movie_id     int primary key auto_increment,
    title        varchar(255),
    director     varchar(255),
    duration     int,
    release_date date,
    status       bit
);

create table showtimes
(
    showtime_id     int primary key auto_increment,
    movie_id        int,
    show_time       datetime,
    total_seats     int,
    available_seats int,
    status          bit,
    foreign key (movie_id) references movies (movie_id)
);

create table tickets
(
    ticket_id     int primary key auto_increment,
    showtime_id   int,
    seat_number   varchar(255),
    customer_name varchar(255),
    foreign key (showtime_id) references showtimes (showtime_id)
);

# PROCEDURE FOR MOVIE

DELIMITER //
create procedure create_movie(
    in_title varchar(255),
    in_director varchar(255),
    in_duration int,
    in_release_date date,
    in_status bit
)
begin
    insert into movies(title, director, duration, release_date, status)
    values (in_title, in_director, in_duration, in_release_date, in_status);
end //

create procedure check_exist_movie(in_id int)
begin
    select 1 from movies where movie_id = in_id;
end //

create procedure update_movie(
    in_id int,
    in_title varchar(255),
    in_director varchar(255),
    in_duration int,
    in_release_date date,
    in_status bit
)
begin
    update movies
    set title        = in_title,
        director     = in_director,
        duration     = in_duration,
        release_date = in_release_date,
        status       = in_status
    where movie_id = in_id;
end;

create procedure delete_movie(in_id int)
begin
    declare d_movie_id int;
    -- Lấy movieId có ràng buộc không
    select s.showtime_id
    into d_movie_id
    from showtimes s
    where movie_id = in_id
    limit 1;

    if (d_movie_id) is null then
        delete from movies where movie_id = in_id;
    else
        update movies set status = 0 where movie_id = in_id;
    end if;
end //

create procedure get_all_movie()
begin
    select * from movies;
end //

DELIMITER ;

# PROCEDURE FOR SHOWTIME
DELIMITER //
create procedure create_showtime(
    in_movie_id int,
    in_show_time datetime,
    in_total_seats int,
    in_available_seats int,
    in_status bit
)
begin
    insert into showtimes(movie_id, show_time, total_seats, available_seats, status)
    values (in_movie_id, in_show_time, in_total_seats, in_available_seats, in_status);
end //

create procedure check_exist_showtime(in_id int)
begin
    select 1 from showtimes where showtime_id = in_id;
end //

create procedure update_showtime(
    in_id int,
    in_movie_id int,
    in_show_time datetime,
    in_total_seats int,
    in_available_seats int,
    in_status bit
)
begin
    update showtimes
    set movie_id        = in_movie_id,
        show_time       = in_show_time,
        total_seats     = in_total_seats,
        available_seats = in_available_seats,
        status          = in_status
    where showtime_id = in_id;
end;

create procedure delete_showtime(in_id int)
begin
    declare d_showtime_id int;
    -- Lấy showtimeId có ràng buộc không
    select t.ticket_id
    into d_showtime_id
    from tickets t
    where t.showtime_id = in_id
    limit 1;

    if (d_showtime_id) is null then
        delete from showtimes where showtime_id = in_id;
    else
        update showtimes set status = 0 where showtime_id = in_id;
    end if;
end //

create procedure get_showtime_by_movie(in_movie_id int)
begin
    declare i_status bit;
    select status into i_status from movies m where movie_id = in_movie_id;

    if (i_status = 1) then
        select s.showtime_id, s.available_seats
        from showtimes s
        where s.movie_id = in_movie_id
          and s.status = 1;
    end if;
end //

DELIMITER ;

# PROCEDURE FOR TICKET
DELIMITER //
create procedure create_ticket(
    in_showtime_id int,
    in_seat_number varchar(255),
    in_customer_name varchar(255)
)
begin
    declare c_available_seats int;
    select s.available_seats into c_available_seats from showtimes s where showtime_id = in_showtime_id;

    if (c_available_seats = 0) then
        signal sqlstate "45000" set message_text = "Đã hết ghế!";
    else
        insert into tickets(showtime_id, seat_number, customer_name)
        values (in_showtime_id, in_seat_number, in_customer_name);

        update showtimes s
        set s.available_seats = s.available_seats - 1
        where showtime_id = in_showtime_id;
    end if;
end //

create procedure get_ticket(in_showtime_id int)
begin
    select t.* from tickets t where t.showtime_id = in_showtime_id;
end //

DELIMITER ;