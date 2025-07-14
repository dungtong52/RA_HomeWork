CREATE DATABASE accounts_manager_db;
USE accounts_manager_db;

CREATE TABLE accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0
);

DELIMITER $$

CREATE PROCEDURE transfer_funds (
    IN p_from_account_id INT,
    IN p_to_account_id INT,
    IN p_amount DECIMAL(15,2)
)
BEGIN
    DECLARE from_balance DECIMAL(15,2);
    
    -- Kiểm tra số dư tài khoản nguồn
    SELECT balance INTO from_balance
    FROM accounts
    WHERE id = p_from_account_id;

    -- Nếu không đủ tiền thì báo lỗi
    IF from_balance < p_amount THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Số dư tài khoản không đủ để chuyển tiền';
    END IF;

    -- Trừ tiền từ tài khoản nguồn
    UPDATE accounts
    SET balance = balance - p_amount
    WHERE id = p_from_account_id;

    -- Cộng tiền vào tài khoản đích
    UPDATE accounts
    SET balance = balance + p_amount
    WHERE id = p_to_account_id;
END $$

DELIMITER ;