-- -----------------------------------------------------
-- Schema adopteunjungle
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `adopteunjungle` DEFAULT CHARACTER SET utf8 ;
USE adopteunjungle ;

-- -----------------------------------------------------
-- Table AUJ_USER
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AUJ_USER (
  AUJ_USER_ID INT NOT NULL AUTO_INCREMENT,
  AUJ_USER_riotID BIGINT(20) NOT NULL,
  AUJ_USER_username VARCHAR(20) NOT NULL,
  AUJ_USER_password VARCHAR(15) NOT NULL,
  AUJ_USER_additionalInformation VARCHAR(100) NULL,
  PRIMARY KEY (AUJ_USER_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AUJ_MESSAGE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AUJ_MESSAGE (
  AUJ_MESSAGE_ID INT NOT NULL AUTO_INCREMENT,
  AUJ_MESSAGE_content VARCHAR(200) NOT NULL,
  AUJ_USER_AUJ_SENDER_ID INT NOT NULL,
  AUJ_USER_AUJ_RECEIVER_ID INT NOT NULL,
  PRIMARY KEY (AUJ_MESSAGE_ID, AUJ_USER_AUJ_SENDER_ID, AUJ_USER_AUJ_RECEIVER_ID),
  INDEX fk_AUJ_MESSAGE_AUJ_USER1_idx (AUJ_USER_AUJ_SENDER_ID ASC),
  INDEX fk_AUJ_MESSAGE_AUJ_USER2_idx (AUJ_USER_AUJ_RECEIVER_ID ASC),
  CONSTRAINT fk_AUJ_MESSAGE_AUJ_USER1
    FOREIGN KEY (AUJ_USER_AUJ_SENDER_ID)
    REFERENCES adopteunjungle.AUJ_USER (AUJ_USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_AUJ_MESSAGE_AUJ_USER2
    FOREIGN KEY (AUJ_USER_AUJ_RECEIVER_ID)
    REFERENCES adopteunjungle.AUJ_USER (AUJ_USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AUJ_POST
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AUJ_POST (
  AUJ_POST_ID INT NOT NULL AUTO_INCREMENT,
  AUJ_POST_postDate DATE NOT NULL,
  AUJ_POST_content VARCHAR(100) NOT NULL,
  AUJ_USER_AUJ_USER_ID INT NOT NULL,
  PRIMARY KEY (AUJ_POST_ID, AUJ_USER_AUJ_USER_ID),
  INDEX fk_AUJ_POST_AUJ_USER1_idx (AUJ_USER_AUJ_USER_ID ASC),
  CONSTRAINT fk_AUJ_POST_AUJ_USER1
    FOREIGN KEY (AUJ_USER_AUJ_USER_ID)
    REFERENCES adopteunjungle.AUJ_USER (AUJ_USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AUJ_RIOTACCOUNT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AUJ_RIOTACCOUNT (
  AUJ_RIOTACCOUNT_ID INT NOT NULL AUTO_INCREMENT,
  AUJ_RIOTACCOUNT_pseudo VARCHAR(45) NOT NULL,
  AUJ_RIOTACCOUNT_rank INT NOT NULL,
  AUJ_RIOTACCOUNT_role VARCHAR(45) NOT NULL,
  AUJ_RIOTACCOUNT_server VARCHAR(45) NOT NULL,
  AUJ_USER_AUJ_USER_ID INT NOT NULL,
  PRIMARY KEY (AUJ_RIOTACCOUNT_ID, AUJ_USER_AUJ_USER_ID),
  INDEX fk_AUJ_RIOTACCOUNT_AUJ_USER1_idx (AUJ_USER_AUJ_USER_ID ASC),
  CONSTRAINT fk_AUJ_RIOTACCOUNT_AUJ_USER1
    FOREIGN KEY (AUJ_USER_AUJ_USER_ID)
    REFERENCES adopteunjungle.AUJ_USER (AUJ_USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AUJ_IN_CONTACT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AUJ_IN_CONTACT (
  AUJ_USER_AUJ_USER1_ID INT NOT NULL,
  AUJ_USER_AUJ_USER2_ID INT NOT NULL,
  PRIMARY KEY (AUJ_USER_AUJ_USER1_ID, AUJ_USER_AUJ_USER2_ID),
  INDEX fk_AUJ_USER_has_AUJ_CONTACT_AUJ_USER1_idx (AUJ_USER_AUJ_USER1_ID ASC),
  INDEX fk_AUJ_IN_CONTACT_AUJ_USER1_idx (AUJ_USER_AUJ_USER2_ID ASC),
  CONSTRAINT fk_AUJ_USER_has_AUJ_CONTACT_AUJ_USER1
    FOREIGN KEY (AUJ_USER_AUJ_USER1_ID)
    REFERENCES adopteunjungle.AUJ_USER (AUJ_USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_AUJ_IN_CONTACT_AUJ_USER1
    FOREIGN KEY (AUJ_USER_AUJ_USER2_ID)
    REFERENCES adopteunjungle.AUJ_USER (AUJ_USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
