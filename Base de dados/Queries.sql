-------------------------------------------------- Routes --------------------------------------------------------------

-- ver quantos spots tem uma rota
select count(*) from route_spots
where rs_rt_id = 1;


-- ver os pontos de uma rota
select sp_name  from route_spots, spots
where rs_sp_id = sp_id and rs_rt_id = 1;

--ver rotas ordenadas pelas mais populares (as que nao estao em favoritos nao aparecem)
select rt_name from routes, fav_routes
where fr_rt_id = rt_id
group by rt_name
order by count(fr_rt_id) desc ;

-------------------------------------------------- End Routes ----------------------------------------------------------

------------------------------------------------- Achievements ---------------------------------------------------------
-- ver quantos achievements tem um user
select count(*) from user_achievements
where ua_us_id = 2;

-- ver as medalhas do user 2, pela data em ordem decrescente
select ua.ua_date, ac.ac_name from user_achievements ua, achievements ac
where ua.ua_ac_id = ac.ac_id and ua.ua_us_id = 2
order by ua_date desc;

-- ver as medalhas de um user
select ac.ac_name from user_achievements ua, achievements ac
where ua.ua_ac_id = ac.ac_id and ua.ua_us_id = 2;

--------------------------------------------- End Achievements ---------------------------------------------------------