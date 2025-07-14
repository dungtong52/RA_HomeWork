create database banking_system_db;
use banking_system_db;

create table account (
    id int auto_increment primary key,
    account_number varchar(20) not null,
    balance decimal(10, 2) not null
);

create table transaction (
    id int auto_increment primary key,
    from_account_id int,
    to_account_id int,
    amount decimal(10, 2) not null,
    transaction_date datetime not null,
    foreign key (from_account_id) references account(id) on delete cascade,
    foreign key (to_account_id) references account(id) on delete cascade
);
