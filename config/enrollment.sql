drop sequence clazz_seq;
drop view v_enrolled_clazz;
drop view v_clazz;
drop view v_user_roles;
drop table enrolled_clazz;
drop table student;
drop table user_roles;
drop table roles;
drop table users;
drop table clazz;
drop table teacher;
drop table schedule;
drop table subject;

grant all privileges to enrollment;

create table subject (
	id number(19) primary key,
	name varchar2(50) not null unique,
	subject_type char(1) not null
);

create table schedule (
	id number(19) primary key,
	period char(7) not null unique
);

create table teacher (
	id number(19) primary key,
	name varchar2(50) not null unique
);

create table clazz (
	id number(19) primary key,
	subject_fk number(19) not null references subject (id),
	schedule_fk number(19) not null references schedule (id),
	teacher_fk number(19) not null references teacher (id),
	constraint clazz_unq unique (subject_fk, schedule_fk, teacher_fk) 
);

create table users (
	id number(19) primary key,
	username varchar2(20) not null unique,
	password char(64) not null
);

create table roles (
	id number(19) primary key,
	role_name varchar2(10) not null unique
);

create table user_roles (
	user_id number(19) references users (id),
	role_id number(19) references roles (id),
	constraint user_roles_pk primary key (user_id, role_id)
);

create table student (
	id number(19) primary key references users (id),
	name varchar2(50) not null
);

create table enrolled_clazz (
	student_id number(19) references student (id),
	clazz_id number(19) references clazz (id),
	constraint enrolled_clazz_pk primary key (student_id, clazz_id)
);

create view v_user_roles as
	select u.username, u.password, r.role_name
	from users u, roles r, user_roles ur
	where u.id = ur.user_id and r.id = ur.role_id;

create view v_clazz as
	select cl.id as clazz_id,
		subj.id as subject_id,
		subj.name as subject_name,
		subj.subject_type as subject_type,
		sched.id as schedule_id,
		sched.period as schedule_period,
		teach.id as teacher_id,
		teach.name as teacher_name
	from subject subj join clazz cl on subj.id = cl.subject_fk
		join schedule sched on sched.id = cl.schedule_fk
		join teacher teach on teach.id = cl.teacher_fk
	order by cl.id;
	
create view v_enrolled_clazz as
	select ec.student_id, cl.*
	from enrolled_clazz ec join v_clazz cl on ec.clazz_id = cl.clazz_id
	order by ec.student_id, ec.clazz_id;
	
create sequence clazz_seq start with 1;

insert into subject values (1, 'Math', 'U');
insert into subject values (2, 'Science', 'U');
insert into subject values (3, 'Biology', 'G');
insert into subject values (4, 'Chemistry', 'G');
insert into subject values (5, 'Physics', 'G');

insert into schedule values (1, 'MWF 1-2');
insert into schedule values (2, 'MWF 2-3');
insert into schedule values (3, 'MWF 3-4');
insert into schedule values (4, 'MWF 4-5');
insert into schedule values (5, 'MWF 5-6');
insert into schedule values (6, 'TTH 1-2');
insert into schedule values (7, 'TTH 2-3');
insert into schedule values (8, 'TTH 3-4');
insert into schedule values (9, 'TTH 4-5');
insert into schedule values (10, 'TTH 5-6');

insert into teacher values (1, 'Mr. Barry Gibbs');
insert into teacher values (2, 'Mr. Klaus Maine');
insert into teacher values (3, 'Mr. James Hetfield');
insert into teacher values (4, 'Mr. Peter Cetera');
insert into teacher values (5, 'Mr. Bobby Kimball');

insert into users values (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
insert into users values (2, 'mramos', '9baf3a40312f39849f46dad1040f2f039f1cffa1238c41e9db675315cfad39b6');
insert into users values (3, 'rquitasol', 'e35990dc4c3247d672aba72bb97adac0b6e0893d8fcbf94bf3ac45c7610eca55');

insert into roles values (1, 'admin');
insert into roles values (2, 'student');

insert into user_roles values ('1', '1');
insert into user_roles values ('2', '2');
insert into user_roles values ('3', '2');

insert into student values (2, 'Max Ramos');
insert into student values (3, 'Romel Quitasol');

select * from subject;
select * from schedule;
select * from teacher;
select * from clazz;
select * from users;
select * from roles;
select * from user_roles;
select * from student;
select * from enrolled_clazz;
select * from v_user_roles;
select * from v_clazz;
select * from v_enrolled_clazz;
