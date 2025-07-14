create database movie_management_db;
use movie_management_db;

create table movies
(
    movie_id    int primary key auto_increment,
    movie_title varchar(100) not null,
    director    varchar(100),
    year        int check ( year > 1888 )
);

delimiter //
create procedure get_all_movies()
begin
    select m.movie_id, m.movie_title, m.director, m.year
    from movies m;
end;
delimiter //

delimiter //
create procedure add_movie(
    in_title varchar(100),
    in_director varchar(100),
    in_year int
)
begin
    insert into movies(movie_title, director, year)
    values (in_title, in_director, in_year);
end;
delimiter //

delimiter //
create procedure update_movie(
    in_id int,
    in_title varchar(100),
    in_director varchar(100),
    in_year int
)
begin
    update movies m
    set m.movie_title = in_title,
        m.director    = in_director,
        m.year        = in_year
    where m.movie_id = in_id;
end;
delimiter //

delimiter //
create procedure delete_movie(in_id int)
begin
    delete from movies
    where movie_id = in_id;
end;
delimiter  //

delimiter //
create procedure find_movie_by_id(in_id int)
begin
    select m.movie_id, m.movie_title, m.director, m.year
    from movies m
    where movie_id = in_id;
end;
delimiter //


