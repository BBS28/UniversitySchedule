SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (1, '105');
INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (2, '215');
INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (3, '216');
INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (4, '301-A');
INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (5, '301-B');
INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (6, '310');
INSERT INTO university_schedule.classrooms OVERRIDING SYSTEM VALUE VALUES (7, '311');


INSERT INTO university_schedule.groups OVERRIDING SYSTEM VALUE VALUES (1, 'AB-19');
INSERT INTO university_schedule.groups OVERRIDING SYSTEM VALUE VALUES (2, 'CDE-22');
INSERT INTO university_schedule.groups OVERRIDING SYSTEM VALUE VALUES (3, 'FG-21');


INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (1, 1, 'MONDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (2, 1, 'TUESDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (3, 1, 'WEDNESDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (4, 1, 'THURSDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (5, 1, 'FRIDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (6, 2, 'MONDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (7, 2, 'TUESDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (8, 2, 'WEDNESDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (9, 2, 'THURSDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (10, 2, 'FRIDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (11, 3, 'MONDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (12, 3, 'TUESDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (13, 3, 'WEDNESDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (14, 3, 'THURSDAY');
INSERT INTO university_schedule.schedule OVERRIDING SYSTEM VALUE VALUES (15, 3, 'FRIDAY');


INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (3, 1, 1, 'Ecology');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (4, 7, 6, 'Ecology');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (5, 2, 11, 'Ecology');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (6, 3, 2, 'Мathematics');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (7, 4, 7, 'Mathematics');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (8, 6, 12, 'Mathematics');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (9, 2, 3, 'Physics');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (10, 3, 8, 'Physics');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (11, 4, 13, 'Physics');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (12, 5, 4, 'Philosophy');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (13, 6, 9, 'Philosophy');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (14, 7, 14, 'Philosophy');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (15, 1, 5, 'Geometry');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (16, 2, 10, 'Geometry');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (17, 3, 15, 'Geometry');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (18, 4, 1, 'Chemistry');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (19, 5, 2, 'Chemistry');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (20, 6, 3, 'Chemistry');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (21, 7, 4, 'Biology');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (22, 2, 5, 'Biology');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (23, 4, 6, 'Biology');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (24, 6, 7, 'Music');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (25, 1, 8, 'Music');
INSERT INTO university_schedule.lectures OVERRIDING SYSTEM VALUE VALUES (26, 5, 9, 'Music');


INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (2, 'Louis', 'Armstrong', 'armstrong@email.com', 1);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (3, 'Jimi
', 'Hendrix', 'hendrix@email.com', 2);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (4, 'Kurt', 'Kobein', 'kobein@email.com', 3);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (5, 'Marilyn', 'Manson', 'manson@email.com', 1);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (7, 'Elvis', 'Presley', 'elvis@email.com', 2);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (8, 'Ritchie', 'Blackmore', 'ritchie@email.com', 3);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (1, 'John', 'Lennon', 'lennon@email.com', 1);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (9, 'Tina', 'Turner', 'tina@email.com', 2);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (10, 'Till', 'Lindemann', 'till@email.com', 3);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (11, 'Alice', 'Cooper', 'alice@email.com', 1);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (12, 'Sandra', 'Nasic', 'sandra@email.com', 2);
INSERT INTO university_schedule.users OVERRIDING SYSTEM VALUE VALUES (13, 'Eric', 'Clapton', 'eric@email.com', 3);


SELECT pg_catalog.setval('university_schedule.classrooms_id_seq', 7, true);


SELECT pg_catalog.setval('university_schedule.groups_id_seq', 3, true);


SELECT pg_catalog.setval('university_schedule.lections_id_seq', 26, true);


SELECT pg_catalog.setval('university_schedule.schedule_id_seq', 15, true);


SELECT pg_catalog.setval('university_schedule.users_id_seq', 13, true);



