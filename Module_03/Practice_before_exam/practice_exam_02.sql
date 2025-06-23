--
-- PHẦN 2: Thiết kế cơ sở dữ liệu
--
-- 1. Thiết kế cơ sở dữ liệu theo ERD trên.
CREATE DATABASE `practice_exam_02`;
USE `practice_exam_02`;

CREATE TABLE `customers`(
	`customer_id` VARCHAR(10),
    `customer_full_name` VARCHAR(150) NOT NULL,
    `customer_email` VARCHAR(255) NOT NULL UNIQUE,
    `customer_address` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`customer_id`)
);
CREATE TABLE `rooms`(
	`room_id` VARCHAR(10),
    `room_price` FLOAT,
    `room_status` ENUM('Available', 'Booked'),
    `room_area` INT,
    PRIMARY KEY (`room_id`)
);

CREATE TABLE `booking`(
	`booking_id` INT AUTO_INCREMENT,
    `customer_id` VARCHAR(10) NOT NULL,
    `room_id` VARCHAR(10) NOT NULL,
    `check_in_date` DATE NOT NULL,
    `check_out_date` DATE NOT NULL,
    `total_amount` FLOAT,
    PRIMARY KEY (`booking_id`),
    CONSTRAINT `fk_customers_booking`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`customer_id`),
    CONSTRAINT `fk_rooms_booking`
    FOREIGN KEY (`room_id`)
    REFERENCES `rooms`(`room_id`)
);
CREATE TABLE `payment`(
	`payment_id` INT AUTO_INCREMENT,
    `booking_id` INT NOT NULL,
    `payment_method` VARCHAR(50) NOT NULL,
    `payment_date` DATE NOT NULL,
    `payment_amount` FLOAT NOT NULL,
    PRIMARY KEY (`payment_id`),
    CONSTRAINT `fk_booking_payment`
    FOREIGN KEY (`booking_id`)
    REFERENCES `booking`(`booking_id`)
);

-- 2. Thêm cột room_type có kiểu dữ liệu là enum gồm các giá trị "single", "double", "suite" trong bảng Room.
ALTER TABLE `rooms`
ADD COLUMN `room_type` ENUM('single', 'double', 'suite');

-- 3. Thêm cột số điện thoại khách hàng (customer_phone) trong bảng Customer có kiểu dữ liệu char(15), có rằng buộc not null và unique.
ALTER TABLE `customers`
ADD COLUMN `customer_phone` VARCHAR(15) NOT NULL UNIQUE;

-- 4. Thêm ràng buộc cho cột total_amount trong bảng Booking phải có giá trị lớn hơn hoặc bằng 0.
ALTER TABLE `booking`
ADD CONSTRAINT `check_above_0` CHECK (`total_amount` >= 0);

-- 
-- PHẦN 3: Thao tác với dữ liệu các bảng
-- 
-- 1. Thêm dữ liệu vào các bảng theo
INSERT INTO `customers`(`customer_id`, `customer_full_name`, `customer_email`, `customer_phone`, `customer_address`)
VALUES
('C001', 'Nguyen Anh Tu', 'tu.nguyen@example.com', '0912345678', 'Hanoi, Vietnam'),
('C002', 'Tran Thi Mai', 'mai.tran@example.com', '0923456789', 'Ho Chi Minh, Vietnam'),
('C003', 'Le Minh Hoang', 'hoang.le@example.com', '0934567890', 'Danang, Vietnam'),
('C004', 'Pham Hoang Nam', 'nam.pham@example.com', '0945678901', 'Hue, Vietnam'),
('C005', 'Vu Minh Thu', 'thu.vu@example.com', '0956789012', 'Hai Phong, Vietnam'),
('C006', 'Nguyen Thi Lan', 'lan.nguyen@example.com', '0967890123', 'Quang Ninh, Vietnam'),
('C007', 'Bui Minh Tuan', 'tuan.bui@example.com', '0978901234', 'Bac Giang, Vietnam'),
('C008', 'Pham Quang Hieu', 'hieu.pham@example.com', '0989012345', 'Quang Nam, Vietnam'),
('C009', 'Le Thi Lan', 'lan.le@example.com', '0990123456', 'Da Lat, Vietnam'),
('C010', 'Nguyen Thi Mai', 'mai.nguyen@example.com', '0901234567', 'Can Tho, Vietnam');

