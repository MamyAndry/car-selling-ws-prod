--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-0+deb12u1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-0+deb12u1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: f_get_fund_user_per_month(); Type: FUNCTION; Schema: public; Owner: mamisoa
--

CREATE FUNCTION public.f_get_fund_user_per_month() RETURNS TABLE(sum double precision, year integer, month integer)
    LANGUAGE plpgsql
    AS $$
DECLARE
    id_users_loop VARCHAR(50);
    month_loop INT;
BEGIN
    FOR year IN SELECT DISTINCT EXTRACT(YEAR FROM date_add) FROM profit
    LOOP
        FOR month_loop IN 1..12
        LOOP
            sum := COALESCE((
                SELECT SUM(rising)
                FROM profit
                WHERE EXTRACT(YEAR FROM date_add) = year
                AND EXTRACT(MONTH FROM date_add) = month_loop
            ), 0);
            month := month_loop;

            IF sum IS NOT NULL THEN
                month := month_loop;
                RETURN NEXT;
            END IF;
        END LOOP;
    END LOOP;
END;
$$;


ALTER FUNCTION public.f_get_fund_user_per_month() OWNER TO mamisoa;

--
-- Name: f_get_profit_user_per_month(); Type: FUNCTION; Schema: public; Owner: mamisoa
--

CREATE FUNCTION public.f_get_profit_user_per_month() RETURNS TABLE(id_users_result character varying, sum double precision, year integer, month integer)
    LANGUAGE plpgsql
    AS $$
DECLARE
    id_users_loop VARCHAR(50);
    month_loop INT;
BEGIN
    FOR id_users_loop IN SELECT DISTINCT id_users FROM users
    LOOP
        FOR year IN SELECT DISTINCT EXTRACT(YEAR FROM date_add) FROM profit
        LOOP
            FOR month_loop IN 1..12
            LOOP
                sum := COALESCE((
                    SELECT SUM(rising)
                    FROM profit
                    WHERE EXTRACT(YEAR FROM date_add) = year
                    AND EXTRACT(MONTH FROM date_add) = month_loop
                    AND id_users = id_users_loop
                ), 0);
                month := month_loop;

                IF sum IS NOT NULL THEN
                    id_users_result := id_users_loop;
                    month := month_loop;
                    RETURN NEXT;
                END IF;
            END LOOP;
        END LOOP;
    END LOOP;
END;
$$;


ALTER FUNCTION public.f_get_profit_user_per_month() OWNER TO mamisoa;

--
-- Name: f_get_sales_count_by_month(); Type: FUNCTION; Schema: public; Owner: mamisoa
--

CREATE FUNCTION public.f_get_sales_count_by_month() RETURNS TABLE(brand_result character varying, model_result character varying, model_id character varying, year integer, month integer, vente_count integer)
    LANGUAGE plpgsql
    AS $$
DECLARE
    brand_loop VARCHAR(50);
    model_loop VARCHAR(50);
    id_loop VARCHAR(50);
    month_loop INT;
BEGIN
    FOR brand_loop, model_loop, id_loop IN SELECT DISTINCT brand, modele, id FROM v_sold_car
    LOOP
        FOR year IN SELECT DISTINCT EXTRACT(YEAR FROM date_sell) FROM v_sold_car WHERE brand = brand_loop AND modele = model_loop
        LOOP
            FOR month_loop IN 1..12
            LOOP
                vente_count := COALESCE((
                    SELECT COUNT(id_vente)
                    FROM v_sold_car
                    WHERE EXTRACT(YEAR FROM date_sell) = year
                    AND EXTRACT(MONTH FROM date_sell) = month_loop
                    AND brand = brand_loop
                    AND modele = model_loop
                    AND id = id_loop
                ), 0);
                month := month_loop;

                IF vente_count IS NOT NULL THEN
                    brand_result := brand_loop;
                    model_result := model_loop;
                    model_id := id_loop;
                    month := month_loop;
                    RETURN NEXT;
                END IF;
            END LOOP;
        END LOOP;
    END LOOP;
END;
$$;


