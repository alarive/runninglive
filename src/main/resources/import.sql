insert into user(id, username, password, height) values (1, 'aurelien', 'l4r1v3', 185);
insert into user(id, username, password, height) values (2,'jessica','d4ussy', 180);
insert into user(id, username, password, height) values (3,'sahbi','kt1f4', 182);
 
insert into role(id, role_name) values (1, 'ROLE_RUNNER');
insert into role(id, role_name) values (2, 'ROLE_ORGANIZER');
insert into role(id, role_name) values (3, 'ROLE_DEVELOPER');
insert into role(id, role_name) values (4, 'ROLE_USER');

insert into user_role(user_id, role_id) values (1, 1);
insert into user_role(user_id, role_id) values (1, 3);
insert into user_role(user_id, role_id) values (2, 2);
insert into user_role(user_id, role_id) values (3, 1);
insert into user_role(user_id, role_id) values (1, 4);
insert into user_role(user_id, role_id) values (2, 4);
insert into user_role(user_id, role_id) values (3, 4);

insert into competition(id, date_and_time, name, organizer_id) values (1, '2016-04-03', 'Marathon de Paris 2016', 2)
insert into competition(id, date_and_time, name, organizer_id) values (2, '2015-09-13', 'Frappadingue Opale X''TREM 2015', 2)
insert into competition(id, date_and_time, name, organizer_id) values (3, '2015-10-03', 'Transquar Beauvais 2015', 2)
