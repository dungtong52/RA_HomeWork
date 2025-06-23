-- LESSON_02
USE `CompanyDB`;
CREATE TABLE `Products`(
	`ProductID` INT NOT NULL,
    `ProductName` VARCHAR(100) NOT NULL,
    `Category` VARCHAR(100) NOT NULL,
    `Price` FLOAT NOT NULL,
    `StockQuantity` INT NOT NULL,
    PRIMARY KEY (`ProductID`)
);
