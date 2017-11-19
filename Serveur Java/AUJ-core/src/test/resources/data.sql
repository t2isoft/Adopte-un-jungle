INSERT INTO auj_riotaccount VALUES (1,'Dimsy972','ADC','EUW');
INSERT INTO auj_user VALUES (1,'didjo972','123','Test','dimi.marie-rose',1);

/* TEST CONTACT DAO */
INSERT INTO auj_riotaccount VALUES (2,'User2','ADC','EUW');
INSERT INTO auj_user VALUES (2,'User2','123','Test','User2@gmail.com',2);
INSERT INTO auj_riotaccount VALUES (3,'User3','SUPP','EUW');
INSERT INTO auj_user VALUES (3,'User3','123','Test','User3@gmail.com',3);

INSERT INTO auj_riotaccount VALUES (4,'User4','ADC','EUW');
INSERT INTO auj_user VALUES (4,'User4','123','Test','User4@gmail.com',4);
INSERT INTO auj_riotaccount VALUES (5,'User5','SUPP','EUW');
INSERT INTO auj_user VALUES (5,'User5','123','Test','User5@gmail.com',5);

INSERT INTO auj_in_contact VALUES (2,4,5,'1');
INSERT INTO auj_in_contact VALUES (3,3,4,'0');
INSERT INTO auj_in_contact VALUES (4,2,4,'1');