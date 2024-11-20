-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafytrip` ;

-- -----------------------------------------------------
-- Table `ssafytrip`.`sidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`sidos` (
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
-- Table `ssafytrip`.`guguns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`guguns` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `gugun_name` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `guguns_sido_to_sidos_cdoe_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `gugun_code_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `guguns_sido_to_sidos_cdoe_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `ssafytrip`.`sidos` (`sido_code`))
ENGINE = InnoDB
AUTO_INCREMENT = 235
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafytrip`.`contenttypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`contenttypes` (
  `content_type_id` INT NOT NULL,
  `content_type_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafytrip`.`attractions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`attractions` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NULL DEFAULT NULL,
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
  INDEX `attractions_typeid_to_types_typeid_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attractions_sido_to_sidos_code_fk_idx` (`area_code` ASC) VISIBLE,
  INDEX `attractions_sigungu_to_guguns_gugun_fk_idx` (`si_gun_gu_code` ASC) VISIBLE,
  CONSTRAINT `attractions_area_to_sidos_code_fk`
    FOREIGN KEY (`area_code`)
    REFERENCES `ssafytrip`.`sidos` (`sido_code`),
  CONSTRAINT `attractions_sigungu_to_guguns_gugun_fk`
    FOREIGN KEY (`si_gun_gu_code`)
    REFERENCES `ssafytrip`.`guguns` (`gugun_code`),
  CONSTRAINT `attractions_typeid_to_types_typeid_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `ssafytrip`.`contenttypes` (`content_type_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 56644
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafytrip`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`members` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(256) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_status` int not null default 1,
  `token` varchar(1000) null default null,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `ssafytrip`.`members` (user_id, user_name, user_password, email_id, email_domain, join_date)
values 	('ssafy', '김싸피', 'UxOcdW4QNwYyU3QSG/pT/Q==', 'ssafy', 'ssafy.com', now()), 
		('admin', '관리자', 'UxOcdW4QNwYyU3QSG/pT/Q==', 'admin', 'google.com', now());
        
-- -----------------------------------------------------
-- Table `ssafytrip`.`faqs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`faqs` ;

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
-- Table `ssafytrip`.`file_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`file_info` ;

CREATE TABLE `file_info` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `article_no` int DEFAULT NULL,
  `save_folder` varchar(45) DEFAULT NULL,
  `original_file` varchar(50) DEFAULT NULL,
  `save_file` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `file_info_to_faqs_article_no_fk` (`article_no`),
  CONSTRAINT `file_info_to_faqs_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES `faqs` (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;