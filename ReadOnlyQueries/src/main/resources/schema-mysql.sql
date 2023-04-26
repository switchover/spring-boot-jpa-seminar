-- DML
DROP TABLE IF EXISTS `author`;

CREATE TABLE IF NOT EXISTS `author` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `age` INTEGER NOT NULL,
    `genre` VARCHAR(255),
    `name` VARCHAR(255),
    PRIMARY KEY (`id`));