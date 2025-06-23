CREATE DATABASE `practice_after_exam`;
USE `practice_after_exam`;

--
-- PHẦN 2: Thiết kế cơ sở dữ liệu
--
-- 1. Thiết kế cơ sở dữ liệu theo ERD về quản lý đặt vé máy bay trên
CREATE TABLE `flights`(
	`flight_id` VARCHAR(10),
    `airline_name` VARCHAR(100) NOT NULL,
    `departure_airport` VARCHAR(100) NOT NULL,
    `arrival_airport` VARCHAR(100) NOT NULL,
    `departure_time` DATETIME NOT NULL,
    `arrival_time` DATETIME NOT NULL,
    `ticket_price` FLOAT NOT NULL,
    PRIMARY KEY (`flight_id`)
);

CREATE TABLE `passengers`(
	`passenger_id` VARCHAR(10),
    `passenger_full_name` VARCHAR(150) NOT NULL,
    `passenger_email` VARCHAR(250) UNIQUE,
    `passenger_phone` VARCHAR(15) UNIQUE,
    `passenger_bod` DATE,
    PRIMARY KEY (`passenger_id`)
);

CREATE TABLE `booking`(
	`booking_id` INT AUTO_INCREMENT,
    `passenger_id` VARCHAR(10) NOT NULL,
    `flight_id` VARCHAR(10) NOT NULL,
    `booking_date` DATE,
    `booking_status` ENUM('Confirmed', 'Cancelled', 'Pending'),
    PRIMARY KEY (`booking_id`),
    CONSTRAINT `flights_fk`
    FOREIGN KEY (`flight_id`)
    REFERENCES `flights`(`flight_id`)
    ON DELETE CASCADE,
    CONSTRAINT `passengers_fk`
    FOREIGN KEY (`passenger_id`)
    REFERENCES `passengers`(`passenger_id`)
    ON DELETE CASCADE
);

CREATE TABLE `payment`(
	`payment_id` INT AUTO_INCREMENT,
    `booking_id` INT NOT NULL,
    `payment_method` ENUM('Credit Card', 'Bank Transfer', 'Cash'),
    `payment_amount` FLOAT,
    `payment_date` DATE,
    `payment_status` ENUM('Success', 'Failed', 'Pending'),
    PRIMARY KEY (`payment_id`),
    CONSTRAINT `booking_fk`
    FOREIGN KEY (`booking_id`)
    REFERENCES `booking`(`booking_id`)
    ON DELETE CASCADE
);

-- 2. Thêm cột passenger_gender có kiểu dữ liệu là enum với các giá trị 'Nam', 'Nữ',
-- 'Khác' trong bảng Passenger.
ALTER TABLE `passengers`
ADD COLUMN `passenger_gender` ENUM('Nam', 'Nữ', 'Khác');

-- 3. Thêm cột ticket_quantity trong bảng Booking có kiểu dữ liệu là integer, có rằng
-- buộc NOT NULL và giá trị mặc định là 1. Cột này thể hiện số lượng vé mà hành
-- khách đã đặt
ALTER TABLE `booking`
ADD `ticket_quantity` INT NOT NULL DEFAULT 1;

-- 4. Thêm rằng buộc cho cột payment_amount trong bảng Payment phải có giá trị lớn
-- hơn 0 và có kiểu dữ liệu là DECIMAL(10, 2).
ALTER TABLE `payment`
MODIFY `payment_amount` DECIMAL(10, 2);

ALTER TABLE `payment`
ADD CONSTRAINT `check_payment_amount`
CHECK (`payment_amount` > 0);

