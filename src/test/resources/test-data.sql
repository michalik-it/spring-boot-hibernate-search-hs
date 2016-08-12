
insert into groups(id, name, alt_name, extid, years, yearb) values (1, "Grupa spec", "Alt", 123, "2999", 2999);
insert into groups(id, name, alt_name, extid, years, yearb) values (2, "Grupa normalna", "Alt", 124, "2999", 2999);
insert into groups(id, name, alt_name, extid, years, yearb) values (3, "Grupa z archiwum X", "Alt", 125, "2999", 2999);

insert into projects(id, name, alt_name, extid, years, yearb) values (1, "Projektten", "Alt", 123, "2999", 2999);
insert into projects(id, name, alt_name, extid, years, yearb) values (2, "Projekttamten", "Alt", 124, "2999", 2999);
insert into projects(id, name, alt_name, extid, years, yearb) values (3, "Projektinny", "Alt", 125, "2999", 2999);

insert into users(id, name, email, city, group_id, years, yeari, birth_day) values (1, "Amy Henderson", "ahenderson@gmail.com", "Oklahoma City", 1, "1999", 1999, "2016-08-09 10:02:00");
insert into users(id, name, email, city, group_id, years, yeari, birth_day) values (2, "Amy Murphy", "amurphy@outlook.com", "Minneapolis", 2, "1888", 1888, "2016-08-17");
insert into users(id, name, email, yeari, years, group_id) values (3, "Amy Clark", "aclark@yahoo.com", 2, "2", 1);
insert into users(id, name, email, city, group_id) values (4, "Amy Ramirez", "aramirez@gmail.com", "Denver xxx 1999", 1);
insert into users(id, name, email, city) values (5, "Amy Roberts", "aroberts@outlook.com", "Springfield");
insert into users(id, name, email, city) values (6, "Amy Richardson", "arichardson@netgloo.com", "Miami");
insert into users(id, name, email, city) values (7, "Amy Bailey", "abailey@gmail.com", "San Jose");

insert into users_projects(id, user_id, project_id) values (1,1,1);
insert into users_projects(id, user_id, project_id) values (5,5,1);
insert into users_projects(id, user_id, project_id) values (2,1,2);
insert into users_projects(id, user_id, project_id) values (3,2,3);