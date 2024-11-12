CREATE TABLE `orders`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_number` VARCHAR(255) NOT NULL,
  `sku_code` VARCHAR(255) NOT NULL,
  `price` DECIMAL(19, 2) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`)
);