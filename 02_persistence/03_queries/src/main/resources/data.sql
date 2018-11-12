INSERT INTO public.project (dtype, id, name) VALUES ('DesignProject', 1000, 'Neue Website');
INSERT INTO public.project (dtype, id, name) VALUES ('QualityProject', 1001, 'Quality Gate 2');

INSERT INTO public.department (id, name) VALUES (nextval('department_seq'), 'HR');

INSERT INTO public.address (id, city, state, street, zip) VALUES (nextval('address_seq'), 'Rehag', 'AG', 'Plattenstrasse 26', '5046');
INSERT INTO public.employee (id, name, salary, address_id, boss_id, department_id) VALUES (nextval('employee_seq'), 'Ursula Friedman', 95000, currval('address_seq'), null, currval('department_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '031 333 11 12', 'WORK', currval('employee_seq'));

INSERT INTO public.department (id, name) VALUES (nextval('department_seq'), 'IT');

INSERT INTO public.address (id, city, state, street, zip) VALUES (nextval('address_seq'), 'Fruthwilen', 'ZH', 'Im Sandbüel 55', '8269');
INSERT INTO public.employee (id, name, salary, address_id, boss_id, department_id) VALUES (1000, 'Hans Boss', 175000, currval('address_seq'), null, currval('department_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '031 333 11 01', 'WORK', currval('employee_seq'));

INSERT INTO public.address (id, city, state, street, zip) VALUES (nextval('address_seq'), 'Bern', 'BE', 'Rosengasse 1', '3012');
INSERT INTO public.employee (id, name, salary, address_id, boss_id, department_id) VALUES (nextval('employee_seq'), 'Peter Muster', 88000, currval('address_seq'), 1000, currval('department_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '044 450 61 34', 'HOME', currval('employee_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '031 333 11 32', 'WORK', currval('employee_seq'));

INSERT INTO public.employee_projects (employees_id, projects_id) VALUES (currval('employee_seq'), 1000);

INSERT INTO public.address (id, city, state, street, zip) VALUES (nextval('address_seq'), 'Vaugondry', 'VD', 'Via Gabbietta 77', '1423');
INSERT INTO public.employee (id, name, salary, address_id, boss_id, department_id) VALUES (nextval('employee_seq'), 'Luca Traugott', 72000, currval('address_seq'), 1000, currval('department_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '024 656 44 65', 'HOME', currval('employee_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '031 333 11 15', 'WORK', currval('employee_seq'));

INSERT INTO public.employee_projects (employees_id, projects_id) VALUES (currval('employee_seq'), 1001);

INSERT INTO public.address (id, city, state, street, zip) VALUES (nextval('address_seq'), 'Unterstammheim', 'ZH', 'Via Verbano 75', '8476');
INSERT INTO public.employee (id, name, salary, address_id, boss_id, department_id) VALUES (nextval('employee_seq'), 'Lea Schulze', 72000, currval('address_seq'), 1000, currval('department_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '052 527 23 11', 'HOME', currval('employee_seq'));
INSERT INTO public.phone (id, phonenumber, type, employee_id) VALUES (nextval('phone_seq'), '031 333 11 76', 'WORK', currval('employee_seq'));

INSERT INTO public.employee_projects (employees_id, projects_id) VALUES (currval('employee_seq'), 1000);

INSERT INTO public.address (id, city, state, street, zip) VALUES (nextval('address_seq'), 'Geiss', 'ZH', 'Schliffgasse 64', '6123');
INSERT INTO public.employee (id, name, salary, address_id, boss_id, department_id) VALUES (nextval('employee_seq'), 'Felix Beyer', 79000, currval('address_seq'), 1000, currval('department_seq'));
