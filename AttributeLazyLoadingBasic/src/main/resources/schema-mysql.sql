-- DML
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `author`;

CREATE TABLE IF NOT EXISTS `author` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `avatar` LONGBLOB,
    `age` INTEGER NOT NULL,
    `genre` VARCHAR(255),
    `name` VARCHAR(255),
    PRIMARY KEY (`id`));
