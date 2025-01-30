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

INSERT INTO empresa (id, cif, direccion, coordenadas, nombre)
VALUES (nextval('empresa_id'), '12345ABC', 'Calle Tarso, 7', '-123.0943, 98.7653', 'Fini Golosinas S.L.');

INSERT INTO empresa (id, cif, direccion, coordenadas, nombre)
VALUES (nextval('empresa_id'), '6547TRG', 'Calle Albareda, 9', '-129.0943, 91.7653', 'RKPJ S.L.');

INSERT INTO trabajador(id, nombre, apellidos, email, telefono, area, puesto)
VALUES (nextval('trabajador_id'), 'David', 'Sevillano Dominguez', 'dvd.sevi@gmail.com', 65479023, 'Desarrollo', 'Mobile');

INSERT INTO contacto(profesor_id, trabajador_id, fecha, canal, resumen)
VALUES (1, 1, '2025-01-17', 'email', 'Aceptación del convenio de practicas');
