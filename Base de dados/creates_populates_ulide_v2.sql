create table achievements
(
    ac_id   serial
        constraint achievements_pkey
            primary key,
    ac_name varchar(30) not null
);

INSERT INTO public.achievements (ac_id, ac_name) VALUES (1, '1 Museu Visitado');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (2, '5 Museus Visitados');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (3, '10 Museus Visitados');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (4, '1 Rota Concluída');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (5, '5 Rotas Concluídas');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (6, '10 Rotas Concluídas');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (7, '1 Ponto Medieval Visitado');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (8, '5 Pontos Medievais Visitados');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (9, '10 Pontos Medievais Visitados');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (10, '1 km Percorrido');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (11, '5 km Percorridos');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (12, '20 km Percorridos');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (13, '5 Locais Visitados');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (14, '15 Locais Visitados');
INSERT INTO public.achievements (ac_id, ac_name) VALUES (15, '30 Locais Visitados');

create table done_routes
(
    dr_id    serial
        constraint done_routes_pkey
            primary key,
    dr_us_id integer not null
        constraint done_routes_dr_us_id_fkey
            references users,
    dr_rt_id integer not null
        constraint done_routes_dr_rt_id_fkey
            references routes
);

INSERT INTO public.done_routes (dr_id, dr_us_id, dr_rt_id) VALUES (1, 2, 1);
INSERT INTO public.done_routes (dr_id, dr_us_id, dr_rt_id) VALUES (2, 3, 3);
INSERT INTO public.done_routes (dr_id, dr_us_id, dr_rt_id) VALUES (3, 4, 4);
INSERT INTO public.done_routes (dr_id, dr_us_id, dr_rt_id) VALUES (4, 5, 2);

create table fav_routes
(
    fr_id    serial
        constraint fav_routes_pkey
            primary key,
    fr_us_id integer not null
        constraint fav_routes_fr_us_id_fkey
            references users,
    fr_rt_id integer not null
        constraint fav_routes_fr_rt_id_fkey
            references routes
);

INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (1, 2, 1);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (2, 3, 1);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (3, 4, 1);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (4, 5, 1);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (5, 9, 1);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (6, 2, 1);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (7, 3, 3);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (8, 4, 3);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (9, 6, 3);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (10, 2, 4);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (11, 8, 4);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (12, 9, 4);
INSERT INTO public.fav_routes (fr_id, fr_us_id, fr_rt_id) VALUES (13, 5, 5);

create table fav_spots
(
    fs_id    serial
        constraint fav_spots_pkey
            primary key,
    fs_us_id integer not null
        constraint fav_spots_fs_us_id_fkey
            references users,
    fs_sp_id integer not null
        constraint fav_spots_fs_sp_id_fkey
            references spots
);

INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (1, 2, 5);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (2, 2, 6);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (3, 2, 13);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (4, 3, 3);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (5, 3, 5);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (6, 3, 7);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (7, 4, 1);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (8, 4, 2);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (9, 4, 4);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (10, 4, 10);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (12, 5, 8);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (13, 5, 9);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (14, 5, 11);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (15, 5, 12);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (16, 5, 15);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (17, 6, 4);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (18, 6, 8);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (19, 6, 10);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (20, 6, 15);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (21, 6, 16);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (22, 7, 6);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (23, 7, 13);
INSERT INTO public.fav_spots (fs_id, fs_us_id, fs_sp_id) VALUES (24, 7, 16);

create table progress_routes
(
    pr_id    serial
        constraint progress_routes_pkey
            primary key,
    pr_us_id integer not null
        constraint progress_routes_pr_us_id_fkey
            references users,
    pr_rt_id integer not null
        constraint progress_routes_pr_rt_id_fkey
            references routes,
    pr_sp_id integer not null
        constraint progress_routes_pr_sp_id_fkey
            references spots
);

INSERT INTO public.progress_routes (pr_id, pr_us_id, pr_rt_id, pr_sp_id) VALUES (1, 2, 2, 16);

create table route_evaluations
(
    re_id      serial
        constraint route_evaluations_pkey
            primary key,
    re_rate    integer,
    re_comment varchar(400),
    re_us_id   integer not null
        constraint route_evaluations_re_us_id_fkey
            references users,
    re_rt_id   integer not null
        constraint route_evaluations_re_rt_id_fkey
            references routes
);

INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (1, 5, 'Very good!', 2, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (2, 3, null, 3, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (3, 2, null, 4, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (4, 4, null, 5, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (5, 5, null, 6, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (6, 3, null, 7, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (7, 4, null, 8, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (8, 5, null, 9, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (9, 4, null, 10, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (10, 2, null, 11, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (11, 5, null, 6, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (12, 4, null, 7, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (13, 2, null, 8, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (14, 4, null, 9, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (15, 3, null, 10, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (16, 2, null, 11, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (17, 5, null, 6, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (18, 5, null, 7, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (19, 5, null, 8, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (20, 5, null, 9, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (21, 5, null, 10, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (22, 4, null, 11, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (23, 4, null, 6, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (24, 4, null, 7, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (25, 4, null, 8, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (26, 4, null, 9, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (27, 4, null, 10, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (28, 3, null, 11, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (41, 0, null, 1, 1);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (42, 0, null, 1, 2);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (43, 0, null, 1, 3);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (44, 0, null, 1, 4);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (45, 0, null, 1, 5);
INSERT INTO public.route_evaluations (re_id, re_rate, re_comment, re_us_id, re_rt_id) VALUES (46, 0, null, 1, 7);

create table route_spots
(
    rs_id    serial
        constraint route_spots_pkey
            primary key,
    rs_rt_id integer not null
        constraint route_spots_rs_rt_id_fkey
            references routes,
    rs_sp_id integer not null
        constraint route_spots_rs_sp_id_fkey
            references spots
);

INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (1, 1, 12);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (2, 1, 13);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (3, 1, 14);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (4, 1, 15);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (5, 2, 8);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (6, 2, 16);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (7, 2, 6);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (8, 3, 1);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (9, 3, 2);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (10, 3, 5);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (11, 3, 11);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (12, 3, 16);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (13, 4, 5);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (14, 4, 2);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (15, 5, 1);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (16, 5, 2);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (17, 5, 4);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (18, 5, 9);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (19, 5, 11);
INSERT INTO public.route_spots (rs_id, rs_rt_id, rs_sp_id) VALUES (20, 5, 12);

create table routes
(
    rt_id   serial
        constraint routes_pkey
            primary key,
    rt_name varchar(60) not null,
    rt_bio  varchar(400),
    rt_dist double precision
);

INSERT INTO public.routes (rt_id, rt_name, rt_bio, rt_dist) VALUES (1, 'Compras', 'Capitalismo no seu melhor', 4);
INSERT INTO public.routes (rt_id, rt_name, rt_bio, rt_dist) VALUES (2, 'Natureza', 'Muitos animais agua e plantas, brother!!!', 10);
INSERT INTO public.routes (rt_id, rt_name, rt_bio, rt_dist) VALUES (3, 'Histórica', null, 3);
INSERT INTO public.routes (rt_id, rt_name, rt_bio, rt_dist) VALUES (4, 'Popular', null, 2);
INSERT INTO public.routes (rt_id, rt_name, rt_bio, rt_dist) VALUES (5, 'Gratis', null, 6);
INSERT INTO public.routes (rt_id, rt_name, rt_bio, rt_dist) VALUES (7, 'Moderna', null, 10.2);

create table spot_evaluations
(
    se_id      serial
        constraint spot_evaluations_pkey
            primary key,
    se_rate    integer,
    se_comment varchar(400),
    se_us_id   integer not null
        constraint spot_evaluations_se_us_id_fkey
            references users,
    se_sp_id   integer not null
        constraint spot_evaluations_se_sp_id_fkey
            references spots
);

INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (1, 5, 'Fantastic', 2, 12);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (2, 4, 'Mui bueno', 2, 13);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (3, 1, 'Not for me', 2, 14);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (4, 5, 'Im a tea pot', 2, 15);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (5, 4, null, 2, 8);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (6, 5, null, 3, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (7, 4, null, 3, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (8, 2, null, 3, 5);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (9, 3, null, 3, 11);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (10, 4, null, 3, 16);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (11, 5, null, 4, 5);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (12, 4, null, 4, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (13, 3, null, 5, 8);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (14, 4, null, 5, 16);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (15, 5, null, 5, 6);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (16, 5, null, 6, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (17, 5, null, 6, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (18, 1, null, 6, 3);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (19, 4, null, 6, 4);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (20, 5, null, 6, 6);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (21, 4, null, 6, 12);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (22, 4, null, 6, 13);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (23, 4, null, 6, 15);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (24, 1, null, 7, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (25, 5, null, 7, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (26, 4, null, 7, 3);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (27, 3, null, 7, 4);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (28, 5, null, 7, 6);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (29, 5, null, 7, 12);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (30, 5, null, 7, 13);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (31, 4, null, 7, 15);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (32, 4, null, 8, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (33, 1, null, 8, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (34, 5, null, 8, 3);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (35, 3, null, 8, 4);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (36, 2, null, 9, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (37, 3, null, 9, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (38, 4, null, 9, 3);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (39, 4, null, 9, 4);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (40, 3, null, 10, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (41, 2, null, 10, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (42, 2, null, 10, 3);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (43, 4, null, 10, 4);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (44, 5, null, 11, 1);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (45, 2, null, 11, 2);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (46, 1, null, 11, 3);
INSERT INTO public.spot_evaluations (se_id, se_rate, se_comment, se_us_id, se_sp_id) VALUES (47, 3, null, 11, 4);

create table spots
(
    sp_id    serial
        constraint spots_pkey
            primary key,
    sp_name  varchar(60)      not null,
    sp_lat   double precision not null,
    sp_long  double precision not null,
    sp_price boolean,
    sp_bio   varchar(400)
);

INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (20, 'Basilica da Estrela', 38.7135853, -9.160129, null, null);
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (21, 'testetestado', 37.421998333333335, -122.08400000000002, null, null);
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (23, 'reyhrsedfg', 37.421998333333335, -122.08400000000002, null, null);
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (24, 'sgsadg', 37.421998333333335, -122.08400000000002, null, null);
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (26, 'Local de Teste 005', 25.567, -8.89746, null, null);
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (10, 'Jardim Zoológico', 38.744495366088586, -9.170685015893174, true, 'Zoológico urbano com 300 espécies, fazendinha e programa de preservação, além de shows diários com golfinhos.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (6, 'Elevador da Glória', 38.716897389128455, -9.142660122889284, true, 'Elétrico com interior em madeira e latão que sobe uma colina íngreme até um local com vista panorâmica para a cidade.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (15, 'Lx Factory', 38.70366533341345, -9.178862273566953, null, 'Este complexo industrial histórico abriga diversos varejistas de arte e restaurantes exclusivos.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (8, 'Oceanário', 38.76371079269964, -9.093720044728894, true, 'Aquário moderno à beira-mar com habitats oceânicos para tubarões, arraias, pinguins e peixes tropicais.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (2, 'Mosteiro dos Jerónimos', 38.69806671988026, -9.206671715894556, false, 'Monastério em estilo manuelino gótico que abriga nas suas alas dois museus: um marítimo e outro arqueológico.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (11, 'Panteão Nacional', 38.715061350403744, -9.12490880796462, false, 'Igreja barroca do século XVII transformada em um mausoléu moderno para os túmulos das celebridades nacionais.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (14, 'Mercado de Campo de Ourique', 38.707173108334246, -9.143361173566847, null, 'Mercado movimentado onde vendedores de comida dividem espaço com bares e restaurantes modernos.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (1, 'Torre de Belém', 38.69175116131192, -9.215966573567318, false, 'Torre fortificada medieval com terraço e vista para o estuário, localizada em uma ilha fluvial.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (12, 'Mercado da Ribeira', 38.70690732582062, -9.145907544730553, false, 'Mercado espaçoso com dois andares e design aberto, além de refeições, plantas, artes, livros e antiguidades.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (7, 'Estádio da Luz', 38.752886685013436, -9.184763273565512, true, 'Estádio do SL Benfica, com 65.000 assentos em 3 andares. Projetado por Damon Lavelle.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (4, 'Padrão dos Descobrimentos', 38.6936015, -9.2079002, false, 'Monumento erguido em 1940 e perpetuado em 1960 em celebração dos 500 anos da morte de Henrique, o Navegador.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (3, 'Castelo de São Jorge', 38.71405986610344, -9.133454744730386, true, 'Castelo mouro e residência real do séc. XI em uma colina, onde há ruínas do palácio e museu arqueológico.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (13, 'Mercado do Príncipe Real', 38.716159660346214, -9.149212773566605, null, 'Feira semanal de produtores locais com foco em produtos orgânicos e saudáveis.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (9, 'Praça do Comércio', 38.70759494492502, -9.13644380826236, false, 'Praça pública à beira-mar com um arco e uma estátua famosos, repleta de cafés ao ar livre e lojas.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (5, 'Museu da Cidade', 38.7585930361228, -9.156325382521535, true, 'Museu sobre a história de Lisboa com acervo que abrange o período da pré-história até o início do século 20.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (16, 'Miradouro de Santa Catarina', 38.709725975239834, -9.147623365894209, null, 'O miradouro de Santa Catarina, conhecido também como miradouro de Adamastor, está situado numa das sete colinas de Lisboa e é afamado como miradouro e retiro de observadores.');
INSERT INTO public.spots (sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio) VALUES (27, 'Castelo de sao jorge', 37.421998333333335, -122.08400000000002, null, null);

create table tag_types
(
    tt_id   serial
        constraint tag_types_pkey
            primary key,
    tt_name varchar(30) not null
);

INSERT INTO public.tag_types (tt_id, tt_name) VALUES (1, 'User');
INSERT INTO public.tag_types (tt_id, tt_name) VALUES (2, 'System');

create table tags
(
    tg_id    serial
        constraint tags_pkey
            primary key,
    tg_name  varchar(30) not null,
    tg_tt_id integer     not null
        constraint tags_tg_tt_id_fkey
            references tag_types
);

INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (1, 'Mercado', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (2, 'Monumento', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (3, 'Natureza', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (4, 'Museu', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (5, 'Desporto', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (6, 'Festa', 2);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (7, 'Cool', 2);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (8, 'Sexta-feira', 2);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (9, 'Mediaval', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (10, 'Ar livre', 2);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (11, 'Conhecimento', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (12, 'Elavdor', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (13, 'Estádio', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (14, 'Criança', 1);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (15, 'TagTeste01', 2);
INSERT INTO public.tags (tg_id, tg_name, tg_tt_id) VALUES (16, 'TagTeste02', 1);

create table type_users
(
    tu_id   serial
        constraint type_users_pkey
            primary key,
    tu_name varchar(30) not null
);

INSERT INTO public.type_users (tu_id, tu_name) VALUES (1, 'Super');
INSERT INTO public.type_users (tu_id, tu_name) VALUES (2, 'Nivel 1');
INSERT INTO public.type_users (tu_id, tu_name) VALUES (3, 'Nivel 2');
INSERT INTO public.type_users (tu_id, tu_name) VALUES (4, 'Nivel 3');
INSERT INTO public.type_users (tu_id, tu_name) VALUES (5, 'Nivel 4');

create table user_achievements
(
    ua_id    serial
        constraint user_achievements_pkey
            primary key,
    ua_date  date,
    ua_us_id integer not null
        constraint user_achievements_ua_us_id_fkey
            references users,
    ua_ac_id integer not null
        constraint user_achievements_ua_ac_id_fkey
            references achievements
);

INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (1, '2021-11-19', 5, 1);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (2, '2021-08-23', 5, 2);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (3, '2021-05-01', 5, 10);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (4, '2021-07-08', 5, 11);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (5, '2021-10-12', 5, 13);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (6, '2021-06-03', 2, 1);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (7, '2020-12-29', 2, 2);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (8, '2021-04-17', 2, 3);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (9, '2021-11-19', 2, 13);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (10, '2021-05-30', 3, 4);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (11, '2021-11-11', 3, 5);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (12, '2021-12-05', 3, 10);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (13, '2021-08-13', 3, 11);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (14, '2021-11-19', 3, 13);
INSERT INTO public.user_achievements (ua_id, ua_date, ua_us_id, ua_ac_id) VALUES (15, '2021-11-11', 4, 1);

create table users
(
    us_id       serial
        constraint users_pkey
            primary key,
    us_name     varchar(60) not null,
    us_bdate    date        not null,
    us_gender   char        not null,
    us_email    varchar(30),
    us_country  varchar(30),
    us_bio      varchar(400),
    us_dist     integer default 0,
    us_tu_id    integer default 2
        constraint users_us_tu_id_fkey
            references type_users,
    us_username varchar(20) not null,
    us_password varchar(60) not null
);

create unique index users_us_username_uindex
    on users (us_username);

INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (8, 'Constantino Pinho', '1965-09-27', 'M', null, null, null, 0, 2, 'Leonerdo15', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (10, 'Plácido Ramires', '1992-07-17', 'M', null, null, null, 0, 2, '123qaz', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (5, 'Teobaldo Mata', '1946-10-25', 'M', 'teokiller@gmail.com', 'Germany', null, 0, 2, 'wsx123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (4, 'Jordana Santiago', '1976-11-27', 'F', 'jordsatanas@email.com', 'Spain', null, 0, 2, 'asd123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (7, 'Brígida Campos', '1996-05-09', 'F', null, null, null, 0, 2, '123wsx', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (9, 'Núria Vale', '1983-01-30', 'F', null, null, null, 0, 2, '123edc', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (2, 'Caetano Feliciano', '1997-08-30', 'M', 'catefe@gmail.com', 'Germany', 'I like very big horses', 0, 2, 'qwe123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (11, 'Nicolau Esteves', '2001-10-01', 'M', null, null, null, 0, 2, 'edc123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (6, 'Salomé Pereyra', '1952-07-05', 'F', 'salmepere@gmail.com', null, null, 0, 2, 'zxc123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (1, 'Super Ulide', '2021-11-07', 'M', null, null, null, 0, 1, 'qaz123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (3, 'Teófilo Vale', '2003-05-01', 'M', 'Tevale12@hotmail.com', 'United States', 'Hamburger with fries and coke', 0, 2, 'mainAccount123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (12, 'Felipe Sabbado', '1986-05-08', 'M', null, null, null, null, null, 'fsabbado', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (13, 'Leonardo lage', '2021-10-12', 'M', 'Leonardo15', null, null, null, null, 'l@gmail.com', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (14, 'Leonardo Lage', '2021-10-12', 'M', 'Leonardo14', null, null, null, null, 'le@gmail.com', '1234567');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (15, 'Leonardo Lage', '2021-11-12', 'M', 'Leonerdo13', null, null, null, null, 'leo@gmail.com', '1234567');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (16, 'hdjsjs dhdjs', '2021-12-18', 'M', 'hdhdjsjsjs', null, null, null, null, 'hshs', 'nxnxjdjdjd');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (17, 'jsjsjstsg', '2021-11-12', 'M', 'jdjdhs@.com', null, null, null, null, 'isjsksjshcid', 'nxnskaix8xhdnd');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (18, 'idososow', '2021-10-12', 'M', 'baja@gmail.com', null, null, null, null, 'poi123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (19, 'tetsts', '1997-08-30', 'M', 'ce@gmail.com', null, null, null, null, 'btgt123', '123456');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (20, 'hdhsjs', '2021-12-15', 'M', 'jdjwkq8', null, null, null, null, 'hdhsjs8o1', 'nxnsksiai');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (21, 'fgbfdgbd', '2021-12-13', 'M', 'asdfdasf', null, null, null, null, 'asdfasdf', 'fgbfgdbfgdbfg');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (22, 'dfafas', '2021-12-30', 'M', 'asdfasdf', null, null, null, null, 'sdafasdf', 'asdfads');
INSERT INTO public.users (us_id, us_name, us_bdate, us_gender, us_email, us_country, us_bio, us_dist, us_tu_id, us_username, us_password) VALUES (23, 'Teste01', '2021-12-17', 'M', 'fadsfa@dfadfad.com', null, null, null, null, 'Teste01', '123456');


create table users_spots_tags
(
    ust_id    serial
        constraint users_spots_tags_pkey
            primary key,
    ust_sp_id integer not null
        constraint users_spots_tags_ust_sp_id_fkey
            references spots,
    ust_tg_id integer not null
        constraint users_spots_tags_ust_tg_id_fkey
            references tags,
    ust_us_id integer not null
        constraint users_spots_tags_ust_us_id_fkey
            references users
);

INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (1, 1, 2, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (2, 1, 7, 3);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (3, 2, 2, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (4, 2, 9, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (5, 3, 2, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (6, 3, 9, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (7, 3, 8, 2);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (8, 4, 2, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (9, 4, 8, 3);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (10, 4, 10, 4);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (11, 5, 2, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (12, 5, 4, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (13, 5, 11, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (14, 6, 12, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (15, 7, 5, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (16, 7, 13, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (17, 8, 3, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (18, 8, 11, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (19, 8, 14, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (20, 9, 10, 3);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (21, 10, 3, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (22, 10, 10, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (23, 10, 14, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (24, 11, 2, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (25, 11, 11, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (26, 12, 1, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (27, 12, 8, 4);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (28, 12, 8, 2);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (29, 13, 1, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (30, 13, 8, 2);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (31, 13, 10, 3);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (32, 14, 1, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (33, 15, 1, 1);
INSERT INTO public.users_spots_tags (ust_id, ust_sp_id, ust_tg_id, ust_us_id) VALUES (34, 15, 10, 3);
