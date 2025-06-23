-- LESSON_04
USE `CompanyDB`;
CREATE TABLE `Customer`(
	`CustomerID` INT NOT NULL,
    `CustomerName` VARCHAR(100) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`CustomerID`)
);

CREATE TABLE `Order`(
	`OrderID` INT NOT NULL,
    `OrderDate` DATE NOT NULL,
    `CustomerID` INT NOT NULL,
    `TotalAmount` FLOAT NOT NULL,
    PRIMARY KEY (`OrderID`),
    CONSTRAINT `customer_order_fk`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `Customer`(`CustomerID`),
    CHECK (`TotalAmount` > 0)
);
