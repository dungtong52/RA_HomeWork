create database banking_system_db;
use banking_system_db;

create table account
(
    id             int auto_increment primary key,
    account_number varchar(20)    not null,
    balance        decimal(10, 2) not null
);

create table account_transaction
(
    id               int auto_increment primary key,
    from_account_id  int,
    to_account_id    int,
    amount           decimal(10, 2) not null,
    transaction_date datetime       not null,
    foreign key (from_account_id) references account (id) on delete cascade,
    foreign key (to_account_id) references account (id) on delete cascade
);

delimiter //
create procedure isExists(in_account_id int)
begin
    select 1 from account where id = in_account_id;
end //

create procedure transfer_money(
    in_from_account_id int,
    in_to_account_id int,
    in_amount double
)
begin
    declare from_balance decimal(10, 2);
    set from_balance = (select a.balance from account a where id = in_from_account_id);

    if (from_balance < 0) then
        signal sqlstate "45000" set message_text = "Tài khoản âm!";
    elseif (from_balance < in_amount) then
        signal sqlstate "45000" set message_text = "Tài khoản không đủ!";
    end if;

    update account a set a.balance = a.balance - in_amount where id = in_from_account_id;
    update account a set a.balance = a.balance + in_amount where id = in_to_account_id;

    insert into account_transaction(from_account_id, to_account_id, amount, transaction_date)
    values (in_from_account_id, in_to_account_id, in_amount, Now());
end //

create procedure get_account_info(in_account_id int)
begin
    select *
    from account a
    where id = in_account_id;
end //

create procedure list_transactions(in_account_id int)
begin
    select * from account_transaction t where from_account_id = in_account_id;
end //

delimiter ;

INSERT INTO account (account_number, balance)
VALUES ('ACC1001', 1000.00),
       ('ACC1002', 1500.00),
       ('ACC1003', 2000.00);
