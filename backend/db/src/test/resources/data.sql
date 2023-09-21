-- File is imported by Spring Boot on startup


INSERT INTO club VALUES (1, 'FC Mustermann', 0, 48.1372424399433, 11.5753922259255, NULL, NULL);
INSERT INTO club VALUES (2, 'FC Test 2012', 0, 48.1372424399433, 11.5753922259255, NULL, NULL);

INSERT INTO team VALUES (1, 'FC Bayern', 'http://fcb.de', 1, 0, NULL, NULL);
INSERT INTO team VALUES (4, 'FC Bayern Amateure', NULL, 1, 0, NULL, NULL);
INSERT INTO team VALUES (5, 'Team 1', NULL, 2, 0, NULL, NULL);
INSERT INTO team VALUES (8, 'DELETED-Veuve Tricot', 'http://www.royalliga.de/', 2, 1, '2015-10-12 17:09:40', NULL);


INSERT INTO users VALUES (1, 'oliver.blume@yahoo.de', true, 'a8f5f167f44f4964e6c998dee827110c', 'Flower', NULL, true, 'FIT', 2, 0, NULL, NULL, NULL, NULL, '2023-07-19 13:04:12');
INSERT INTO users VALUES (79, 'schweinsteiger@mailinator.com', true, 'd25e3eb85eb1094663a5037a4dd69bcd', 'Schweinsteiger', 'kmsx', NULL, 'FIT', 1, 0, NULL, NULL, '2012-08-30 14:13:01', NULL, NULL);
INSERT INTO users VALUES (80, 'martinez@mailinator.com', true, '15bdfe51b4c5a1c24f681e4891b8c29b', 'Martinez', 'vkxr', NULL, 'FIT', 1, 0, NULL, NULL, '2012-08-30 14:13:21', NULL, NULL);
INSERT INTO users VALUES (81, 'gustavo@mailinator.com', true, '60494b575e52e98f260d3e559f7899ef', 'Gustavo', 'kb9q', NULL, 'FIT', 1, 0, NULL, NULL, '2012-08-30 14:13:36', NULL, NULL);
INSERT INTO users VALUES (82, 'robben@mailinator.com', true, 'ead7272c4b8d1f431894f3735f2d6ada', 'Robben', '2h24', NULL, 'FIT', 1, 0, NULL, NULL, '2012-08-30 14:13:50', NULL, NULL);
INSERT INTO users VALUES (83, 'ribery@mailinator.com', true, '4669fd91e3352d800f59646c8b7fab22', 'Ribery', 'pr2x', NULL, 'FIT', 1, 0, NULL, NULL, '2012-08-30 14:14:03', NULL, NULL);
INSERT INTO users VALUES (84, 'mueller@mailinator.com', true, 'f1cae9cfffcbe39619fec6e7e1dc28a2', 'Müller', 'zb9a', NULL, 'FIT', 1, 0, NULL, NULL, '2012-08-30 14:14:16', NULL, NULL);


INSERT INTO venue VALUES (1,'Lochhamer Straße 33, 82152 Planegg, Deutschland',48.1140152,11.4516327,'Sportzentrum Martinsried',1,0,NULL,NULL);
INSERT INTO venue VALUES (2,'Eggernstrasse 10\n81667 München',48.1330032348633,11.5955190658569,'Bei Pötschibonsi',1,0,NULL,NULL);
INSERT INTO venue VALUES (3,'Werner-Heisenberg-Allee 25\n80939 München',48.2187423706055,11.624529838562,'Allianz Arena',1,0,NULL,NULL);
INSERT INTO venue VALUES (4,'Grünwalder Straße 4\n81547 München',48.1108589172363,11.5747909545898,'Gründwalder Stadion',1,0,NULL,NULL);

INSERT INTO opponent VALUES (1,'Ajax aus der Traum',NULL,0,1,NULL,NULL);
INSERT INTO opponent VALUES (2,'Hinter Mailand',NULL,0,1,NULL,NULL);
INSERT INTO opponent VALUES (3,'FC Flower Blume',NULL,0,2,NULL,NULL);

INSERT INTO uniform VALUES (1,0,'Weiss/Weiss/Weiss','Weiss','Weiss','Weiss',5,NULL,NULL);
INSERT INTO uniform VALUES (2,0,'Rot/Schwarz/Rot','Rot','Schwarz','Rot',5,NULL,NULL);
INSERT INTO uniform VALUES (3,0,'Juve All Black','Schwarz','Schwarz','Dunkel',5,NULL,NULL);
INSERT INTO uniform VALUES (4,0,'Weiß/Schwarz/Weiß','Weiß','Schwarz','Weiß',1,NULL,NULL);
INSERT INTO uniform VALUES (5,0,'All White','Weiß','Weiß','Weiß',1,NULL,NULL);
INSERT INTO uniform VALUES (6,0,'Rot/Weiß/Rot','Rot','Weiß','Rot',5,'2012-07-24 18:42:31',NULL);
INSERT INTO uniform VALUES (7,0,'Weiß-Schwarz-Weiß','Weiß','Schwarz','Weiß',5,'2014-10-09 21:57:14',NULL);
INSERT INTO uniform VALUES (8,0,'Old school','rot','rot','rot',4,'2014-11-16 20:30:30',NULL);


INSERT INTO event VALUES ('Match', 4, 'Jungs, hier wird die Richtung für die kommende Saision festgelegt. Also haut rein!', true, 'Saisonopening', '14:30:00', 2, 5, 3, 1, 0, 'NATURAL_GRASS', NULL, 1, false, '2012-08-18 14:45:00', NULL, NULL, '2012-08-18 17:15:00');
INSERT INTO event VALUES ('Event', 25, 'Bitte eintragen, damit ich besser planen kann.', true, 'Urlaubs- und Fehlzeitenplanung', NULL, 1, 5, 2, NULL, 0, NULL, NULL, 1, false, '2012-11-30 00:00:00', '2012-07-03 17:55:51', NULL, '2012-11-30 04:00:00');
INSERT INTO event VALUES ('Training', 52, 'Wir sind gebucht jeden Sonntag bis Ende März!!', true, 'Soccer5 in Martinsried', '17:15:00', 1, 1, 1, NULL, 0, 'ARTIFICIAL_GRASS', NULL, 1, false, '2012-11-04 17:00:00', '2012-10-29 16:23:22', NULL, '2012-11-04 18:45:00');
INSERT INTO event VALUES ('Training', 53, NULL, true, '3. Soccer5', '17:15:00', 1, 5, 1, NULL, 0, 'ARTIFICIAL_GRASS', NULL, 1, false, '2012-11-11 17:00:00', '2012-10-29 16:28:16', NULL, '2012-11-11 18:45:00');
