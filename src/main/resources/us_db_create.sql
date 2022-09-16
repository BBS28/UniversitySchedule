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


CREATE SCHEMA university_schedule;

ALTER SCHEMA university_schedule OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE university_schedule.classrooms (
    id bigint NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE university_schedule.classrooms OWNER TO postgres;


ALTER TABLE university_schedule.classrooms ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME university_schedule.classrooms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE university_schedule.groups (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE university_schedule.groups OWNER TO postgres;

ALTER TABLE university_schedule.groups ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME university_schedule.groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE university_schedule.lectures (
    id bigint NOT NULL,
    classroom_id bigint,
    schedule_id bigint,
    name character varying(255) NOT NULL
);


ALTER TABLE university_schedule.lectures OWNER TO postgres;


ALTER TABLE university_schedule.lectures ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME university_schedule.lections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE university_schedule.schedule (
    id bigint NOT NULL,
    group_id bigint,
    week_day character varying(20)
);


ALTER TABLE university_schedule.schedule OWNER TO postgres;


ALTER TABLE university_schedule.schedule ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME university_schedule.schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE university_schedule.users (
    id bigint NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    group_id bigint
);


ALTER TABLE university_schedule.users OWNER TO postgres;


ALTER TABLE university_schedule.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME university_schedule.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


ALTER TABLE ONLY university_schedule.classrooms
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (id);


ALTER TABLE ONLY university_schedule.groups
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


ALTER TABLE ONLY university_schedule.lectures
    ADD CONSTRAINT lection_pkey PRIMARY KEY (id);


ALTER TABLE ONLY university_schedule.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


ALTER TABLE ONLY university_schedule.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


ALTER TABLE ONLY university_schedule.lectures
    ADD CONSTRAINT fk_lecture_classroom FOREIGN KEY (classroom_id) REFERENCES university_schedule.classrooms(id) NOT VALID;


ALTER TABLE ONLY university_schedule.lectures
    ADD CONSTRAINT fk_lecture_schedule FOREIGN KEY (schedule_id) REFERENCES university_schedule.schedule(id) NOT VALID;


ALTER TABLE ONLY university_schedule.schedule
    ADD CONSTRAINT fk_schedule_group FOREIGN KEY (group_id) REFERENCES university_schedule.groups(id) NOT VALID;


ALTER TABLE ONLY university_schedule.users
    ADD CONSTRAINT fk_user_group FOREIGN KEY (group_id) REFERENCES university_schedule.groups(id) NOT VALID;



