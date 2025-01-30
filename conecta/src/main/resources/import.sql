INSERT INTO profesor(id, nombre, apellidos, email, telefono)
VALUES (nextval('profesor_seq'),'Lucia', 'Sanchez Garcia', 'lucia@gmail.com', 6554321);

INSERT INTO usuario(id, username, password, role, profesor_id)
VALUES (nextval('usuario_seq'),'luciasangar', '123456789', 'profesor', 1);

INSERT INTO profesor(id, nombre, apellidos, email, telefono)
VALUES (nextval('profesor_seq'), 'Luis', 'Gómez Torres', 'lgomez@gmail.com', 678548923);

INSERT INTO usuario(id, username, password, role, profesor_id)
VALUES (nextval('usuario_seq'),'luisgoto', '56748392', 'profesor', 51);

INSERT INTO titulo (id, nombre, duracion, grado)
VALUES (nextval('titulo_seq'),'Técnico Superior en Desarrollo de Aplicaciones Multiplataforma', 2000, 'Grado Superior');

INSERT INTO titulo (id, nombre, duracion, grado)
VALUES (nextval('titulo_seq'),'Técnico en Sistemas Microinformáticos y Redes', 1500, 'Grado Medio');

INSERT INTO titulo (id, nombre, duracion, grado)
VALUES (nextval('titulo_seq'),'Técnico Superior en Administración de Sistemas Informáticos en Red', 2000, 'Grado Superior');


INSERT INTO curso (id, nombre, horas_empresa, titulo_id)
VALUES (nextval('curso_seq'),'Primero', 400, 1);

INSERT INTO curso (id, nombre, horas_empresa, titulo_id)
VALUES (nextval('curso_seq'),'Segundo', 300, 51);

INSERT INTO curso (id, nombre, horas_empresa, titulo_id)
VALUES (nextval('curso_seq'),'Segundo', 500, 1);


INSERT INTO profesor_cursos (curso_id, profesor_id)
VALUES (1, 1);

INSERT INTO profesor_cursos (curso_id, profesor_id)
VALUES (1, 51);

INSERT INTO profesor_cursos (curso_id, profesor_id)
VALUES (51, 1);





