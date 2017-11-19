DROP SCHEMA IF EXISTS adopteunjungle;

CREATE SCHEMA IF NOT EXISTS adopteunjungle DEFAULT CHARACTER SET utf8;

USE adopteunjungle;

DROP TABLE IF EXISTS auj_riotaccount;

DROP TABLE IF EXISTS auj_user;

DROP TABLE IF EXISTS auj_in_contact;

DROP TABLE IF EXISTS auj_message;

DROP TABLE IF EXISTS auj_post;

CREATE TABLE IF NOT EXISTS auj_riotaccount (
  ID BIGINT(11) NOT NULL AUTO_INCREMENT,
  PSEUDO VARCHAR(255) NOT NULL,
  RANK INT(11) NOT NULL,
  ROLE VARCHAR(255) NOT NULL,
  SERVER VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS auj_user (
  ID BIGINT(11) NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  ADDITIONALINFORMATION VARCHAR(255) NULL DEFAULT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  auj_riotaccount_ID BIGINT(11) NOT NULL,
  PRIMARY KEY (ID))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS auj_in_contact (
  ID BIGINT(11) NOT NULL AUTO_INCREMENT,
  auj_user1_ID BIGINT(11) NOT NULL,
  auj_user2_ID BIGINT(11) NOT NULL,
  PRIMARY KEY (ID))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS auj_message (
  ID BIGINT(11) NOT NULL AUTO_INCREMENT,
  CONTENT VARCHAR(200) NOT NULL,
  auj_sender_ID BIGINT(11) NOT NULL,
  auj_receiver_ID BIGINT(11) NOT NULL,
  PRIMARY KEY (ID))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS auj_post (
  ID BIGINT(11) NOT NULL AUTO_INCREMENT,
  POSTDATE DATETIME NOT NULL,
  CONTENT VARCHAR(100) NOT NULL,
  auj_user_ID BIGINT(11) NOT NULL,
  PRIMARY KEY (ID))
ENGINE = InnoDB;

ALTER TABLE auj_user
	ADD UNIQUE INDEX EMAIL_UNIQUE (EMAIL ASC),
	ADD UNIQUE INDEX USERNAME_UNIQUE (USERNAME ASC);
	
ALTER TABLE auj_user
        ADD INDEX fk_user_riotaccount1_idx (auj_riotaccount_ID),
        ADD CONSTRAINT fk_user_riotaccount1
        FOREIGN KEY (auj_riotaccount_ID) 
        REFERENCES auj_riotaccount (ID);
        
ALTER TABLE auj_message
        ADD INDEX fk_message_user1_idx (auj_sender_ID),
        ADD CONSTRAINT fk_message_user1
        FOREIGN KEY (auj_sender_ID) 
        REFERENCES auj_user (ID);

ALTER TABLE auj_message
        ADD INDEX fk_message_user2_idx (auj_receiver_ID),
        ADD CONSTRAINT fk_message_user2
        FOREIGN KEY (auj_receiver_ID) 
        REFERENCES auj_user (ID);

ALTER TABLE auj_post
        ADD INDEX fk_post_user1_idx (auj_user_ID),
        ADD CONSTRAINT fk_post_user1
        FOREIGN KEY (auj_user_ID) 
        REFERENCES auj_user (ID);
        
ALTER TABLE auj_in_contact
        ADD INDEX fk_contact_user1_idx (auj_user1_ID),
        ADD CONSTRAINT fk_contact_user1
        FOREIGN KEY (auj_user1_ID) 
        REFERENCES auj_user (ID);

ALTER TABLE auj_in_contact
        ADD INDEX fk_contact_user2_idx (auj_user2_ID),
        ADD CONSTRAINT fk_contact_user2
        FOREIGN KEY (auj_user2_ID) 
        REFERENCES auj_user (ID);
       
