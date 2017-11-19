-- LES DROPS TABLE --
DROP TABLE IF EXISTS auj_riotaccount;
DROP TABLE IF EXISTS auj_user;
DROP TABLE IF EXISTS auj_in_contact;
DROP TABLE IF EXISTS auj_message;
DROP TABLE IF EXISTS auj_post;

-- LES TABLES --
CREATE TABLE IF NOT EXISTS auj_riotaccount (
  ID BIGINT(11) NOT NULL AUTO_INCREMENT,
  PSEUDO VARCHAR(255) NOT NULL,
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
  STATUS INT(11) NOT NULL,
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

-- LES INDEX UNIQUE --
ALTER TABLE auj_user
	ADD UNIQUE INDEX EMAIL_UNIQUE (EMAIL ASC);
ALTER TABLE auj_user
	ADD UNIQUE INDEX USERNAME_UNIQUE (USERNAME ASC);
	
-- LES FOREIGN KEY --
CREATE INDEX fk_user_riotaccount1_idx ON auj_user(auj_riotaccount_ID);
CREATE INDEX fk_message_user1_idx ON auj_message(auj_sender_ID);
CREATE INDEX fk_message_user2_idx ON auj_message(auj_receiver_ID);
CREATE INDEX fk_post_user1_idx ON auj_post(auj_user_ID);
CREATE INDEX fk_contact_user1_idx ON auj_in_contact(auj_user1_ID);
CREATE INDEX fk_contact_user2_idx ON auj_in_contact(auj_user2_ID);

-- LES CONTRAINTES --
ALTER TABLE auj_user
    ADD CONSTRAINT fk_user_riotaccount1
    FOREIGN KEY (auj_riotaccount_ID) 
    REFERENCES auj_riotaccount (ID);

ALTER TABLE auj_message
        ADD CONSTRAINT fk_message_user1
        FOREIGN KEY (auj_sender_ID) 
        REFERENCES auj_user (ID);

ALTER TABLE auj_message
        ADD CONSTRAINT fk_message_user2
        FOREIGN KEY (auj_receiver_ID) 
        REFERENCES auj_user (ID);

ALTER TABLE auj_post
        ADD CONSTRAINT fk_post_user1
        FOREIGN KEY (auj_user_ID) 
        REFERENCES auj_user (ID);
        
ALTER TABLE auj_in_contact
        ADD CONSTRAINT fk_contact_user1
        FOREIGN KEY (auj_user1_ID) 
        REFERENCES auj_user (ID);

ALTER TABLE auj_in_contact
        ADD CONSTRAINT fk_contact_user2
        FOREIGN KEY (auj_user2_ID) 
        REFERENCES auj_user (ID);