--
-- PHẦN 3: Thao tác với dữ liệu các bảng
--
-- 1. Thêm dữ liệu vào các bảng theo yêu cầu
INSERT INTO `passengers`
VALUES
('P0001', 'Nguyen Anh Tuan', 'tuan.nguyen@examle.com', '0901234567', '1995-05-15', 'Nam'),
('P0002', 'Tran Thi Mai', 'mai.tran@example.com', '0912345678', '1996-06-16', 'Nu'),
('P0003', 'Le Minh Tuan', 'tuan.le@example.com', '0923456789', '1997-07-17', 'Nam'),
('P0004', 'Pham Hong Son', 'son.pham@example.com ', '0934567890', '1998-08-18', 'Nam'),
('P0005', 'Nguyen Thi Lan', 'lan.nguyen@example.com', '0945678901', '1999-09-19', 'Nu'),
('P0006', 'Vu Thi Bao', 'bao.vu@example.com', '0956789012', '2000-10-20', 'Nu'),
('P0007', 'Doan Minh Hoang', 'hoang.doan@example.com', '0967890123', '2001-11-21', 'Nam'),
('P0008', 'Nguyen Thi Thanh', 'thanh.nguyen@example.com', '0978901234', '2002-12-22', 'Nu'),
('P0009', 'Trinh Bao Vy', 'vy.trinh@example.com ', '0989012345', '2003-01-23', 'Nu'),
('P0010', 'Bui Hoang Nam', 'nam.bui@example.com', '0990123456', '2004-02-24', 'Nam');

INSERT INTO `flights`
VALUES
('F001', 'VietJetAir', 'Tan Son Nhat', 'Nha Trang', '2025-03-01 08:00:00', '2025-03-01 10:00:00', 150.5),
('F002', 'VietnamAirlines', 'Noi Bai', 'Ha Noi', '2025-03-01 09:00:00', '2025-03-01 11:30:00', 200.0),
('F003', 'BambooAirways', 'Da Nang', 'Phu Quoc', '2025-03-01 10:00:00', '2025-03-01 12:00:00', 120.8),
('F004', 'VietravelAirlines', 'Can Tho', 'Ho Chi Minh', '2025-03-01 11:00:00', '2025-03-01 12:30:00', 180.0);

INSERT INTO `booking` (`passenger_id`, `flight_id`, `booking_date`, `booking_status`, `ticket_quantity`)
VALUES
('P0001', 'F001', '2025-02-20', 'Confirmed', 1),
('P0002', 'F002', '2025-02-21', 'Cancelled', 2),
('P0003', 'F003', '2025-02-22', 'Pending', 1),
('P0004', 'F004', '2025-02-23', 'Confirmed', 3),
('P0005', 'F001', '2025-02-24', 'Pending', 1),
('P0006', 'F002', '2025-02-25', 'Confirmed', 2),
('P0007', 'F003', '2025-02-26', 'Cancelled', 1),
('P0008', 'F004', '2025-02-27', 'Pending', 4),
('P0009', 'F001', '2025-02-28', 'Confirmed', 1),
('P0010', 'F002', '2025-02-28', 'Pending', 1),
('P0001', 'F003', '2025-03-01', 'Confirmed', 3),
('P0002', 'F004', '2025-03-02', 'Cancelled', 1),
('P0003', 'F001', '2025-03-03', 'Pending', 2),
('P0004', 'F002', '2025-03-04', 'Confirmed', 1),
('P0005', 'F003', '2025-03-05', 'Cancelled', 2),
('P0006', 'F004', '2025-03-06', 'Pending', 1),
('P0007', 'F001', '2025-03-07', 'Confirmed', 3),
('P0008', 'F002', '2025-03-08', 'Cancelled', 2),
('P0009', 'F003', '2025-03-09', 'Pending', 1),
('P0010', 'F004', '2025-03-10', 'Confirmed', 1);

INSERT INTO `payment` (`booking_id`, `payment_method`, `payment_amount`, `payment_date`, `payment_status`)
VALUES
(1, 'Credit Card', 150.5, '2025-02-20', 'Success'),
(2, 'Bank Transfer', 200.0, '2025-02-21', 'Failed'),
(3, 'Cash', 120.8, '2025-02-22', 'Pending'),
(4, 'Credit Card', 180.0, '2025-02-23', 'Success'),
(5, 'Bank Transfer', 150.5, '2025-02-24', 'Pending'),
(6, 'Cash', 200.0, '2025-02-25', 'Success'),
(7, 'Credit Card', 120.8, '2025-02-26', 'Failed'),
(8, 'Bank Transfer', 180.0, '2025-02-27', 'Pending'),
(9, 'Cash', 150.5, '2025-02-28', 'Success'),
(10, 'Credit Card', 200.0, '2025-03-01', 'Pending');

