SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cubby
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cubby` ;
CREATE SCHEMA IF NOT EXISTS `cubby` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `cubby` ;

-- -----------------------------------------------------
-- Table `cubby`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cubby`.`roles` ;

CREATE TABLE IF NOT EXISTS `cubby`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cubby`.`trainings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cubby`.`trainings` ;

CREATE TABLE IF NOT EXISTS `cubby`.`trainings` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `cost` INT(11) NULL DEFAULT NULL,
  `online` INT(11) NULL DEFAULT NULL,
  `url` VARCHAR(50) NULL DEFAULT NULL,
  `startDate` DATETIME NULL DEFAULT NULL,
  `place` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cubby`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cubby`.`users` ;

CREATE TABLE IF NOT EXISTS `cubby`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `enabled` INT(11) NOT NULL,
  `firstname` VARCHAR(20) NOT NULL,
  `lastname` VARCHAR(40) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `street` VARCHAR(60) NOT NULL,
  `zipcode` VARCHAR(6) NOT NULL,
  `birthdate` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cubby`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cubby`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `cubby`.`user_roles` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_role_id_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `cubby`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `cubby`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cubby`.`user_trainings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cubby`.`user_trainings` ;

CREATE TABLE IF NOT EXISTS `cubby`.`user_trainings` (
  `active` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `training_id` INT(11) NOT NULL,
  PRIMARY KEY (`training_id`, `user_id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `training_id_idx` (`training_id` ASC),
  CONSTRAINT `training_id`
    FOREIGN KEY (`training_id`)
    REFERENCES `cubby`.`trainings` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `cubby`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
