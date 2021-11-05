create table users(
	us_id serial
		constraint user_pk
			primary key,
	us_name varchar(60) not null,
	us_bdate date not null,
	us_gender char(1) not null,
	us_email varchar(30),
	us_country varchar(30),
	us_bio varchar(400),
	us_dist int default 0
);

create table routes (
	rt_id serial
		constraint routes_pk
			primary key,
	rt_name varchar(60),
	rt_bio varchar(400),
	rt_dist float
);

create table spots (
	sp_id serial
		constraint spot_pk
			primary key,
	sp_name varchar(60) not null,
	sp_lat float not null,
	sp_long float not null,
	sp_price boolean,
	sp_bio varchar(400)
);

create table route_spots (
	rs_id serial
		constraint route_spots_pk
			primary key,
	rs_rt_id int not null,
	rs_sp_id int not null
);

create table tags (
	tg_id serial
		constraint tags_pk
			primary key,
	tg_name varchar(30) not null,
	tg_tt_id int not null
);

create table tag_types (
    tt_id   serial
        constraint tag_types_pk
            primary key,
    tt_name varchar(30) not null
);

create table user_tags (
	ut_id serial
		constraint user_tags_pk
			primary key,
	ut_tg_id int not null,
	ut_us_id int not null
);

create table spot_tags (
	st_id serial
		constraint spot_tags_pkss
			primary key,
	st_sp_id int not null,
	st_tg_id int not null
);

create table spot_evaluations (
	se_id serial
		constraint spot_evaluations_pk
			primary key,
	se_rate int,
	se_comment varchar(400),
	se_us_id int not null,
	se_sp_id int not null
);

create table fav_spots (
	fs_id serial
		constraint fav_spots_pk
			primary key,
	fs_us_id int not null,
	fs_sp_id int not null
);

create table route_evaluations (
	re_id serial
		constraint route_evaluations_pk
			primary key,
	re_rate int,
	re_comment varchar(400),
	re_us_id int not null,
	re_rt_id int not null
);

create table type_users (
	tu_id serial
		constraint type_users_pk
			primary key,
	tu_name varchar(30)
);

create table user_achievements (
	ua_id serial
		constraint user_achievements_pk
			primary key,
	ua_date date,
	ua_us_id int not null,
	ua_ac_id int not null
);

create table achivements (
	ac_id serial
		constraint achivement_pk
			primary key,
	ac_name varchar(30)
);
