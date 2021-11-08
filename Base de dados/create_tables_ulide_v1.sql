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
	us_dist int default 0,
	us_tu_id int default 2
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

create table users_spots_tags (
	ust_id serial
		constraint users_spots_tags_pk
			primary key,
	ust_sp_id int not null,
	ust_tg_id int not null,
    ust_us_id int not null
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
	tu_name varchar(30) not null
);

create table user_achievements (
	ua_id serial
		constraint user_achievements_pk
			primary key,
	ua_date date,
	ua_us_id int not null,
	ua_ac_id int not null
);

create table achievements (
	ac_id serial
		constraint achievements_pk
			primary key,
	ac_name varchar(30) not null
);

create table done_routes
(
    dr_id serial
        constraint done_routes_pk
            primary key,
    dr_us_id int not null,
    dr_rt_id int not null
);

create table done_spots
(
    ds_id serial
        constraint done_spots_pk
            primary key,
    ds_us_id int not null,
    ds_sp_id int not null
);

create table progress_routes
(
    pr_id serial
        constraint progress_routes_pk
            primary key,
    pr_us_id int not null,
    pr_rt_id int not null,
    pr_sp_id int not null
);



