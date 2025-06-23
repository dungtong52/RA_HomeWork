DROP DATABASE `session03_db`;

CREATE DATABASE `session03_db`;
USE `session03_db`;

CREATE TABLE `suppliers`(
	`supplierID` INT,
    `supplierName` VARCHAR(100) NOT NULL,
    `contactEmail` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`supplierID`)
);

CREATE TABLE `products`(
	`productID` INT,
    `productName` VARCHAR(100) NOT NULL,
    `supplierID` INT,
    `price` DECIMAL(10, 2) NOT NULL,
    `stock` INT NOT NULL,
    PRIMARY KEY (`productID`),
    CONSTRAINT `supplier_product_fk`
    FOREIGN KEY (`supplierID`)
    REFERENCES `suppliers`(`supplierID`)
    ON DELETE CASCADE
);

-- Insert
INSERT INTO `suppliers`
VALUES
(1, 'Vinfast', 'vinfast@gmail.com'),
(2, 'Honda', 'honda@gmail.com'),
(3, 'Toyota', 'toyota@gmail.com');

INSERT INTO `products`
VALUES
(1, 'camry', 3, 30000, 10),
(2, 'vf-9', 1, 40000, 15),
(3, 'vf-8', 1, 35000, 13),
(4, 'cr-v', 2, 25000, 5);

-- UPDATE
UPDATE `products` SET `price` = 45.99 WHERE `productID` = 2;
UPDATE `suppliers` SET `supplierName` = 'BYD' WHERE `supplierID` = 1;

-- DELETE
DELETE FROM `suppliers` WHERE `supplierID` = 3;
DELETE FROM `products` WHERE `productID` = 4;

-- Query
SELECT * FROM `products`;
SELECT * FROM `suppliers`;