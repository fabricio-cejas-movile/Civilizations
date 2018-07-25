CREATE TABLE public.civ_day_weather (
    id bigserial primary key,
    day integer NOT NULL,
    weather varchar(20) NOT NULL,
	perimeter double precision
);