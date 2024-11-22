-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbdamda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbdamda` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dbdamda` ;

-- -----------------------------------------------------
-- Table `dbdamda`.`sidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbdamda`.`sidos` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `sido_code` INT NULL DEFAULT NULL,
  `sido_name` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  UNIQUE INDEX `sido_code_UNIQUE` (`sido_code` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dbdamda`.`guguns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbdamda`.`guguns` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `gugun_name` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `guguns_sido_to_sidos_cdoe_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `gugun_code_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `guguns_sido_to_sidos_cdoe_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `dbdamda`.`sidos` (`sido_code`))
ENGINE = InnoDB
AUTO_INCREMENT = 235
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dbdamda`.`contenttypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbdamda`.`contenttypes` (
  `content_type_id` INT NOT NULL,
  `content_type_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dbdamda`.`attractions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbdamda`.`attractions` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `title` VARCHAR(500) NULL DEFAULT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `area_code` INT NULL DEFAULT NULL,
  `si_gun_gu_code` INT NULL DEFAULT NULL,
  `first_image1` VARCHAR(100) NULL DEFAULT NULL,
  `first_image2` VARCHAR(100) NULL DEFAULT NULL,
  `map_level` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `tel` VARCHAR(20) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(100) NULL DEFAULT NULL,
  `homepage` VARCHAR(1000) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
   UNIQUE INDEX `content_id_UNIQUE` (`content_id`),
  INDEX `attractions_typeid_to_types_typeid_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attractions_sido_to_sidos_code_fk_idx` (`area_code` ASC) VISIBLE,
  INDEX `attractions_sigungu_to_guguns_gugun_fk_idx` (`si_gun_gu_code` ASC) VISIBLE,
  CONSTRAINT `attractions_area_to_sidos_code_fk`
    FOREIGN KEY (`area_code`)
    REFERENCES `dbdamda`.`sidos` (`sido_code`),
  CONSTRAINT `attractions_sigungu_to_guguns_gugun_fk`
    FOREIGN KEY (`si_gun_gu_code`)
    REFERENCES `dbdamda`.`guguns` (`gugun_code`),
  CONSTRAINT `attractions_typeid_to_types_typeid_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `dbdamda`.`contenttypes` (`content_type_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 56644
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dbdamda`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`members` ;

CREATE TABLE IF NOT EXISTS `dbdamda`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(256) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_status` int not null default 1,
  `token` varchar(1000) null default null,
  `birth_date` date null default null,
  `phone_num` varchar(20) null default null,
  `address` varchar(256) null default null,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `dbdamda`.`members` (user_id, user_name, user_password, email_id, email_domain, join_date)
values 	('ssafy', '김싸피', 'UxOcdW4QNwYyU3QSG/pT/Q==', 'ssafy', 'ssafy.com', now()), 
		('admin', '관리자', 'UxOcdW4QNwYyU3QSG/pT/Q==', 'admin', 'google.com', now());
        
-- -----------------------------------------------------
-- Table `dbdamda`.`faqs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`faqs` ;

CREATE TABLE `faqs` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `faq_to_members_user_id_fk` (`user_id`),
  CONSTRAINT `faq_to_members_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `faqs` VALUES (1,'admin','테스트용 제목','테스트용 내용입니당', '2023-10-17 08:06:05');
INSERT INTO `faqs` VALUES (2,'admin','여행 검색은 어떻게 하나요?','여행 검색은 메인 화면에서 가운데 지도로 이동하기 버튼을 누를 수 있습니다. 또는 여행 계획 플랜에서 버튼을 클릭하면 여행 계획 구성 페이지로 넘어 갈 수 있습니다. 이곳에서 여행하고 싶은 다양한 장소를 검색할 수 있습니다.', '2024-11-16 09:06:05');

-- -----------------------------------------------------
-- Table `dbdamda`.`journeys`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`journeys` ;
CREATE TABLE IF NOT EXISTS  `journeys` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `personnel` INT NOT NULL,
  `color` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `dbdamda`.`journey_routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`journey_routes` ;
CREATE TABLE IF NOT EXISTS `journey_routes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NOT NULL,
  `day` INT not null,
  `order_in_day` INT NOT NULL,
  `content_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`journey_id`) REFERENCES `journeys` (`id`) ,
  FOREIGN KEY (`content_id`) REFERENCES `attractions` (`content_id`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `dbdamda`.`member_journey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`member_journey` ;
CREATE TABLE IF NOT EXISTS `member_journey` (
`id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NOT NULL, 
  `user_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`) ,
  FOREIGN KEY (`journey_id`) REFERENCES `journeys` (`id`) 
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `dbdamda`.`journey_accessibility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`journey_accessibility` ;
CREATE TABLE IF NOT EXISTS `journey_accessibility` (
	`id` INT NOT NULL,
  `journey_id` INT NOT NULL, 
  `accessible_option` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`journey_id`) REFERENCES `journeys` (`id`) 
) ENGINE=InnoDB;

commit;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

