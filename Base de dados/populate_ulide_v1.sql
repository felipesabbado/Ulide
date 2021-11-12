insert into tag_types (tt_name) values ('User');
insert into tag_types (tt_name) values ('System');

insert into tags (tg_name, tg_tt_id) values ('Mercado', 1);
insert into tags (tg_name, tg_tt_id) values ('Monumento', 1);
insert into tags (tg_name, tg_tt_id) values ('Natureza', 1);
insert into tags (tg_name, tg_tt_id) values ('Museu', 1);
insert into tags (tg_name, tg_tt_id) values ('Desporto', 1);
insert into tags (tg_name, tg_tt_id) values ('Festa', 2);
insert into tags (tg_name, tg_tt_id) values ('Cool', 2);
insert into tags (tg_name, tg_tt_id) values ('Sexta-feira', 2);
insert into tags (tg_name, tg_tt_id) values ('Mediaval', 1);
insert into tags (tg_name, tg_tt_id) values ('Ar livre', 2);
insert into tags (tg_name, tg_tt_id) values ('Conhecimento',1);
insert into tags (tg_name, tg_tt_id) values ('Elavdor',1);
insert into tags (tg_name, tg_tt_id) values ('Estádio', 1);
insert into tags (tg_name, tg_tt_id) values ('Criança', 1);


insert into type_users(tu_name) values ('Super');
insert into type_users(tu_name) values ('Nivel 1');
insert into type_users(tu_name) values ('Nivel 2');
insert into type_users(tu_name) values ('Nivel 3');
insert into type_users(tu_name) values ('Nivel 4');

insert into users(us_name, us_bdate, us_gender, us_tu_id)
values ('Super Ulide', to_date('2021-11-07', 'YYYY-MM-DD'), 'M', 1);
insert into users(us_name, us_bdate, us_gender, us_email, us_country, us_bio)
values ('Caetano Feliciano', to_date('1997-08-30', 'YYYY-MM-DD'), 'M', 'catefe@gmail.com', 'Germany', 'I like very big horses');
insert into users(us_name, us_bdate, us_gender, us_email, us_country, us_bio)
values ('Teófilo Vale', to_date('2003-05-01', 'YYYY-MM-DD'), 'M','Tevale12@hotmail.com', 'United States', 'Hamburger with fries and coke');
insert into users(us_name, us_bdate, us_gender, us_email, us_country)
values ('Jordana Santiago', to_date('1976-11-27', 'YYYY-MM-DD'), 'F', 'jordsatanas@email.com', 'Spain');
insert into users(us_name, us_bdate, us_gender,  us_email, us_country)
values ('Teobaldo Mata', to_date('1946-10-25', 'YYYY-MM-DD'), 'M', 'teokiller@gmail.com', 'Germany');
insert into users(us_name, us_bdate, us_gender, us_email)
values ('Salomé Pereyra', to_date('1952-07-05', 'YYYY-MM-DD'), 'F', 'salmepere@gmail.com');
insert into users(us_name, us_bdate, us_gender)
values ('Brígida Campos', to_date('1996-05-09', 'YYYY-MM-DD'), 'F');
insert into users(us_name, us_bdate, us_gender)
values ('Constantino Pinho', to_date('1965-09-27', 'YYYY-MM-DD'), 'M');
insert into users(us_name, us_bdate, us_gender)
values ('Núria Vale', to_date('1983-01-30', 'YYYY-MM-DD'), 'F');
insert into users(us_name, us_bdate, us_gender)
values ('Plácido Ramires', to_date('1992-07-17', 'YYYY-MM-DD'), 'M');
insert into users(us_name, us_bdate, us_gender)
values ('Nicolau Esteves', to_date('2001-10-01', 'YYYY-MM-DD'), 'M');