INSERT INTO `rooms`(`room_id`, `room_type`, `room_price`, `room_status`, `room_area`)
VALUES
('R001', 'Single', 100.0, 'Available', 25),
('R002', 'Double', 150.0, 'Booked', 40),
('R003', 'Suite', 250.0, 'Available', 60),
('R004', 'Single', 120.0, 'Booked', 30),
('R005', 'Double', 160.0, 'Available', 35);

INSERT INTO `booking` (`customer_id`, `room_id`, `check_in_date`, `check_out_date`, `total_amount`)
VALUES
('C001', 'R001', '2025-03-01', '2025-03-05', 400.0),
('C002', 'R002', '2025-03-02', '2025-03-06', 600.0),
('C003', 'R003', '2025-03-03', '2025-03-07', 1000.0),
('C004', 'R004', '2025-03-04', '2025-03-08', 480.0),
('C005', 'R005', '2025-03-05', '2025-03-09', 800.0),
('C006', 'R001', '2025-03-06', '2025-03-10', 400.0),
('C007', 'R002', '2025-03-07', '2025-03-11', 600.0),
('C008', 'R003', '2025-03-08', '2025-03-12', 1000.0),
('C009', 'R004', '2025-03-09', '2025-03-13', 480.0),
('C010', 'R005', '2025-03-10', '2025-03-14', 800.0);

INSERT INTO `payment` (`booking_id`, `payment_method`, `payment_date`, `payment_amount`)
VALUES
(1, 'Cash', '2025-03-05', 400.0),
(2, 'Credit Card', '2025-03-06', 600.0),
(3, 'Bank Transfer', '2025-03-07', 1000.0),
(4, 'Cash', '2025-03-08', 480.0),
(5, 'Credit Card', '2025-03-09', 800.0),
(6, 'Bank Transfer', '2025-03-10', 400.0),
(7, 'Cash', '2025-03-11', 600.0),
(8, 'Credit Card', '2025-03-12', 1000.0),
(9, 'Bank Transfer', '2025-03-13', 480.0),
(10, 'Cash', '2025-03-14', 800.0),
(1, 'Credit Card', '2025-03-15', 400.0),
(2, 'Bank Transfer', '2025-03-16', 600.0),
(3, 'Cash', '2025-03-17', 1000.0),
(4, 'Credit Card', '2025-03-18', 480.0),
(5, 'Bank Transfer', '2025-03-19', 800.0),
(6, 'Cash', '2025-03-20', 400.0),
(7, 'Credit Card', '2025-03-21', 600.0),
(8, 'Bank Transfer', '2025-03-22', 1000.0),
(9, 'Cash', '2025-03-23', 480.0),
(10, 'Credit Card', '2025-03-24', 800.0);

-- 2. Viết câu update cho phép cập nhật dữ liệu cho các khách hàng trong bảng Booking
	-- Công thức tính tổng tiền (total_amount) = giá phòng * số ngày lưu trú.
	-- Chỉ cập nhật tổng tiền khi trạng thái phòng là "Booked" và ngày nhận phòng
	-- (check_in_date) đã qua.
UPDATE `booking` b
JOIN `rooms` r ON r.`room_id` = b.`room_id`
SET b.`total_amount` = r.`room_price` * DATEDIFF(b.`check_out_date`, b.`check_in_date`)
WHERE r.`room_status` = 'Booked' AND b.`check_in_date` < CURRENT_DATE;

-- 3. Xóa các thanh toán trong bảng Payment nếu phương thức thanh toán là "Cash" và
-- tổng tiền thanh toán (payment_amount) nhỏ hơn 500.
DELETE FROM `payment`
WHERE `payment_method` = 'Cash' 
	AND `payment_amount` < 500;
    
--
-- PHẦN 4: Truy vấn dữ liệu
--
-- 1. Lấy thông tin khách hàng gồm mã khách hàng, họ tên, email, số điện thoại và địa chỉ được
-- sắp xếp theo họ tên khách hàng tăng dần.
SELECT *
FROM `customers`
ORDER BY `customer_full_name` ASC;

-- 2. Lấy thông tin các phòng khách sạn gồm mã phòng, loại phòng, giá phòng và diện tích
-- phòng, sắp xếp theo giá phòng giảm dần.
SELECT `room_id`, `room_type`, `room_price`, `room_area`
FROM `rooms`
ORDER BY `room_price` DESC;

