USE adopteunjungle;

ALTER TABLE auj_riotaccount
	DROP COLUMN RANK;

ALTER TABLE auj_in_contact
	ADD COLUMN STATUS INT(11) NOT NULL AFTER auj_user2_ID;