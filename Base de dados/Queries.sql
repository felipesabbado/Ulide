-------------------------------------------------- Users ---------------------------------------------------------------

-- ver as rotas favoritas de um user
select rt_name from routes, fav_routes, users
where rt_id = fr_rt_id and us_id = fr_us_id and us_id = 2;

-- ver os spots favoritos de um user
select sp_name from spots, fav_spots, users
where sp_id = fs_sp_id and us_id = fs_us_id and us_id = 2;

-- ver as rotas feitas de um user
select rt_name from routes, done_routes, users
where rt_id = dr_rt_id and us_id = dr_us_id and us_id = 2;

-- ver os pontos feitos de um user
select sp_name from spots, done_spots, users
where sp_id = ds_sp_id and us_id = ds_us_id and us_id = 2;

-- ver quais tags um user deu a um lugar
select sp_name, tg_name from users_spots_tags, spots, tags, users
where ust_sp_id = sp_id and ust_tg_id = tg_id and ust_us_id = us_id and us_id = 3;

-- ver as rotas q faltao acabar e quais os pontos que faltam
select rt_name, sp_name from spots, routes, progress_routes
where pr_rt_id = rt_id and pr_sp_id = sp_id and pr_us_id = 2;

-- ver os rotas e os pontos que faltam fazer nessas rotas e possivel dar erro se o utilizdor deixou mais de uma rota por fazer
select rt_name, sp_name
from (select rs_id next_spot from progress_routes, route_spots
        where pr_rt_id = rs_rt_id and pr_sp_id = rs_sp_id and pr_us_id = 2) next_start_spot,
     spots, routes, route_spots, progress_routes
where sp_id = rs_sp_id
  and rt_id = rs_rt_id
  and pr_rt_id = rt_id
  and rs_id >= next_spot
order by rs_id;


-- Nas fav mostrar as que nao foram feitas


-------------------------------------------------- End Users -----------------------------------------------------------

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
order by count(fr_rt_id) desc;

-- Mostrar todos os spots de cada rota
select rt_name, sp_name from routes, route_spots, spots
where rs_sp_id = sp_id and rs_rt_id = rt_id;

-- Mostra o nome das rotas que contenham lugares cujo o nome comece por 'P'
select rt_name, sp_name from routes, route_spots, spots
where rs_sp_id = sp_id and rs_rt_id = rt_id and sp_name like 'P%';

-- Mostra só o nome das rotas cujo os lugares comecem pela letra 'P'
select rt_name from routes, route_spots, spots
where rs_sp_id = sp_id and rs_rt_id = rt_id and sp_name like 'P%'
group by rt_name;

select sp_name from spots
where sp_price = false;

select rt_name, sp_name, sp_price from routes, route_spots, spots
where rs_sp_id = sp_id
  and rs_rt_id = rt_id
  and sp_price = false;


-- Questao:
-- O objetivos e obter as rotas toltalmente gratis
-- Resultados ate agora:
-- 1,2,3 select - Devolve o nome da rota escolhida(neste caso a rt_id = 3).
--              Isto acontece pois eu faço um count de quantos spots sao gratis(a rota 3 tem 3 pontos gratis)
--              e como a soma(soma de todos os spots gratis) = 3 quanto eu faço o where mostra me o nome da rota. Porem eu quero igualar a soma
--              a quantidade total de pontos que uma rota tem, pois se a soma(soma de todos os pontos gratis) for egual a soma de todos os pontos
--              numa rota a rota e totalmente gratis.
-- 4 select - O problema foi resolvido agora a questao e que quero ver totdas as rotas nao quero ser eu a por o id
--
--
select rt_name
from (select rt_name, count(*) soma from routes, route_spots, spots
      where rs_sp_id = sp_id and rs_rt_id = rt_id and sp_price = false and rt_id = 3
      group by rt_name) test where soma = 3;

select rt_name, count(*)from routes, route_spots, spots
where rt_id = rs_rt_id and sp_id = rs_sp_id and rt_id =3
group by rt_name;

select rt_name, count(*) soma from routes, route_spots, spots
where rs_sp_id = sp_id and rs_rt_id = rt_id and sp_price = false and rt_id = 3
group by rt_name;

-- v1
select rt_name
from (select rt_name, count(*) gratis from routes, route_spots, spots
      where rs_sp_id = sp_id and rs_rt_id = rt_id and sp_price = false and rt_id = 5
        group by rt_name ) total_spots_grats,
     (select rt_id, count(*) total from routes, route_spots, spots
       where rt_id = rs_rt_id and sp_id = rs_sp_id and rt_id =5
        group by rt_id) total_de_spots
where total = gratis;

-- v2
select rt_name
from (select rt_name, count(rt_name) total, count(sp_price = false) gratis
      from routes, route_spots, spots
      where sp_id = rs_sp_id and rt_id = rs_rt_id and rt_id = 5
      group by rt_name) test
where total = gratis;



-------------------------------------------------- End Routes ----------------------------------------------------------

------------------------------------------------- Achievements ---------------------------------------------------------
-- ver quantos achievements tem um user
select count(*) from user_achievements
where ua_us_id = 2;

-- ver as medalhas do user 2, pela data em ordem decrescente
select ua.ua_date, ac.ac_name from user_achievements ua, achievements ac
where ua.ua_ac_id = ac.ac_id and ua.ua_us_id = 2
order by ua_date desc;

-- ver as medalhas do user 2, pela data em ordem crescente
select ua_date, ac_name from user_achievements , achievements
where ua_ac_id = ac_id and ua_us_id = 2
order by ua_date;

-- ver as medalhas de um user
select ac.ac_name from user_achievements ua, achievements ac
where ua.ua_ac_id = ac.ac_id and ua.ua_us_id = 2;

--------------------------------------------- End Achievements ---------------------------------------------------------