-- 3. Lấy thông tin khách hàng và phòng khách sạn đã đặt, gồm mã khách hàng, họ tên khách
-- hàng, mã phòng, ngày nhận phòng và ngày trả phòng.
SELECT b.`customer_id`, c.`customer_full_name`, b.`room_id`, b.`check_in_date`, b.`check_out_date`
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`;

-- 4. Lấy danh sách khách hàng và tổng tiền đã thanh toán khi đặt phòng, gồm mã khách hàng,
-- họ tên khách hàng, phương thức thanh toán và số tiền thanh toán, sắp xếp theo số tiền thanh
-- toán giảm dần.
SELECT b.`customer_id`, c.`customer_full_name`, p.`payment_method`, SUM(p.`payment_amount`)
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`
JOIN `payment` p ON p.`booking_id` = b.`booking_id`
GROUP BY b.`customer_id`, c.`customer_full_name`, p.`payment_method`
ORDER BY SUM(p.`payment_amount`) DESC;

-- 5. Lấy thông tin khách hàng từ vị trí thứ 2 đến thứ 4 trong bảng Customer được sắp xếp theo
-- tên khách hàng.
SELECT *
FROM `customers`
ORDER BY `customer_full_name`
LIMIT 3 OFFSET 1;

-- 6. Lấy danh sách khách hàng đã đặt ít nhất 2 phòng và có tổng số tiền thanh toán trên 1000,
-- gồm mã khách hàng, họ tên khách hàng và số lượng phòng đã đặt.
SELECT b.`customer_id`, c.`customer_full_name`, COUNT(b.`booking_id`) AS SoLuongPhongDaDat
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`
GROUP BY b.`customer_id`
HAVING SoLuongPhongDaDat >= 2 
	AND SUM(b.`total_amount`) > 1000;

-- 7. Lấy danh sách các phòng có tổng số tiền thanh toán dưới 1000 và có ít nhất 3 khách hàng
-- đặt, gồm mã phòng, loại phòng, giá phòng và tổng số tiền thanh toán.
SELECT b.`room_id`, r.`room_type`, r.`room_price`, SUM(b.`total_amount`) AS TongSoTienThanhToan
FROM `booking` b
JOIN `rooms` r ON r.`room_id` = b.`room_id`
GROUP BY b.`room_id`
HAVING TongSoTienThanhToan < 1000 
	AND COUNT(b.`customer_id`) >= 3;

-- 8. Lấy danh sách các khách hàng có tổng số tiền thanh toán lớn hơn 1000, gồm mã khách
-- hàng, họ tên khách hàng, mã phòng, tổng số tiền thanh toán.
SELECT b.`customer_id`, c.`customer_full_name`, b.`room_id`, SUM(b.`total_amount`) AS TongSoTienThanhToan
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`
GROUP BY b.`customer_id`, b.`room_id`
HAVING TongSoTienThanhToan > 1000;

-- 9. Lấy danh sách các phòng có số lượng khách hàng đặt nhiều nhất và ít nhất, gồm mã phòng,
-- loại phòng và số lượng khách hàng đã đặt
SELECT b.`room_id`, r.`room_type`, COUNT(b.`customer_id`) AS 'Số lượng khách hàng đặt'
FROM `booking` b
JOIN `rooms` r ON r.`room_id` = b.`room_id`
GROUP BY b.`room_id`
HAVING COUNT(b.`customer_id`) >= ALL (
	SELECT COUNT(`customer_id`)
    FROM `booking`
    GROUP BY `room_id`
);

SELECT b.`room_id`, r.`room_type`, COUNT(b.`customer_id`) AS 'Số lượng khách hàng đặt'
FROM `booking` b
JOIN `rooms` r ON r.`room_id` = b.`room_id`
GROUP BY b.`room_id`
HAVING COUNT(b.`customer_id`) <= ALL (
	SELECT COUNT(`customer_id`)
    FROM `booking`
    GROUP BY `room_id`
);

