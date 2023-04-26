-- DML
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `author`;

CREATE TABLE IF NOT EXISTS `author` (
    `age` INTEGER NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `genre` VARCHAR(255),
    PRIMARY KEY (`age`, `name`));

CREATE TABLE IF NOT EXISTS `book` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `isbn` VARCHAR(255),
    `title` VARCHAR(255),
    `age` INTEGER,
    `name` VARCHAR(255),
    PRIMARY KEY (`id`));
ALTER TABLE `book` ADD CONSTRAINT FK_AUTHOR FOREIGN KEY (`age`, `name`) REFERENCES author(`age`, `name`);