-- true is paid, false is free
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Torre de Belém', 38.69175116131192, -9.215966573567318, false);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Mosteiro dos Jerónimos', 38.69806671988026, -9.206671715894556, false);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Castelo de São Jorge', 38.71405986610344, -9.133454744730386, true);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Padrão dos Descobrimentos', 38.6936015,-9.2079002, false);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Museu da Cidade', 38.7585930361228, -9.156325382521535, true);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Elevador da Glória', 38.716897389128455, -9.142660122889284, true);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Estádio da Luz', 38.752886685013436, -9.184763273565512, true);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Oceanário', 38.76371079269964, -9.093720044728894, true);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Praça do Comércio', 38.70759494492502, -9.13644380826236, false);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Jardim Zoológico', 38.744495366088586, -9.170685015893174, true);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Panteão Nacional', 38.715061350403744, -9.12490880796462, false);
insert into spots (sp_name, sp_lat, sp_long, sp_price) values('Mercado da Ribeira', 38.70690732582062, -9.145907544730553, false);
insert into spots (sp_name, sp_lat, sp_long) values('Mercado do Príncipe Real', 38.716159660346214, -9.149212773566605);
insert into spots (sp_name, sp_lat, sp_long) values('Mercado de Campo de Ourique', 38.707173108334246, -9.143361173566847);
insert into spots (sp_name, sp_lat, sp_long) values('Lx Factory', 38.70366533341345, -9.178862273566953);
insert into spots (sp_name, sp_lat, sp_long) values('Miradouro de Santa Catarina', 38.709725975239834, -9.147623365894209);


insert into spot_evaluations(se_rate, se_comment, se_us_id, se_sp_id)
values (5, 'Fantastic', 2, 12);
insert into spot_evaluations(se_rate, se_comment, se_us_id, se_sp_id)
values (4, 'Mui bueno', 2, 13);
insert into spot_evaluations(se_rate, se_comment, se_us_id, se_sp_id)
values (1, 'Not for me', 2, 14);
insert into spot_evaluations(se_rate, se_comment, se_us_id, se_sp_id)
values (5, 'Im a tea pot', 2, 15);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (4, 2, 8);

insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (5, 3, 1);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (4, 3, 2);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (2, 3, 5);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (3, 3, 11);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (4, 3, 16);

insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (5, 4, 5);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (4, 4, 2);

insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (3, 5, 8);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (4, 5, 16);
insert into spot_evaluations(se_rate, se_us_id, se_sp_id)
values (5, 5, 6);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (1, 2, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (1, 7, 3);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (2, 2, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (2, 9, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (3, 2, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (3, 9, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (3, 8, 2);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (4, 2, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (4, 8, 3);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (4, 10, 4);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (5, 2, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (5, 4, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (5, 11, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (6, 12, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (7, 5, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (7, 13, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (8, 3, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (8, 11, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (8, 14, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (9, 10, 3);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (10, 3, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (10, 10, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (10, 14, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (11, 2, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (11, 11, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (12, 1, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (12, 8, 4);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (12, 8, 2);


insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (13, 1, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (13, 8, 2);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (13, 10, 3);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (14, 1, 1);

insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (15, 1, 1);
insert into users_spots_tags(ust_sp_id, ust_tg_id, ust_us_id) values (15, 10, 3);


insert into fav_spots(fs_us_id, fs_sp_id) values (2, 5);
insert into fav_spots(fs_us_id, fs_sp_id) values (2, 6);
insert into fav_spots(fs_us_id, fs_sp_id) values (2, 13);

insert into fav_spots(fs_us_id, fs_sp_id) values (3, 3);
insert into fav_spots(fs_us_id, fs_sp_id) values (3, 5);
insert into fav_spots(fs_us_id, fs_sp_id) values (3, 7);

insert into fav_spots(fs_us_id, fs_sp_id) values (4, 1);
insert into fav_spots(fs_us_id, fs_sp_id) values (4, 2);
insert into fav_spots(fs_us_id, fs_sp_id) values (4, 4);
insert into fav_spots(fs_us_id, fs_sp_id) values (4, 10);
insert into fav_spots(fs_us_id, fs_sp_id) values (4, 14);

insert into fav_spots(fs_us_id, fs_sp_id) values (5, 8);
insert into fav_spots(fs_us_id, fs_sp_id) values (5, 9);
insert into fav_spots(fs_us_id, fs_sp_id) values (5, 11);
insert into fav_spots(fs_us_id, fs_sp_id) values (5, 12);
insert into fav_spots(fs_us_id, fs_sp_id) values (5, 15);

insert into fav_spots(fs_us_id, fs_sp_id) values (6, 4);
insert into fav_spots(fs_us_id, fs_sp_id) values (6, 8);
insert into fav_spots(fs_us_id, fs_sp_id) values (6, 10);
insert into fav_spots(fs_us_id, fs_sp_id) values (6, 15);
insert into fav_spots(fs_us_id, fs_sp_id) values (6, 16);

insert into fav_spots(fs_us_id, fs_sp_id) values (7, 6);
insert into fav_spots(fs_us_id, fs_sp_id) values (7, 13);
insert into fav_spots(fs_us_id, fs_sp_id) values (7, 16);

insert into done_spots(ds_us_id, ds_sp_id) values (2, 12);
insert into done_spots(ds_us_id, ds_sp_id) values (2, 13);
insert into done_spots(ds_us_id, ds_sp_id) values (2, 14);
insert into done_spots(ds_us_id, ds_sp_id) values (2, 15);
insert into done_spots(ds_us_id, ds_sp_id) values (2, 8);

insert into done_spots(ds_us_id, ds_sp_id) values (3, 1);
insert into done_spots(ds_us_id, ds_sp_id) values (3, 2);
insert into done_spots(ds_us_id, ds_sp_id) values (3, 5);
insert into done_spots(ds_us_id, ds_sp_id) values (3, 11);
insert into done_spots(ds_us_id, ds_sp_id) values (3, 16);

insert into done_spots(ds_us_id, ds_sp_id) values (4, 5);
insert into done_spots(ds_us_id, ds_sp_id) values (4, 2);

insert into done_spots(ds_us_id, ds_sp_id) values (5, 8);
insert into done_spots(ds_us_id, ds_sp_id) values (5, 16);
insert into done_spots(ds_us_id, ds_sp_id) values (5, 6);

insert into routes (rt_name,rt_bio, rt_dist) values ('Compras', 'Capitalismo no seu melhor', 4);
insert into routes (rt_name, rt_bio, rt_dist) values ('Natureza', 'Muitos animais agua e plantas, brother!!!', 10);
insert into routes (rt_name,rt_dist) values ('Histórica', 3);
insert into routes (rt_name,rt_dist) values ('Popular', 2);
insert into routes (rt_name,rt_dist) values ('Gratis', 6);

insert into fav_routes(fr_us_id, fr_rt_id) values (2, 1);
insert into fav_routes(fr_us_id, fr_rt_id) values (3, 1);
insert into fav_routes(fr_us_id, fr_rt_id) values (4, 1);
insert into fav_routes(fr_us_id, fr_rt_id) values (5, 1);
insert into fav_routes(fr_us_id, fr_rt_id) values (9, 1);
insert into fav_routes(fr_us_id, fr_rt_id) values (2, 1);

insert into fav_routes(fr_us_id, fr_rt_id) values (3, 3);
insert into fav_routes(fr_us_id, fr_rt_id) values (4, 3);
insert into fav_routes(fr_us_id, fr_rt_id) values (6, 3);

insert into fav_routes(fr_us_id, fr_rt_id) values (2, 4);
insert into fav_routes(fr_us_id, fr_rt_id) values (8, 4);
insert into fav_routes(fr_us_id, fr_rt_id) values (9, 4);

insert into fav_routes(fr_us_id, fr_rt_id) values (5, 5);


insert into route_evaluations(re_rate, re_comment, re_us_id, re_rt_id)
values (5, 'Very good!', 2, 1);
insert into route_evaluations(re_rate, re_us_id, re_rt_id)
values (3, 3, 3);
insert into route_evaluations(re_rate, re_us_id, re_rt_id)
values (2, 4, 4);
insert into route_evaluations(re_rate, re_us_id, re_rt_id)
values (4, 5, 2);


insert into done_routes(dr_us_id, dr_rt_id) values (2, 1);
insert into done_routes(dr_us_id, dr_rt_id) values (3, 3);
insert into done_routes(dr_us_id, dr_rt_id) values (4, 4);
insert into done_routes(dr_us_id, dr_rt_id) values (5, 2);

insert into progress_routes(pr_us_id, pr_rt_id, pr_sp_id) values (2, 2, 16);


insert into route_spots (rs_rt_id, rs_sp_id) values (1, 12);
insert into route_spots (rs_rt_id, rs_sp_id) values (1, 13);
insert into route_spots (rs_rt_id, rs_sp_id) values (1, 14);
insert into route_spots (rs_rt_id, rs_sp_id) values (1, 15);

insert into route_spots (rs_rt_id, rs_sp_id) values (2, 8);
insert into route_spots (rs_rt_id, rs_sp_id) values (2, 16);
insert into route_spots (rs_rt_id, rs_sp_id) values (2, 6);

insert into route_spots (rs_rt_id, rs_sp_id) values (3, 1);
insert into route_spots (rs_rt_id, rs_sp_id) values (3, 2);
insert into route_spots (rs_rt_id, rs_sp_id) values (3, 5);
insert into route_spots (rs_rt_id, rs_sp_id) values (3, 11);
insert into route_spots (rs_rt_id, rs_sp_id) values (3, 16);

insert into route_spots (rs_rt_id, rs_sp_id) values (4, 5);
insert into route_spots (rs_rt_id, rs_sp_id) values (4, 2);


insert into route_spots (rs_rt_id, rs_sp_id) values (5, 1);
insert into route_spots (rs_rt_id, rs_sp_id) values (5, 2);
insert into route_spots (rs_rt_id, rs_sp_id) values (5, 4);
insert into route_spots (rs_rt_id, rs_sp_id) values (5, 9);
insert into route_spots (rs_rt_id, rs_sp_id) values (5, 11);
insert into route_spots (rs_rt_id, rs_sp_id) values (5, 12);

insert into achievements(ac_name) values ('1 Museu Visitado');
insert into achievements(ac_name) values ('5 Museus Visitados');
insert into achievements(ac_name) values ('10 Museus Visitados');
insert into achievements(ac_name) values ('1 Rota Concluída');
insert into achievements(ac_name) values ('5 Rotas Concluídas');
insert into achievements(ac_name) values ('10 Rotas Concluídas');
insert into achievements(ac_name) values ('1 Ponto Medieval Visitado');
insert into achievements(ac_name) values ('5 Pontos Medievais Visitados');
insert into achievements(ac_name) values ('10 Pontos Medievais Visitados');
insert into achievements(ac_name) values ('1 km Percorrido');
insert into achievements(ac_name) values ('5 km Percorridos');
insert into achievements(ac_name) values ('20 km Percorridos');
insert into achievements(ac_name) values ('5 Locais Visitados');
insert into achievements(ac_name) values ('15 Locais Visitados');
insert into achievements(ac_name) values ('30 Locais Visitados');

insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-11-19', 'YYYY-MM-DD'), 5, 1);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-08-23', 'YYYY-MM-DD'), 5, 2);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-05-01', 'YYYY-MM-DD'), 5, 10);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-07-08', 'YYYY-MM-DD'), 5, 11);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-10-12', 'YYYY-MM-DD'), 5, 13);

insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-06-03', 'YYYY-MM-DD'), 2, 1);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2020-12-29', 'YYYY-MM-DD'), 2, 2);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-04-17', 'YYYY-MM-DD'), 2, 3);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-11-19', 'YYYY-MM-DD'), 2, 13);

insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-05-30', 'YYYY-MM-DD'), 3, 4);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-11-11', 'YYYY-MM-DD'), 3, 5);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-12-05', 'YYYY-MM-DD'), 3, 10);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-08-13', 'YYYY-MM-DD'), 3, 11);
insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-11-19', 'YYYY-MM-DD'), 3, 13);

insert into user_achievements (ua_date, ua_us_id, ua_ac_id) values (to_date('2021-11-11', 'YYYY-MM-DD'), 4, 1);
