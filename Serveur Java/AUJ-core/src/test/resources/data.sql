INSERT INTO auj_riotaccount VALUES (1,'didjo972','ADC');
INSERT INTO auj_user VALUES (1,'didjo972','123','Test','dimi.marie-rose',1);

/* TEST CONTACT DAO */
INSERT INTO auj_riotaccount VALUES (2,'User2','ADC');
INSERT INTO auj_user VALUES (2,'User2','123','Test','User2@gmail.com',2);
INSERT INTO auj_riotaccount VALUES (3,'User3','SUPP');
INSERT INTO auj_user VALUES (3,'User3','123','Test','User3@gmail.com',3);

INSERT INTO auj_riotaccount VALUES (4,'User4','ADC');
INSERT INTO auj_user VALUES (4,'User4','123','Test','User4@gmail.com',4);
INSERT INTO auj_riotaccount VALUES (5,'User5','SUPP');
INSERT INTO auj_user VALUES (5,'User5','123','Test','User5@gmail.com',5);

INSERT INTO auj_in_contact VALUES (2,4,5,'1');
INSERT INTO auj_in_contact VALUES (3,3,4,'0');
INSERT INTO auj_in_contact VALUES (4,2,4,'1');

/* TEST CONTACT DAO - Suggestion */
INSERT INTO auj_riotaccount VALUES (6,'User6','MID');
INSERT INTO auj_user VALUES (6,'User6','123','Test','User6@gmail.com',6);
INSERT INTO auj_riotaccount VALUES (7,'xDnCo','MID');
INSERT INTO auj_user VALUES (7,'User7','123','Test','User7@gmail.com',7);

/* TEST USER DAO - Post */
INSERT INTO auj_post VALUES (1,'2017-09-19 19:00:00','Bonjour, voici mon premier post', 1);

/* TEST POST DAO - Update */
INSERT INTO auj_post VALUES (2,'2017-10-19 19:00:00','Mon post est genial', 1);