ALTER FUNCTION public.f_get_sales_count_by_month() OWNER TO mamisoa;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: annonce; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.annonce (
    id_annonce character varying(50) NOT NULL,
    date_add timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    date_validation date,
    status integer,
    price numeric(15,2),
    description character varying(255),
    id_location character varying(50) NOT NULL,
    id_car character varying(50)
);


ALTER TABLE public.annonce OWNER TO mamisoa;

--
-- Name: brand; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.brand (
    id_brand character varying(50) NOT NULL,
    name character varying(50),
    id_origin character varying(10) NOT NULL
);


ALTER TABLE public.brand OWNER TO mamisoa;

--
-- Name: car; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.car (
    id_car character varying(50) NOT NULL,
    door_number integer,
    kilometrage numeric(15,2),
    id_model_motor integer,
    id_model_fuel_type integer,
    id_users character varying(50) NOT NULL,
    id_model character varying(50),
    color character varying(50),
    id_car_status integer,
    id_model_gear_box integer,
    id_transmission character varying(50)
);


ALTER TABLE public.car OWNER TO mamisoa;

--
-- Name: car_status; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.car_status (
    id_car_status integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.car_status OWNER TO mamisoa;

--
-- Name: category; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.category (
    id_category character varying(50) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.category OWNER TO mamisoa;

--
-- Name: commission; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.commission (
    id_commission character varying(50) NOT NULL,
    boundary_inferior double precision,
    boundary_superior double precision,
    percentage integer
);


ALTER TABLE public.commission OWNER TO mamisoa;

--
-- Name: favoris; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.favoris (
    id_favoris character varying(50) NOT NULL,
    id_users character varying(50) NOT NULL,
    id_annonce character varying(50)
);


ALTER TABLE public.favoris OWNER TO mamisoa;

--
-- Name: fuel_type; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.fuel_type (
    id_fuel_type character varying(50) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.fuel_type OWNER TO mamisoa;

--
-- Name: fund; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.fund (
    id_fund character varying(50) NOT NULL,
    rising double precision,
    date_add date,
    id_vente character varying(50) NOT NULL
);


ALTER TABLE public.fund OWNER TO mamisoa;

--
-- Name: gear_box; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.gear_box (
    id_gear_box character varying(50) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.gear_box OWNER TO mamisoa;

--
-- Name: gender; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.gender (
    id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE public.gender OWNER TO mamisoa;

--
-- Name: location; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.location (
    id_location character varying(50) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.location OWNER TO mamisoa;

--
-- Name: model; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.model (
    id_model character varying(50) NOT NULL,
    name character varying(50),
    id_brand character varying(50) NOT NULL,
    id_category character varying(50) NOT NULL
);


ALTER TABLE public.model OWNER TO mamisoa;

--
-- Name: model_fuel_type; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.model_fuel_type (
    id_model_fuel_type integer NOT NULL,
    id_model character varying(50) NOT NULL,
    id_fuel_type character varying(50)
);


ALTER TABLE public.model_fuel_type OWNER TO mamisoa;

--
-- Name: model_fuel_type_id_model_fuel_type_seq; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.model_fuel_type_id_model_fuel_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_fuel_type_id_model_fuel_type_seq OWNER TO mamisoa;

--
-- Name: model_fuel_type_id_model_fuel_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mamisoa
--

ALTER SEQUENCE public.model_fuel_type_id_model_fuel_type_seq OWNED BY public.model_fuel_type.id_model_fuel_type;


--
-- Name: model_gear_box; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.model_gear_box (
    id_model_gear_box integer NOT NULL,
    id_model character varying(50),
    id_gear_box character varying(50)
);


ALTER TABLE public.model_gear_box OWNER TO mamisoa;

--
-- Name: model_gear_box_id_model_gear_box_seq; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.model_gear_box_id_model_gear_box_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_gear_box_id_model_gear_box_seq OWNER TO mamisoa;

--
-- Name: model_gear_box_id_model_gear_box_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mamisoa
--

ALTER SEQUENCE public.model_gear_box_id_model_gear_box_seq OWNED BY public.model_gear_box.id_model_gear_box;


--
-- Name: model_motor; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.model_motor (
    id_model_motor integer NOT NULL,
    id_model character varying(50),
    id_motorisation character varying(50)
);


ALTER TABLE public.model_motor OWNER TO mamisoa;

--
-- Name: model_motor_id_model_motor_seq; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.model_motor_id_model_motor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_motor_id_model_motor_seq OWNER TO mamisoa;

--
-- Name: model_motor_id_model_motor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mamisoa
--

ALTER SEQUENCE public.model_motor_id_model_motor_seq OWNED BY public.model_motor.id_model_motor;


--
-- Name: month; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.month (
    id_month integer,
    month character varying(50)
);


ALTER TABLE public.month OWNER TO mamisoa;

--
-- Name: motorisation; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.motorisation (
    id_motorisation character varying(50) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.motorisation OWNER TO mamisoa;

--
-- Name: origin; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.origin (
    id_origin character varying(10) NOT NULL,
    name character varying(50)
);


ALTER TABLE public.origin OWNER TO mamisoa;

--
-- Name: photo; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.photo (
    id integer NOT NULL,
    picture text,
    id_car character varying(50) NOT NULL
);


ALTER TABLE public.photo OWNER TO mamisoa;

--
-- Name: photo_id_seq; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.photo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.photo_id_seq OWNER TO mamisoa;

--
-- Name: photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mamisoa
--

ALTER SEQUENCE public.photo_id_seq OWNED BY public.photo.id;


--
-- Name: profit; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.profit (
    id_profit character varying(50) NOT NULL,
    rising double precision,
    date_add date,
    id_users character varying(50) NOT NULL
);


ALTER TABLE public.profit OWNER TO mamisoa;

--
-- Name: seq_annonce; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_annonce
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_annonce OWNER TO mamisoa;

--
-- Name: seq_brand; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_brand
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_brand OWNER TO mamisoa;

--
-- Name: seq_car; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_car
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_car OWNER TO mamisoa;

--
-- Name: seq_category; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_category
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_category OWNER TO mamisoa;

--
-- Name: seq_commission; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_commission
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_commission OWNER TO mamisoa;

--
-- Name: seq_favoris; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_favoris
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_favoris OWNER TO mamisoa;

--
-- Name: seq_fueltype; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_fueltype
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_fueltype OWNER TO mamisoa;

--
-- Name: seq_fund; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_fund
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_fund OWNER TO mamisoa;

--
-- Name: seq_gear; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_gear
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_gear OWNER TO mamisoa;

--
-- Name: seq_location; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_location
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_location OWNER TO mamisoa;

--
-- Name: seq_model; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_model
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_model OWNER TO mamisoa;

--
-- Name: seq_model_fuel; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_model_fuel
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_model_fuel OWNER TO mamisoa;

--
-- Name: seq_model_gear; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_model_gear
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_model_gear OWNER TO mamisoa;

--
-- Name: seq_model_motor; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_model_motor
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_model_motor OWNER TO mamisoa;

--
-- Name: seq_motorisation; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_motorisation
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_motorisation OWNER TO mamisoa;

--
-- Name: seq_origin; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_origin
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_origin OWNER TO mamisoa;

--
-- Name: seq_photo; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_photo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_photo OWNER TO mamisoa;

--
-- Name: seq_profit; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_profit
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_profit OWNER TO mamisoa;

--
-- Name: seq_users; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_users
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_users OWNER TO mamisoa;

--
-- Name: seq_vente; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_vente
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_vente OWNER TO mamisoa;

--
-- Name: transmission; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.transmission (
    id_transmission character varying(50) NOT NULL,
    name character varying(50),
    id_car character varying(50)
);


ALTER TABLE public.transmission OWNER TO mamisoa;

--
-- Name: users; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.users (
    id_users character varying(50) NOT NULL,
    name character varying(50),
    first_name character varying(50),
    birthdate date,
    username character varying(50),
    email character varying(50),
    password character varying(50),
    is_admin boolean,
    date_registration date,
    id_gender integer
);


ALTER TABLE public.users OWNER TO mamisoa;

--
-- Name: v_fund_user_per_month; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_fund_user_per_month AS
 SELECT f.sum,
    f.year,
    m.month
   FROM (public.f_get_fund_user_per_month() f(sum, year, month)
     JOIN public.month m ON ((f.month = m.id_month)));


ALTER TABLE public.v_fund_user_per_month OWNER TO mamisoa;

--
-- Name: v_fund_variation_per_year; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_fund_variation_per_year AS
 SELECT sum(f_get_fund_user_per_month.sum) AS sum,
    f_get_fund_user_per_month.year
   FROM public.f_get_fund_user_per_month() f_get_fund_user_per_month(sum, year, month)
  GROUP BY f_get_fund_user_per_month.year;


ALTER TABLE public.v_fund_variation_per_year OWNER TO mamisoa;

--
-- Name: v_most_sold_car_per_year; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_most_sold_car_per_year AS
 SELECT f_get_sales_count_by_month.brand_result,
    f_get_sales_count_by_month.model_result,
    f_get_sales_count_by_month.year,
    COALESCE(sum(f_get_sales_count_by_month.vente_count), (0)::bigint) AS count
   FROM public.f_get_sales_count_by_month() f_get_sales_count_by_month(brand_result, model_result, model_id, year, month, vente_count)
  GROUP BY f_get_sales_count_by_month.brand_result, f_get_sales_count_by_month.model_result, f_get_sales_count_by_month.year;


ALTER TABLE public.v_most_sold_car_per_year OWNER TO mamisoa;

--
-- Name: v_most_sold_brand_per_year; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_most_sold_brand_per_year AS
 SELECT v_most_sold_car_per_year.brand_result,
    v_most_sold_car_per_year.year,
    sum(v_most_sold_car_per_year.count) AS sum
   FROM public.v_most_sold_car_per_year
  GROUP BY v_most_sold_car_per_year.brand_result, v_most_sold_car_per_year.year;


ALTER TABLE public.v_most_sold_brand_per_year OWNER TO mamisoa;

--
-- Name: v_profit_user_per_month; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_profit_user_per_month AS
 SELECT f.id_users_result,
    f.sum,
    f.year,
    m.month
   FROM (public.f_get_profit_user_per_month() f(id_users_result, sum, year, month)
     JOIN public.month m ON ((f.month = m.id_month)));


ALTER TABLE public.v_profit_user_per_month OWNER TO mamisoa;

--
-- Name: v_profit_variation_per_year; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_profit_variation_per_year AS
 SELECT f_get_profit_user_per_month.id_users_result AS id,
    sum(f_get_profit_user_per_month.sum) AS sum,
    f_get_profit_user_per_month.year
   FROM public.f_get_profit_user_per_month() f_get_profit_user_per_month(id_users_result, sum, year, month)
  GROUP BY f_get_profit_user_per_month.id_users_result, f_get_profit_user_per_month.year;


ALTER TABLE public.v_profit_variation_per_year OWNER TO mamisoa;

--
-- Name: v_sales_count_by_month; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_sales_count_by_month AS
 SELECT f.brand_result,
    f.model_result,
    f.model_id,
    f.year,
    m.month,
    f.vente_count
   FROM (public.f_get_sales_count_by_month() f(brand_result, model_result, model_id, year, month, vente_count)
     JOIN public.month m ON ((f.month = m.id_month)));


ALTER TABLE public.v_sales_count_by_month OWNER TO mamisoa;

--
-- Name: vente; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.vente (
    id_vente character varying(50) NOT NULL,
    date_sell timestamp without time zone,
    price_payed numeric(15,2),
    status integer,
    date_validation date,
    id_annonce character varying(50),
    id_seller character varying(50)
);


ALTER TABLE public.vente OWNER TO mamisoa;

--
-- Name: model_fuel_type id_model_fuel_type; Type: DEFAULT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_fuel_type ALTER COLUMN id_model_fuel_type SET DEFAULT nextval('public.model_fuel_type_id_model_fuel_type_seq'::regclass);


--
-- Name: model_gear_box id_model_gear_box; Type: DEFAULT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_gear_box ALTER COLUMN id_model_gear_box SET DEFAULT nextval('public.model_gear_box_id_model_gear_box_seq'::regclass);


--
-- Name: model_motor id_model_motor; Type: DEFAULT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_motor ALTER COLUMN id_model_motor SET DEFAULT nextval('public.model_motor_id_model_motor_seq'::regclass);


--
-- Name: photo id; Type: DEFAULT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.photo ALTER COLUMN id SET DEFAULT nextval('public.photo_id_seq'::regclass);


--
-- Data for Name: annonce; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.annonce (id_annonce, date_add, date_validation, status, price, description, id_location, id_car) FROM stdin;
ANN0001	2024-01-27 11:46:31.807441	\N	1	15000.00	Well-maintained Honda Accord for sale	LOC0001	CAR0001
ANN0002	2024-01-27 11:46:31.807441	\N	1	25000.00	Ford F-150 in excellent condition	LOC0003	CAR0002
ANN0003	2024-01-27 11:46:31.807441	\N	1	18000.50	Sporty red Honda Civic Type R	LOC0001	CAR0003
ANN0004	2024-01-27 11:46:31.807441	\N	1	20000.75	Powerful Civic Type R with low mileage	LOC0002	CAR0004
ANN0005	2024-01-27 11:46:31.807441	\N	1	22000.00	White Ford Escape, great fuel efficiency	LOC0002	CAR0005
\.


--
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.brand (id_brand, name, id_origin) FROM stdin;
BRD0001	Toyota	ORG0004
BRD0002	Ford	ORG0005
BRD0003	Honda	ORG0004
\.


--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.car (id_car, door_number, kilometrage, id_model_motor, id_model_fuel_type, id_users, id_model, color, id_car_status, id_model_gear_box, id_transmission) FROM stdin;
CAR0001	4	50000.00	1	1	USR0001	MDL0001	Blue	1	1	TRA0001
CAR0002	2	70000.50	2	2	USR0002	MDL0002	Silver	0	2	TRA0002
CAR0003	4	30000.75	3	3	USR0001	MDL0003	Red	1	3	TRA0003
CAR0004	2	60000.25	1	1	USR0002	MDL0004	Black	0	4	TRA0001
CAR0005	4	40000.00	2	2	USR0001	MDL0005	White	1	5	TRA0002
\.


--
-- Data for Name: car_status; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.car_status (id_car_status, name) FROM stdin;
0	In working condition
1	Non-operational
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.category (id_category, name) FROM stdin;
CAT0001	Sedan
CAT0002	SUV
CAT0003	Hatchback
\.


--
-- Data for Name: commission; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.commission (id_commission, boundary_inferior, boundary_superior, percentage) FROM stdin;
COM0001	0	15000	15
COM0002	15000	30000	20
COM0003	30000	50000	30
\.


--
-- Data for Name: favoris; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.favoris (id_favoris, id_users, id_annonce) FROM stdin;
FAV0001	USR0001	ANN0001
FAV0002	USR0002	ANN0002
FAV0003	USR0001	ANN0003
FAV0004	USR0002	ANN0004
FAV0005	USR0001	ANN0005
\.


--
-- Data for Name: fuel_type; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.fuel_type (id_fuel_type, name) FROM stdin;
FUT0001	Petrol
FUT0002	Diesel
FUT0003	Electric
\.


--
-- Data for Name: fund; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.fund (id_fund, rising, date_add, id_vente) FROM stdin;
\.


--
-- Data for Name: gear_box; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.gear_box (id_gear_box, name) FROM stdin;
GRB0001	Automatic
GRB0002	Manual
GRB0003	CVT
GRB0004	DCT
GRB0005	Tiptronic
\.


--
-- Data for Name: gender; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.gender (id, name) FROM stdin;
0	Femme
1	Homme
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.location (id_location, name) FROM stdin;
LOC0001	Antananarivo
LOC0002	Toamasina
LOC0003	Antsirabe
\.


--
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.model (id_model, name, id_brand, id_category) FROM stdin;
MDL0001	Accord	BRD0003	CAT0001
MDL0002	F-150	BRD0002	CAT0002
MDL0003	Corolla	BRD0001	CAT0001
MDL0004	Civic Type R	BRD0003	CAT0003
MDL0005	Escape	BRD0002	CAT0002
\.


--
-- Data for Name: model_fuel_type; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.model_fuel_type (id_model_fuel_type, id_model, id_fuel_type) FROM stdin;
1	MDL0001	FUT0001
2	MDL0002	FUT0002
3	MDL0003	FUT0003
4	MDL0004	FUT0001
5	MDL0005	FUT0002
\.


--
-- Data for Name: model_gear_box; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.model_gear_box (id_model_gear_box, id_model, id_gear_box) FROM stdin;
1	MDL0001	GRB0001
2	MDL0002	GRB0002
3	MDL0003	GRB0003
4	MDL0004	GRB0004
5	MDL0005	GRB0005
\.


--
-- Data for Name: model_motor; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.model_motor (id_model_motor, id_model, id_motorisation) FROM stdin;
1	MDL0001	MOT0001
2	MDL0002	MOT0002
3	MDL0003	MOT0003
4	MDL0004	MOT0001
5	MDL0005	MOT0002
\.


--
-- Data for Name: month; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.month (id_month, month) FROM stdin;
1	January
2	February
3	March
4	April
5	May
6	June
7	July
8	August
9	September
10	October
11	November
12	December
\.


--
-- Data for Name: motorisation; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.motorisation (id_motorisation, name) FROM stdin;
MOT0001	Automatic
MOT0002	Manual
MOT0003	Hybrid
\.


--
-- Data for Name: origin; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.origin (id_origin, name) FROM stdin;
ORG0001	German
ORG0002	French
ORG0003	Spanish
ORG0004	Japanese
ORG0005	American
ORG0006	Italian
\.


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.photo (id, picture, id_car) FROM stdin;
\.


--
-- Data for Name: profit; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.profit (id_profit, rising, date_add, id_users) FROM stdin;
\.


--
-- Data for Name: transmission; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.transmission (id_transmission, name, id_car) FROM stdin;
TRA0001	Automatic	\N
TRA0002	Manual	\N
TRA0003	Semi-automatic	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.users (id_users, name, first_name, birthdate, username, email, password, is_admin, date_registration, id_gender) FROM stdin;
USR0001	Diana	Rakotomaharo	2004-12-16	mhr_di	dianarakoto9@gmail.com	1234567huit	t	\N	0
USR0002	Mamisoa	Ratsimbazafy	2002-04-24	mmms	rmams@gmail.com	mamisoa	f	\N	1
\.


--
-- Data for Name: vente; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.vente (id_vente, date_sell, price_payed, status, date_validation, id_annonce, id_seller) FROM stdin;
VEN0001	2024-01-27 11:46:31.818532	14500.00	\N	\N	ANN0001	USR0002
VEN0002	2024-01-27 11:46:31.818532	24000.00	\N	\N	ANN0002	USR0001
VEN0003	2024-01-27 11:46:31.818532	17500.50	\N	\N	ANN0003	USR0002
VEN0004	2024-01-27 11:46:31.818532	19500.75	\N	\N	ANN0004	USR0001
VEN0005	2024-01-27 11:46:31.818532	21500.00	\N	\N	ANN0005	USR0002
\.


--
-- Name: model_fuel_type_id_model_fuel_type_seq; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.model_fuel_type_id_model_fuel_type_seq', 5, true);


--
-- Name: model_gear_box_id_model_gear_box_seq; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.model_gear_box_id_model_gear_box_seq', 5, true);


--
-- Name: model_motor_id_model_motor_seq; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.model_motor_id_model_motor_seq', 5, true);


--
-- Name: photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.photo_id_seq', 1, false);


--
-- Name: seq_annonce; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_annonce', 1, false);


--
-- Name: seq_brand; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_brand', 4, true);


--
-- Name: seq_car; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_car', 1, false);


--
-- Name: seq_category; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_category', 1, false);


--
-- Name: seq_commission; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_commission', 1, false);


--
-- Name: seq_favoris; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_favoris', 1, false);


--
-- Name: seq_fueltype; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_fueltype', 1, false);


--
-- Name: seq_fund; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_fund', 1, false);


--
-- Name: seq_gear; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_gear', 6, true);


--
-- Name: seq_location; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_location', 1, false);


--
-- Name: seq_model; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_model', 1, false);


--
-- Name: seq_model_fuel; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_model_fuel', 1, false);


--
-- Name: seq_model_gear; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_model_gear', 1, false);


--
-- Name: seq_model_motor; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_model_motor', 1, false);


--
-- Name: seq_motorisation; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_motorisation', 4, true);


--
-- Name: seq_origin; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_origin', 1, false);


--
-- Name: seq_photo; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_photo', 1, false);


--
-- Name: seq_profit; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_profit', 1, false);


--
-- Name: seq_users; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_users', 1, false);


--
-- Name: seq_vente; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_vente', 1, false);


--
-- Name: annonce annonce_id_car_key; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_id_car_key UNIQUE (id_car);


--
-- Name: annonce annonce_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_pkey PRIMARY KEY (id_annonce);


--
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id_brand);


--
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id_car);


--
-- Name: car_status car_status_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car_status
    ADD CONSTRAINT car_status_pkey PRIMARY KEY (id_car_status);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id_category);


--
-- Name: commission commission_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.commission
    ADD CONSTRAINT commission_pkey PRIMARY KEY (id_commission);


--
-- Name: favoris favoris_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.favoris
    ADD CONSTRAINT favoris_pkey PRIMARY KEY (id_favoris);


--
-- Name: fuel_type fuel_type_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.fuel_type
    ADD CONSTRAINT fuel_type_pkey PRIMARY KEY (id_fuel_type);


--
-- Name: fund fund_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.fund
    ADD CONSTRAINT fund_pkey PRIMARY KEY (id_fund);


--
-- Name: gear_box gear_box_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.gear_box
    ADD CONSTRAINT gear_box_pkey PRIMARY KEY (id_gear_box);


--
-- Name: gender gender_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.gender
    ADD CONSTRAINT gender_pkey PRIMARY KEY (id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id_location);


--
-- Name: model_fuel_type model_fuel_type_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_fuel_type
    ADD CONSTRAINT model_fuel_type_pkey PRIMARY KEY (id_model_fuel_type);


--
-- Name: model_gear_box model_gear_box_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_gear_box
    ADD CONSTRAINT model_gear_box_pkey PRIMARY KEY (id_model_gear_box);


--
-- Name: model_motor model_motor_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_motor
    ADD CONSTRAINT model_motor_pkey PRIMARY KEY (id_model_motor);


--
-- Name: model model_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pkey PRIMARY KEY (id_model);


--
-- Name: motorisation motorisation_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.motorisation
    ADD CONSTRAINT motorisation_pkey PRIMARY KEY (id_motorisation);


--
-- Name: origin origin_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.origin
    ADD CONSTRAINT origin_pkey PRIMARY KEY (id_origin);


--
-- Name: photo photo_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (id);


--
-- Name: profit profit_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.profit
    ADD CONSTRAINT profit_pkey PRIMARY KEY (id_profit);


--
-- Name: transmission transmission_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.transmission
    ADD CONSTRAINT transmission_pkey PRIMARY KEY (id_transmission);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_users);


--
-- Name: vente vente_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT vente_pkey PRIMARY KEY (id_vente);


--
-- Name: annonce annonce_id_car_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_id_car_fkey FOREIGN KEY (id_car) REFERENCES public.car(id_car);


--
-- Name: annonce annonce_id_location_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_id_location_fkey FOREIGN KEY (id_location) REFERENCES public.location(id_location);


--
-- Name: brand brand_id_origin_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_id_origin_fkey FOREIGN KEY (id_origin) REFERENCES public.origin(id_origin);


--
-- Name: car car_id_car_status_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_car_status_fkey FOREIGN KEY (id_car_status) REFERENCES public.car_status(id_car_status);


--
-- Name: car car_id_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_model_fkey FOREIGN KEY (id_model) REFERENCES public.model(id_model);


--
-- Name: car car_id_model_fuel_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_model_fuel_type_fkey FOREIGN KEY (id_model_fuel_type) REFERENCES public.model_fuel_type(id_model_fuel_type);


--
-- Name: car car_id_model_gear_box_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_model_gear_box_fkey FOREIGN KEY (id_model_gear_box) REFERENCES public.model_gear_box(id_model_gear_box);


--
-- Name: car car_id_model_motor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_model_motor_fkey FOREIGN KEY (id_model_motor) REFERENCES public.model_motor(id_model_motor);


--
-- Name: car car_id_transmission_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_transmission_fkey FOREIGN KEY (id_transmission) REFERENCES public.transmission(id_transmission);


--
-- Name: car car_id_users_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_users_fkey FOREIGN KEY (id_users) REFERENCES public.users(id_users);


--
-- Name: favoris favoris_id_annonce_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.favoris
    ADD CONSTRAINT favoris_id_annonce_fkey FOREIGN KEY (id_annonce) REFERENCES public.annonce(id_annonce);


--
-- Name: favoris favoris_id_users_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.favoris
    ADD CONSTRAINT favoris_id_users_fkey FOREIGN KEY (id_users) REFERENCES public.users(id_users);


--
-- Name: fund fund_id_vente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.fund
    ADD CONSTRAINT fund_id_vente_fkey FOREIGN KEY (id_vente) REFERENCES public.vente(id_vente);


--
-- Name: model_fuel_type model_fuel_type_id_fuel_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_fuel_type
    ADD CONSTRAINT model_fuel_type_id_fuel_type_fkey FOREIGN KEY (id_fuel_type) REFERENCES public.fuel_type(id_fuel_type);


--
-- Name: model_fuel_type model_fuel_type_id_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_fuel_type
    ADD CONSTRAINT model_fuel_type_id_model_fkey FOREIGN KEY (id_model) REFERENCES public.model(id_model);


--
-- Name: model_gear_box model_gear_box_id_gear_box_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_gear_box
    ADD CONSTRAINT model_gear_box_id_gear_box_fkey FOREIGN KEY (id_gear_box) REFERENCES public.gear_box(id_gear_box);


--
-- Name: model_gear_box model_gear_box_id_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_gear_box
    ADD CONSTRAINT model_gear_box_id_model_fkey FOREIGN KEY (id_model) REFERENCES public.model(id_model);


--
-- Name: model model_id_brand_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_id_brand_fkey FOREIGN KEY (id_brand) REFERENCES public.brand(id_brand);


--
-- Name: model model_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);


--
-- Name: model_motor model_motor_id_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_motor
    ADD CONSTRAINT model_motor_id_model_fkey FOREIGN KEY (id_model) REFERENCES public.model(id_model);


--
-- Name: model_motor model_motor_id_motorisation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.model_motor
    ADD CONSTRAINT model_motor_id_motorisation_fkey FOREIGN KEY (id_motorisation) REFERENCES public.motorisation(id_motorisation);


--
-- Name: photo photo_id_car_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_id_car_fkey FOREIGN KEY (id_car) REFERENCES public.car(id_car);


--
-- Name: profit profit_id_users_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.profit
    ADD CONSTRAINT profit_id_users_fkey FOREIGN KEY (id_users) REFERENCES public.users(id_users);


--
-- Name: users users_id_gender_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_gender_fkey FOREIGN KEY (id_gender) REFERENCES public.gender(id);


--
-- Name: vente vente_id_annonce_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT vente_id_annonce_fkey FOREIGN KEY (id_annonce) REFERENCES public.annonce(id_annonce);


--
-- Name: vente vente_id_users_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT vente_id_users_fkey FOREIGN KEY (id_seller) REFERENCES public.users(id_users);


--
-- PostgreSQL database dump complete
--