-- 2. Viết câu UPDATE cho phép cập nhật trạng thái thanh toán trong bảng Payment:
--  Cập nhật trạng thái thanh toán thành "Success" nếu số tiền thanh toán
-- (payment_amount) > 0 và phương thức thanh toán là "Credit Card".
-- Cập nhật trạng thái thanh toán thành "Pending" nếu phương thức thanh toán là
-- "Bank Transfer" và số tiền thanh toán nhỏ hơn 100.
-- Chỉ cập nhật trạng thái thanh toán cho những giao dịch có ngày thanh toán
-- (payment_date) là trước ngày hiện tại(CURRENT_DATE)
UPDATE `payment`
SET `payment_status` = 'Success'
WHERE `payment_amount` > 0
	AND `payment_method` = 'Credit Card'
	AND `payment_date` < CURRENT_DATE;

UPDATE `payment`
SET `payment_status` = 'Pending'
WHERE `payment_amount` < 100 
	AND `payment_method` = 'Bank Transfer'
    AND `payment_date` < CURRENT_DATE;

--
-- PHẦN 4: Truy vấn dữ liệu
--
-- 1. Lấy thông tin 5 hành khách gồm mã, tên, email, ngày sinh, và giới tính, sắp xếp theo tên
-- hành khách tăng dần
SELECT `passenger_id`,`passenger_full_name`, `passenger_email`, `passenger_bod`, `passenger_gender`
FROM `passengers`
ORDER BY `passenger_full_name` ASC
LIMIT 5;

-- 2. Lấy thông tin các chuyến bay gồm mã, tên hãng hàng không, sân bay khởi hành và sân bay
-- đến, sắp xếp theo giá vé giảm dần
SELECT `flight_id`,`airline_name`, `departure_airport`, `arrival_airport`
FROM `flights`
ORDER BY `ticket_price` DESC;

-- 3. Lấy thông tin các hành khách gồm mã hành khách, tên hành khách, chuyến bay đã đặt và
-- trạng thái vé là "Cancelled"
SELECT p.`passenger_id`, p.`passenger_full_name`, b.`flight_id`
FROM `passengers` p
JOIN `booking` b ON b.`passenger_id` = p.`passenger_id`
WHERE b.`booking_status` = 'Cancelled';

-- 4. Lấy danh sách các chuyến bay gồm mã vé, mã hành khách, chuyến bay đã đặt, và số lượng
-- vé cho các chuyến bay có trạng thái "Confirmed", sắp xếp theo số lượng vé giảm dần
SELECT `booking_id`, `passenger_id`, `flight_id`, `ticket_quantity`
FROM `booking`
WHERE `booking_status` = 'Confirmed'
ORDER BY `ticket_quantity` DESC;

-- 5. Lấy danh sách các hành khách gồm mã vé, tên hành khách, chuyến bay đã đặt, và số lượng
-- vé cho các hành khách có số lượng vé đặt trong khoảng từ 2 đến 3, sắp xếp theo tên hành khách
SELECT b.`booking_id`, p.`passenger_full_name`, b.`flight_id`, b.`ticket_quantity`
FROM `booking` b
JOIN `passengers` p ON p.`passenger_id` = b.`passenger_id`
WHERE b.`ticket_quantity` IN (2, 3)
ORDER BY p.`passenger_full_name`;

