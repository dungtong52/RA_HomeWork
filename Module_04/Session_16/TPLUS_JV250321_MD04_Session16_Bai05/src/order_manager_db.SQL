CREATE DATABASE order_manager_db;
USE order_manager_db;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255),
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    total_amount DECIMAL(15,2),
    product_id INT,
    quantity INT
);

DELIMITER $$

CREATE PROCEDURE place_order (
    IN p_customer_id INT,
    IN p_product_id INT,
    IN p_quantity INT
)
BEGIN
    DECLARE current_stock INT;
    DECLARE unit_price DECIMAL(15,2);
    DECLARE total DECIMAL(15,2);

    -- Kiểm tra tồn tại sản phẩm
    SELECT stock INTO current_stock
    FROM products
    WHERE id = p_product_id;

    IF current_stock IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Sản phẩm không tồn tại';
    END IF;

    -- Kiểm tra đủ hàng không
    IF current_stock < p_quantity THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không đủ hàng trong kho';
    END IF;

    -- Cập nhật tồn kho
    UPDATE products
    SET stock = stock - p_quantity
    WHERE id = p_product_id;

    -- Tính tổng tiền
    SET total = unit_price * p_quantity;

    -- Thêm đơn hàng
    INSERT INTO orders (customer_id, total_amount, product_id, quantity)
    VALUES (p_customer_id, total, p_product_id, p_quantity);
END $$

DELIMITER ;
