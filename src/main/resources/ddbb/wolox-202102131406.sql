--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2

-- Started on 2021-02-13 14:06:31

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
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: sidi_own
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO sidi_own;

--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: sidi_own
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 17891)
-- Name: shared_album; Type: TABLE; Schema: public; Owner: sidi_own
--

CREATE TABLE public.shared_album (
    album_id character varying(5) NOT NULL,
    album_owner character varying NOT NULL,
    read boolean,
    write boolean,
    user_id character varying NOT NULL
);


ALTER TABLE public.shared_album OWNER TO sidi_own;

--
-- TOC entry 2904 (class 0 OID 17891)
-- Dependencies: 202
-- Data for Name: shared_album; Type: TABLE DATA; Schema: public; Owner: sidi_own
--

COPY public.shared_album (album_id, album_owner, read, write, user_id) FROM stdin;
\.


--
-- TOC entry 2777 (class 2606 OID 17929)
-- Name: shared_album shared_album_pk; Type: CONSTRAINT; Schema: public; Owner: sidi_own
--

ALTER TABLE ONLY public.shared_album
    ADD CONSTRAINT shared_album_pk PRIMARY KEY (album_id, album_owner, user_id);


-- Completed on 2021-02-13 14:06:31

--
-- PostgreSQL database dump complete
--