-- 6. Lấy danh sách các hành khách đã đặt ít nhất 2 vé và có trạng thái thanh toán là "Pending",
-- gồm mã hành khách, tên hành khách và số lượng vé đã đặt
SELECT DISTINCT p.`passenger_id`, p.`passenger_full_name`, SUM(b.`ticket_quantity`) AS 'tổng số vé'
FROM `booking` b
JOIN `passengers` p ON p.`passenger_id` = b.`passenger_id`
JOIN `payment` pa ON pa.`booking_id` = b.`booking_id`
WHERE pa.`payment_status` = 'Pending'
GROUP BY p.`passenger_id`, p.`passenger_full_name`
HAVING SUM(b.`ticket_quantity`) >= 2;

-- 7. Lấy danh sách các hành khách gồm mã hành khách, tên hành khách và số tiền thanh toán
-- cho các giao dịch có trạng thái thanh toán "Success"
SELECT p.`passenger_id`, p.`passenger_full_name`, pa.`payment_amount`
FROM `booking` b
JOIN `passengers` p ON p.`passenger_id` = b.`passenger_id`
JOIN `payment` pa ON pa.`booking_id` = b.`booking_id`
WHERE pa.`payment_status` = 'Success';

-- 8. Lấy danh sách 5 hành khách đầu tiên có số lượng vé đặt (ticket_quantity) lớn hơn 1, sắp
-- xếp theo số lượng vé giảm dần, gồm mã hành khách, tên hành khách, số lượng vé và trạng
-- thái vé
SELECT p.`passenger_id`, p.`passenger_full_name`, b.`ticket_quantity`, b.`booking_status`
FROM `passengers` p
JOIN `booking` b ON b.`passenger_id` = p.`passenger_id`
WHERE b.`ticket_quantity` > 1
ORDER BY b.`ticket_quantity` DESC
LIMIT 5;

-- 9. Lấy danh sách các chuyến bay có số lượng vé đặt nhiều nhất, gồm mã chuyến bay, tên
-- hãng hàng không, và số lượng vé đặt
SELECT f.`flight_id`, f.`airline_name`, SUM(b.`ticket_quantity`) AS total_ticket
FROM `flights` f
JOIN `booking` b ON b.`flight_id` = f.`flight_id`
GROUP BY f.`flight_id`, f.`airline_name`
HAVING total_ticket = (
	SELECT MAX(tsl)
    FROM (
		SELECT `flight_id`, SUM(`ticket_quantity`) AS tsl
		FROM `booking`
        GROUP BY `flight_id`
    ) AS ticket_sum
);

SELECT f.`flight_id`, f.`airline_name`, SUM(b.`ticket_quantity`) AS total_ticket
FROM `flights` f
JOIN `booking` b ON b.`flight_id` = f.`flight_id`
GROUP BY f.`flight_id`, f.`airline_name`
HAVING total_ticket >= ALL (
	SELECT SUM(`ticket_quantity`)
	FROM `booking`
	GROUP BY `flight_id`
);

-- 10. Lấy danh sách các hành khách gồm tên hành khách, số tiền thanh toán,trạng thái thanh
-- toán cho những hành khách có ngày sinh trước năm 2000, sắp xếp theo tên hành khách
SELECT p.`passenger_full_name`, pa.`payment_amount`, pa.`payment_status`
FROM `booking` b
JOIN `passengers` p ON p.`passenger_id` = b.`passenger_id`
JOIN `payment` pa ON pa.`booking_id` = b.`booking_id`
WHERE p.`passenger_bod` < '2000-01-01'
ORDER BY p.`passenger_full_name`;

--
-- PHẦN 5: Tạo View
--
-- 1. Tạo view view_all_passenger_booking để lấy danh sách tất cả các hành khách và vé họ
-- đã đặt, gồm mã hành khách, tên hành khách, mã vé, mã chuyến bay và số lượng vé đã đặt
CREATE VIEW `view_all_passenger_booking` AS
SELECT p.`passenger_id`, p.`passenger_full_name`, b.`flight_id`
FROM `passengers` p
JOIN `booking` b ON b.`passenger_id` = p.`passenger_id`;

SELECT * FROM `view_all_passenger_booking`;

