insert into users(us_name, us_bdate, us_gender)
values ('Caetano Feliciano', to_date('1997-08-30', 'YYYY-MM-DD'), 'M');
insert into users(us_name, us_bdate, us_gender)
values ('Teófilo Vale', to_date('2003-05-01', 'YYYY-MM-DD'), 'M');
insert into users(us_name, us_bdate, us_gender)
values ('Jordana Santiago', to_date('1976-11-27', 'YYYY-MM-DD'), 'F');
insert into users(us_name, us_bdate, us_gender)
values ('Teobaldo Mata', to_date('1946-10-25', 'YYYY-MM-DD'), 'M');
insert into users(us_name, us_bdate, us_gender)
values ('Salomé Pereyra', to_date('1952-07-05', 'YYYY-MM-DD'), 'F');
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

insert into spots (sp_name, sp_lat, sp_long) values('Torre de Belém', 38.69175116131192, -9.215966573567318);
insert into spots (sp_name, sp_lat, sp_long) values('Mosteiro dos Jerónimos', 38.69806671988026, -9.206671715894556);
insert into spots (sp_name, sp_lat, sp_long) values('Castelo de São Jorge', 38.71405986610344, -9.133454744730386);
insert into spots (sp_name, sp_lat, sp_long) values('Padrão dos Descobrimentos', 38.6936015,-9.2079002);
insert into spots (sp_name, sp_lat, sp_long) values('Museu da Cidade', 38.7585930361228, -9.156325382521535);
insert into spots (sp_name, sp_lat, sp_long) values('Elevador da Glória', 38.716897389128455, -9.142660122889284);
insert into spots (sp_name, sp_lat, sp_long) values('Estádio da Luz', 38.752886685013436, -9.184763273565512);
insert into spots (sp_name, sp_lat, sp_long) values('Oceanário', 38.76371079269964, -9.093720044728894);
insert into spots (sp_name, sp_lat, sp_long) values('Praça do Comércio', 38.70759494492502, -9.13644380826236);
insert into spots (sp_name, sp_lat, sp_long) values('Jardim Zoológico', 38.744495366088586, -9.170685015893174);
insert into spots (sp_name, sp_lat, sp_long) values('Panteão Nacional', 38.715061350403744, -9.12490880796462);
insert into spots (sp_name, sp_lat, sp_long) values('Mercado da Ribeira', 38.70690732582062, -9.145907544730553);
insert into spots (sp_name, sp_lat, sp_long) values('Mercado do Príncipe Real', 38.716159660346214, -9.149212773566605);
insert into spots (sp_name, sp_lat, sp_long) values('Mercado de Campo de Ourique', 38.707173108334246, -9.143361173566847);
insert into spots (sp_name, sp_lat, sp_long) values('Lx Factory', 38.70366533341345, -9.178862273566953);
insert into spots (sp_name, sp_lat, sp_long) values('Miradouro de Santa Catarina', 38.709725975239834, -9.147623365894209);

insert into routes (rt_name) values ('Compras');
insert into routes (rt_name) values ('Natureza');
insert into routes (rt_name) values ('Histórica');
insert into routes (rt_name) values ('Popular');

insert into tags (tg_name, tg_tt_id) values ('Mercados', 1);
insert into tags (tg_name, tg_tt_id) values ('Monumentos', 1);
insert into tags (tg_name, tg_tt_id) values ('Natureza', 1);
insert into tags (tg_name, tg_tt_id) values ('Museu', 1);
insert into tags (tg_name, tg_tt_id) values ('Desporto', 1);
insert into tags (tg_name, tg_tt_id) values ('Festa', 2);
insert into tags (tg_name, tg_tt_id) values ('Cool', 2);
insert into tags (tg_name, tg_tt_id) values ('Sexta-feira', 2);

insert into routes_spots () values ();