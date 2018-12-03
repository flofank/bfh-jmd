--drop table public.employee_projects;
--drop table public.phone;
--drop table public.employee;
--drop table public.address;
--drop table public.department;
--drop table public.project;

--drop sequence address_seq;
--drop sequence department_seq;
--drop sequence employee_seq;
--drop sequence phone_seq;
--drop sequence project_seq;

CREATE TABLE public.address (
    id integer NOT NULL,
    city character varying(255),
    state character varying(255),
    street character varying(255),
    zip character varying(255)
);

ALTER TABLE public.address OWNER TO hr;

CREATE SEQUENCE public.address_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.address_seq OWNER TO hr;

CREATE TABLE public.department (
    id integer NOT NULL,
    name character varying(255)
);

ALTER TABLE public.department OWNER TO hr;

CREATE SEQUENCE public.department_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.department_seq OWNER TO hr;

CREATE TABLE public.employee (
    id integer NOT NULL,
    name character varying(255),
    salary bigint NOT NULL,
    address_id integer,
    boss_id integer,
    department_id integer
);

ALTER TABLE public.employee OWNER TO hr;

CREATE TABLE public.employee_projects (
    employees_id integer NOT NULL,
    projects_id integer NOT NULL
);

ALTER TABLE public.employee_projects OWNER TO hr;

CREATE SEQUENCE public.employee_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.employee_seq OWNER TO hr;

CREATE TABLE public.phone (
    id integer NOT NULL,
    phonenumber character varying(255),
    type character varying(255),
    employee_id integer NOT NULL
);

ALTER TABLE public.phone OWNER TO hr;

CREATE SEQUENCE public.phone_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.phone_seq OWNER TO hr;

CREATE TABLE public.project (
    dtype character varying(31) NOT NULL,
    id integer NOT NULL,
    name character varying(255)
);

ALTER TABLE public.project OWNER TO hr;

CREATE SEQUENCE public.project_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.project_seq OWNER TO hr;

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.employee_projects
    ADD CONSTRAINT employee_projects_pk PRIMARY KEY (employees_id, projects_id);

ALTER TABLE ONLY public.phone
    ADD CONSTRAINT phone_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.phone
    ADD CONSTRAINT phone_employee_fk FOREIGN KEY (employee_id) REFERENCES public.employee(id);

ALTER TABLE ONLY public.employee_projects
    ADD CONSTRAINT employee_projects_employee_fk FOREIGN KEY (employees_id) REFERENCES public.employee(id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_department_fk FOREIGN KEY (department_id) REFERENCES public.department(id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT boss_employee_fk FOREIGN KEY (boss_id) REFERENCES public.employee(id);

ALTER TABLE ONLY public.employee_projects
    ADD CONSTRAINT employee_projects_fk FOREIGN KEY (projects_id) REFERENCES public.project(id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_address_fk FOREIGN KEY (address_id) REFERENCES public.address(id);
