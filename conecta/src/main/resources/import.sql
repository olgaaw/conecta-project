INSERT INTO profesor(id, nombre, apellidos, email, telefono)
VALUES (nextval('profesor_seq'),'Lucia', 'Sanchez Garcia', 'lucia@gmail.com', 6554321);

INSERT INTO profesor(id, nombre, apellidos, email, telefono)
VALUES (nextval('profesor_seq'), 'Luis', 'Gómez Torres', 'lgomez@gmail.com', 678548923);


INSERT INTO usuario(id, username, password, role, profesor_id, deleted)
VALUES (nextval('usuario_seq'),'luciasangar', '123456789', 'profesor', 1, false);

INSERT INTO usuario(id, username, password, role, profesor_id, deleted)
VALUES (nextval('usuario_seq'),'luisgoto', '56748392', 'profesor', 51, false);


INSERT INTO familia_profesional (id, nombre)
VALUES (nextval('familia_profesional_seq'),'Tecnología');

INSERT INTO familia_profesional (id, nombre)
VALUES (nextval('familia_profesional_seq'),'Salud');


INSERT INTO titulo (id, nombre, duracion, grado, familia_profesional_id)
VALUES (nextval('titulo_seq'),'Técnico Superior en Desarrollo de Aplicaciones Multiplataforma', 2000, 'Grado Superior', 1);

INSERT INTO titulo (id, nombre, duracion, grado, familia_profesional_id)
VALUES (nextval('titulo_seq'),'Técnico en Sistemas Microinformáticos y Redes', 1500, 'Grado Medio', 51);

INSERT INTO titulo (id, nombre, duracion, grado, familia_profesional_id)
VALUES (nextval('titulo_seq'),'Técnico Superior en Administración de Sistemas Informáticos en Red', 2000, 'Grado Superior', 1);



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
VALUES (nextval('empresa_seq'),'A12345678', 'Calle Falsa 123', '40.416775,-3.703790', 'Empresa de Tecnología S.A.');

INSERT INTO empresa (id, cif, direccion, coordenadas, nombre)
VALUES (nextval('empresa_seq'),'B87654321', 'Avenida Siempre Viva 742', '40.416775,-3.703790', 'Salud y Vida S.L.');

INSERT INTO empresa (id, cif, direccion, coordenadas, nombre) VALUES (nextval('empresa_seq'), 'A12345678', 'Calle Falsa 123', '40.416775,-3.703790', 'Empresa de Tecnología S.A.');
INSERT INTO empresa (id, cif, direccion, coordenadas, nombre) VALUES (nextval('empresa_seq'), 'B87654321', 'Avenida Siempre Viva 742', '40.416775,-3.703790', 'Salud y Vida S.L.');


INSERT INTO empresa_familias_profesionales (empresa_id, familia_profesional_id)
VALUES (1, 1); -- Empresa de Tecnología con Tecnología

INSERT INTO empresa_familias_profesionales (empresa_id, familia_profesional_id)
VALUES (51, 51); -- Salud y Vida con Salud

INSERT INTO convocatoria (id, curso_Escolar, nombre) VALUES (nextval('convocatoria_seq'), '2024-2025', 'Septiembre');
INSERT INTO convocatoria (id, curso_Escolar, nombre) VALUES (nextval('convocatoria_seq'), '2024-2025', 'Diciembre');


INSERT INTO demanda (id, requisitos, empresa_id, cantidad_Alumnos, curso_id, convocatoria_id) VALUES (nextval('demanda_seq'), 'Necesito un software de gestión de proyectos.', 1, 3, 1, 1);
INSERT INTO demanda (id, requisitos, empresa_id, cantidad_Alumnos, curso_id, convocatoria_id) VALUES (nextval('demanda_seq'), 'Requiero servicios de consultoría en salud.', 51, 1, 1, 1);
INSERT INTO demanda (id, requisitos, empresa_id, cantidad_Alumnos, curso_id, convocatoria_id) VALUES (nextval('demanda_seq'), 'Busco un proveedor de hardware.', 101, 2, 51, 1);
INSERT INTO demanda (id, requisitos, empresa_id, cantidad_Alumnos, curso_id, convocatoria_id) VALUES (nextval('demanda_seq'), 'Solicito formación en primeros auxilios.', 151, 4, 51, 51);



INSERT INTO trabajador(id, nombre, apellidos, email, telefono, area, puesto, empresa_id)
VALUES (nextval('trabajador_seq'), 'David', 'Sevillano Dominguez', 'dvd.sevi@gmail.com', 65479023, 'Desarrollo', 'Mobile', 1);

INSERT INTO trabajador(id, nombre, apellidos, email, telefono, area, puesto, empresa_id)
VALUES (nextval('trabajador_seq'), 'Olga', 'Valor Wu', 'ovaolowu@gmail.com', 666789547, 'Diseño', 'ux/ui designer', 51);



INSERT INTO contacto(profesor_id, trabajador_id, fecha, canal, resumen, deleted)
VALUES (1, 1,'2025-01-17', 'email', 'Aceptación del convenio de practicas', false);

INSERT INTO contacto(profesor_id, trabajador_id, fecha, canal, resumen, deleted)
VALUES (1, 51, '2025-01-15', 'llamada', 'Cerrada fecha inicio de practicas primero daw', false);

INSERT INTO contacto(profesor_id, trabajador_id, fecha, canal, resumen, deleted)
VALUES (51, 51, '2025-01-15', 'llamada', 'Cerrada fecha inicio de practicas primero daw', false);

INSERT INTO contacto(profesor_id, trabajador_id, fecha, canal, resumen, deleted)
VALUES (51, 1, '2025-01-15', 'llamada', 'Cerrada fecha inicio de practicas primero daw', false);
