
---------------------------------------- Tags --------------------------------------
create table tag_types
(
    tt_id   serial,
    tt_name varchar(30) not null,
    primary key (tt_id)
);

create table tags
(
    tg_id serial
        constraint tags_pk
            primary key,
    tg_name varchar(30) not null,
    tg_tt_id int not null,
    primary key (tg_id),
    foreign key (tg_tt_id) references tag_types(tt_id)
);
----------------------------------- End Tags ----------------------------------------

------------------------------------- Users -----------------------------------------
create table type_users
(
    tu_id serial,
    tu_name varchar(30) not null,
    primary key (tu_id)
);

create table users
(
    us_id serial,
    us_name varchar(60) not null,
    us_bdate date not null,
    us_gender char(1) not null,
    us_email varchar(30),
    us_country varchar(30),
    us_bio varchar(400),
    us_dist int default 0,
    us_tu_id int default 2,
    primary key (us_id),
    foreign key (us_tu_id) references type_users(tu_id)
);
------------------------------------- End Users -----------------------------------

------------------------------------ Spots ---------------------------------------
create table spots
(
    sp_id serial,
    sp_name varchar(60) not null,
    sp_lat float not null,
    sp_long float not null,
    sp_price boolean,
    sp_bio varchar(400),
    primary key (sp_id)
);


create table spot_evaluations
(
    se_id serial,
    se_rate int,
    se_comment varchar(400),
    se_us_id int not null,
    se_sp_id int not null,
    primary key (se_id),
    foreign key (se_us_id) references users(us_id),
    foreign key (se_sp_id) references spots(sp_id)
);

create table users_spots_tags
(
    ust_id serial,
    ust_sp_id int not null,
    ust_tg_id int not null,
    ust_us_id int not null,
    primary key (ust_id),
    foreign key (ust_sp_id) references spots(sp_id),
    foreign key (ust_tg_id) references tags(tg_id),
    foreign key (ust_us_id) references users(us_id)
);

create table fav_spots
(
    fs_id serial,
    fs_us_id int not null,
    fs_sp_id int not null,
    primary key (fs_id),
    foreign key (fs_us_id) references users(us_id),
    foreign key (fs_sp_id) references spots(sp_id)
);

create table done_spots
(
    ds_id serial,
    ds_us_id int not null,
    ds_sp_id int not null,
    primary key (ds_id),
    foreign key (ds_us_id) references users(us_id),
    foreign key (ds_sp_id) references spots(sp_id)
);
-------------------------------------- End spots -------------------------------------

------------------------------------ Routes ---------------------------------------

create table routes
(
    rt_id serial,
    rt_name varchar(60) not null,
    rt_bio varchar(400),
    rt_dist float,
    primary key (rt_id)
);

create table fav_routes
(
    fr_id serial,
    fr_us_id int not null,
    fr_rt_id int not null,
    primary key (fr_id),
    foreign key (fr_us_id) references users(us_id),
    foreign key (fr_rt_id) references routes(rt_id)
);

create table route_evaluations
(
    re_id serial,
    re_rate int,
    re_comment varchar(400),
    re_us_id int not null,
    re_rt_id int not null,
    primary key (re_id),
    foreign key (re_us_id) references users(us_id),
    foreign key (re_rt_id) references routes(rt_id)
);

create table done_routes
(
    dr_id serial,
    dr_us_id int not null,
    dr_rt_id int not null,
    primary key (dr_id),
    foreign key (dr_us_id) references users(us_id),
    foreign key (dr_rt_id) references routes(rt_id)
);

create table progress_routes
(
    pr_id serial,
    pr_us_id int not null,
    pr_rt_id int not null,
    pr_sp_id int not null,
    primary key (pr_id),
    foreign key (pr_us_id) references users(us_id),
    foreign key (pr_rt_id) references routes(rt_id),
    foreign key (pr_sp_id) references spots(sp_id)
);

create table route_spots
(
    rs_id serial,
    rs_rt_id int not null,
    rs_sp_id int not null,
    primary key (rs_id),
    foreign key (rs_sp_id) references spots(sp_id),
    foreign key (rs_rt_id) references routes(rt_id)
);
----------------------------------- End Routes -----------------------------------

--------------------------------- Achievements ----------------------------------

create table achievements
(
    ac_id serial,
    ac_name varchar(30) not null,
    primary key (ac_id)
);

create table user_achievements
(
    ua_id serial,
    ua_date date,
    ua_us_id int not null,
    ua_ac_id int not null,
    primary key (ua_id),
    foreign key (ua_us_id) references users(us_id),
    foreign key (ua_ac_id) references achievements(ac_id)
);
----------------------------- End Achievements ---------------------------------
















