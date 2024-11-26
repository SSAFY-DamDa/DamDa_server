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
  `ai` boolean NOT NULL default false,
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
-- Table `dbdamda`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbdamda`.`reviews` ;
CREATE TABLE IF NOT EXISTS `reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NOT NULL,
  `user_id` varchar(16) NOT NULL,
  `ratings` INT NOT NULL,
  `comment` varchar(256) NOT NULL,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`journey_id`) REFERENCES `journeys` (`id`) ,
  FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Dummy data
-- -----------------------------------------------------
INSERT INTO `dbdamda`.`members` (user_id, user_name, user_password, email_id, email_domain, join_date)
VALUES 	('ssafy', '김싸피', 'UxOcdW4QNwYyU3QSG/pT/Q==', 'ssafy', 'ssafy.com', now()), 
		('admin', '관리자', 'UxOcdW4QNwYyU3QSG/pT/Q==', 'admin', 'google.com', now());

INSERT INTO `dbdamda`.`faqs` 
VALUES 
	(1,'admin','여행 검색은 무엇인가요?','궁금한 장소를 빠르게 검색 가능한 기능입니다. 검색을 원하는 장소를 제목 혹은 지역명을 통해 검색 가능합니다. ', '2024-11-12 09:00:05'),
    (2,'admin','AI 추천은 무엇인가요?','AI가 원하는 선호에 따라 여행 일정을 계획해주는 기능입니다.', '2024-11-12 09:06:05'),
    (3,'admin','직접 계획은 무엇인가요?','직접 원하는 장소를 검색해 원하는 일정으로 여행을 계획할 수 있는 기능입니다. ', '2024-11-12 09:10:02'),
     (4,'admin','여행 계획은 어떻게 확인하나요?','오른쪽 위 끝 본인의 아이디를 누르면 뜨는 팝업에서 나의 여정을 클릭해 확인 가능합니다.', '2024-11-13 14:10:42'),
    (5,'admin','이전 여정은 어떻게 확인하나요?','마이페이지 > 이전 여정을 눌러 확인할 수 있습니다. ', '2024-11-13 14:15:29');
    
-- 여행 추가
INSERT INTO `dbdamda`.`journeys` (title, start_date, end_date, personnel, color)
VALUES 
    -- ssafy의 여행
    ('서울 랜드마크 투어', '2024-10-20', '2024-10-22', 1, '#FFA500'),
    ('서울 자연 탐방', '2024-11-25', '2024-11-27', 1, '#00FF00'),
    ('서울 역사 유적지 여행', '2024-12-10', '2024-12-12', 1, '#1E90FF'),

    -- admin의 여행
    ('서울 공원 투어', '2024-08-18', '2024-08-20', 1, '#FF6347'),
    ('서울 종로 탐방', '2024-05-22', '2024-05-24', 1, '#FFD700'),
    ('서울 강남 투어', '2024-12-03', '2024-12-05', 1, '#ADFF2F');

-- 유저와 여행 연결
INSERT INTO `dbdamda`.`member_journey` (user_id, journey_id)
VALUES 
    -- ssafy와 연결
    ('ssafy', 1), -- 서울 랜드마크 투어
    ('ssafy', 2), -- 서울 자연 탐방
    ('ssafy', 3), -- 서울 역사 유적지 여행

    -- admin과 연결
    ('admin', 4), -- 서울 공원 투어
    ('admin', 5), -- 서울 종로 탐방
    ('admin', 6); -- 서울 강남 투어


-- ssafy의 여행 경로
INSERT INTO `dbdamda`.`journey_routes` (journey_id, day, order_in_day, content_id)
VALUES 
    -- 서울 랜드마크 투어 (Journey ID: 1)
    (1, 1, 1, 2733967), (1, 1, 2, 2763807), -- 1일차
    (1, 2, 1, 1116925), (1, 2, 2, 294439), -- 2일차
    (1, 3, 1, 264570), (1, 3, 2, 2456536), -- 3일차

    -- 서울 자연 탐방 (Journey ID: 2)
    (2, 1, 1, 127377), (2, 1, 2, 128961), -- 1일차
    (2, 2, 1, 809490), (2, 2, 2, 3043735), -- 2일차
    (2, 3, 1, 2733968), (2, 3, 2, 2591792), -- 3일차

    -- 서울 역사 유적지 여행 (Journey ID: 3)
    (3, 1, 1, 126501), (3, 1, 2, 2733966), -- 1일차
    (3, 2, 1, 2758868), (3, 2, 2, 1604652), -- 2일차
    (3, 3, 1, 126508), (3, 3, 2, 294505), -- 3일차

    -- admin의 여행 경로
    -- 서울 공원 투어 (Journey ID: 4)
    (4, 1, 1, 294439), (4, 1, 2, 3043735), -- 1일차
    (4, 2, 1, 809490), (4, 2, 2, 264570), -- 2일차
    (4, 3, 1, 2758868), (4, 3, 2, 128961), -- 3일차

    -- 서울 종로 탐방 (Journey ID: 5)
    (5, 1, 1, 2930839), (5, 1, 2, 2758868), -- 1일차
    (5, 2, 1, 127377), (5, 2, 2, 2456536), -- 2일차
    (5, 3, 1, 2733968), (5, 3, 2, 264570), -- 3일차

    -- 서울 강남 투어 (Journey ID: 6)
    (6, 1, 1, 2763807), (6, 1, 2, 1116925), -- 1일차
    (6, 2, 1, 2733967), (6, 2, 2, 3043735), -- 2일차
    (6, 3, 1, 1604652), (6, 3, 2, 127377); -- 3일차

-- 리뷰 추가
INSERT INTO `dbdamda`.`reviews` (id, journey_id, user_id, ratings, comment, register_time)
VALUES 
    -- ssafy의 여행
    (1, 1, 'ssafy', 5, 'AI 너무 똑똑해요', '2024-11-12 09:00:05'),
    (2, 1, 'ssafy', 4, '기능이 좋네요', '2024-11-13 12:00:05'),
	(3, 1, 'ssafy', 3, '편하긴 해요', '2024-11-03 19:10:45'),
	(4, 2, 'ssafy', 3, '나름 상세하네요', '2024-11-13 21:46:04'),
	(5, 2, 'ssafy', 4, '세상 편해졌다', '2024-11-15 23:34:46'),
    (6, 2, 'ssafy', 5, '취향 반영 너무 좋네요', '2024-11-26 09:30:23'),
    (7, 3, 'ssafy', 4, '이용해볼만 합니다', '2024-11-19 14:00:05');


commit;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
