drop table s_users;
drop table s_roles;
drop table s_users_roles;

create table s_users(
    id VARCHAR(35) PRIMARY KEY,
    username VARCHAR(35) NOT NULL,
    password varchar(255) NOT NULL,
    active BOOLEAN
);

insert into s_users(id, username, password, active)
values('u001','sofwan','123',true);

insert into s_users(id, username, password, active)
values('u002','fauzi','123',true);

create table s_roles(
    id VARCHAR(35) PRIMARY KEY,
    nama varchar(255)
);

insert into s_roles(id, nama)
values ('adm','ROLE_ADMIN');

insert into s_roles(id, nama)
values ('stf','ROLE_STAF');

create table s_users_roles(
    id_users VARCHAR(35) NOT NULL,
    id_roles VARCHAR(35) NOT NULL
);

insert into s_users_roles(id_users, id_roles)
values ('u001','adm');

insert into s_users_roles(id_users, id_roles)
values ('u002','stf');

select username, password, active as enabled from s_users where username='sofwan';

select u.username, r.nama as authority from s_users u join s_users_roles ur on u.id = ur.id_users
join s_roles r on ur.id_roles = r.id where u.username = 'sofwan';