-- 10. Lấy danh sách các khách hàng có tổng số tiền thanh toán của lần đặt phòng cao hơn số
-- tiền thanh toán trung bình của tất cả các khách hàng cho cùng phòng, gồm mã khách hàng, họ
-- tên khách hàng, mã phòng, tổng tiền thanh toán
SELECT b.`customer_id`, c.`customer_full_name`, b.`room_id`, b.`total_amount`
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`
JOIN(
	SELECT `room_id`, AVG(`total_amount`) AS avg_amount
	FROM `booking`
	GROUP BY `room_id`
) AS avg_table ON avg_table.`room_id` = b.`room_id`
WHERE b.`total_amount` > avg_table.`avg_amount`;

--
-- PHẦN 5: Tạo View
--
-- 1. Hãy tạo một view để lấy thông tin các phòng và khách hàng đã đặt, với điều kiện ngày
-- nhận phòng nhỏ hơn ngày 2025-03-10. Cần hiển thị các thông tin sau: Mã phòng, Loại
-- phòng, Mã khách hàng, họ tên khách hàng
CREATE VIEW `getRoomAndCustomerByCheckIn` AS
SELECT b.`room_id`, r.`room_type`, b.`customer_id`, c.`customer_full_name`
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`
JOIN `rooms` r ON r.`room_id` = b.`room_id`
WHERE b.`check_in_date` < '2025-03-10';

SELECT * FROM `getRoomAndCustomerByCheckIn`;

-- 2. Hãy tạo một view để lấy thông tin khách hàng và phòng đã đặt, với điều kiện diện tích
-- phòng lớn hơn 30 m². Cần hiển thị các thông tin sau: Mã khách hàng, Họ tên khách hàng, Mã
-- phòng, Diện tích phòng
CREATE VIEW `getRoomAndCustomerByArea` AS
SELECT b.`customer_id`, c.`customer_full_name`, b.`room_id`, r.`room_area`
FROM `booking` b
JOIN `customers` c ON c.`customer_id` = b.`customer_id`
JOIN `rooms` r ON r.`room_id` = b.`room_id`
WHERE r.`room_area` > 30;

SELECT * FROM `getRoomAndCustomerByArea`;

--
-- PHẦN 6: Tạo Trigger
--
-- 1. Hãy tạo một trigger check_insert_booking để kiểm tra dữ liệu mối khi chèn vào bảng
-- Booking. Kiểm tra nếu ngày đặt phòng mà sau ngày trả phòng thì thông báo lỗi với nội dung
-- “Ngày đặt phòng không thể sau ngày trả phòng được !” và hủy thao tác chèn dữ liệu vào
-- bảng.
DELIMITER //
CREATE TRIGGER check_insert_booking
BEFORE INSERT ON `booking`
FOR EACH ROW
BEGIN
	IF NEW.`check_in_date` > NEW.`check_out_date` THEN
		SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Ngày đặt phòng không thể sau ngày trả phòng được !';
	END IF;
END //
DELIMITER ;

-- 2. Hãy tạo một trigger có tên là update_room_status_on_booking để tự động cập nhật
-- trạng thái phòng thành "Booked" khi một phòng được đặt (khi có bản ghi được INSERT vào
-- bảng Booking).
DELIMITER //
CREATE TRIGGER update_room_status_on_booking
AFTER INSERT ON `booking`
FOR EACH ROW
BEGIN
	UPDATE `rooms`
    SET `room_status` = 'Booked'
	WHERE `room_id` = NEW.`room_id`;
END //
DELIMITER ;

--
-- PHẦN 7: Tạo Store Procedure
--
-- 1. Viết store procedure có tên add_customer để thêm mới một khách hàng với đầy đủ các
-- thông tin cần thiết.
DELIMITER //
CREATE PROCEDURE AddCustomer(
	IN customer_id VARCHAR(10),
    IN customer_full_name VARCHAR(150),
    IN customer_email VARCHAR(255),
    IN customer_phone VARCHAR(15),
    IN customer_address VARCHAR(255)
)
BEGIN
	INSERT INTO `customers`(`customer_id`, `customer_full_name`, `customer_email`, `customer_phone`, `customer_address`)
	VALUES (customer_id, customer_full_name, customer_email, customer_phone, customer_address);
END //
DELIMITER ;

CALL AddCustomer('C011', 'Le Thi Hien', 'hien.le@example.com', '0901234765', 'Dak Nong, Vietnam');

-- 2. Hãy tạo một Stored Procedure có tên là add_payment để thực hiện việc thêm một thanh
-- toán mới cho một lần đặt phòng.
DELIMITER //
CREATE PROCEDURE AddPayment(
	IN p_booking_id INT,
	IN p_payment_method VARCHAR(50),
	IN p_payment_amount FLOAT,
	IN p_payment_date DATE
)
BEGIN
	INSERT INTO `payment`(`booking_id`, `payment_method`, `payment_date`, `payment_amount`)
	VALUES (p_booking_id, p_payment_method, p_payment_date, p_payment_amount);
END //
DELIMITER ;