-- 2. Tạo view view_successful_payment để lấy danh sách các hành khách đã thanh toán thành
-- công, gồm mã hành khách, tên hành khách và số tiền thanh toán, chỉ lấy các giao dịch có
-- trạng thái thanh toán "Success"
CREATE VIEW `view_successful_payment` AS
SELECT p.`passenger_id`, p.`passenger_full_name`, pa.`payment_amount`
FROM `passengers` p
JOIN `booking` b ON b.`passenger_id` = p.`passenger_id`
JOIN `payment` pa ON pa.`booking_id` = b.`booking_id`
WHERE pa.`payment_status` = 'Success';

SELECT * FROM `view_successful_payment`;

--
-- PHẦN 6: Tạo Trigger
-- 
-- 1. Tạo một trigger để kiểm tra và đảm bảo rằng số lượng vé (ticket_quantity) trong bảng
-- Booking không bị giảm xuống dưới 1 khi có sự thay đổi. Nếu số lượng vé nhỏ hơn 1, trigger
-- sẽ thông báo lỗi SIGNAL SQLSTATE và không cho phép cập nhật.
DELIMITER //
CREATE TRIGGER check_ticket
BEFORE UPDATE ON `booking`
FOR EACH ROW
BEGIN
	IF NEW.`ticket_quantity` < 1 THEN
		SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'NUMBER OF TICKETS CANNOT BE LESS THAN 1';
	END IF;
END //
DELIMITER ;

-- 2. Tạo một trigger để khi thực hiện chèn dữ liệu vào bảng Payment, sẽ tự động kiểm tra trạng
-- thái thanh toán, nếu như trạng thái thanh toán là “Success” thì tiến hành cập nhật trạng thái
-- booking_status của ở bảng Booking tương ứng với hóa đơn đó thành “Confirmed”
DELIMITER //
CREATE TRIGGER check_payment_status
AFTER INSERT ON `payment`
FOR EACH ROW
BEGIN
	IF NEW.`payment_status` = 'Success' THEN
		UPDATE `booking`
		SET `booking_status` = 'Pending'
        WHERE `booking_id` = NEW.`booking_id`;
    END IF;
END //
DELIMITER ;
DROP TRIGGER check_payment_status;
INSERT INTO `booking` (`passenger_id`, `flight_id`, `booking_date`, `booking_status`, `ticket_quantity`)
VALUES
('P0004', 'F001', '2025-06-26', null, 1);
INSERT INTO `payment` (`booking_id`, `payment_method`, `payment_amount`, `payment_date`, `payment_status`)
VALUES
(25, 'Bank Transfer', 200.5, '2025-02-20', 'Success');

--
-- PHẦN 7: Tạo Store Procedure
--
-- 1. Tạo một stored procedure có tên GetAllPassengerBookings để lấy thông tin tất cả các
-- hành khách và vé họ đã đặt, bao gồm mã hành khách, tên hành khách, mã vé, mã chuyến bay
-- và số lượng vé
DELIMITER //
CREATE PROCEDURE GetAllPassengerBookings()
BEGIN
	SELECT p.`passenger_id`, p.`passenger_full_name`, b.`flight_id`
	FROM `passengers` p
	JOIN `booking` b ON b.`passenger_id` = p.`passenger_id`;
END //
DELIMITER ;

CALL GetAllPassengerBookings();

-- 2. Tạo một stored procedure có tên UpdateBooking để thực hiện thao tác cập nhật một bản
-- ghi trong vào bảng Booking dựa theo khóa chính.
DELIMITER //
CREATE PROCEDURE UpdateBooking(
	IN p_booking_id INT,
	IN p_passenger_id VARCHAR(10),
	IN p_flight_id VARCHAR(10),
	IN p_ticket_quantity INT
)
BEGIN
	UPDATE `booking`
    SET `passenger_id` = p_passenger_id,
		`flight_id` = p_flight_id,
        `ticket_quantity` = p_ticket_quantity
	WHERE `booking_id` = p_booking_id;
END //
DELIMITER ;

CALL UpdateBooking(1, 'P0001', 'F001', 6);