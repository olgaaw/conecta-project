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

-- Insertar Familias Profesionales
INSERT INTO familia_profesional (nombre) VALUES ('Tecnología');
INSERT INTO familia_profesional (nombre) VALUES ('Salud');

-- Insertar Empresas
INSERT INTO empresa (cif, direccion, coordenadas, nombre) VALUES ('A12345678', 'Calle Falsa 123', '40.416775,-3.703790', 'Empresa de Tecnología S.A.');
INSERT INTO empresa (cif, direccion, coordenadas, nombre) VALUES ('B87654321', 'Avenida Siempre Viva 742', '40.416775,-3.703790', 'Salud y Vida S.L.');

-- Insertar Familias Profesionales en la tabla intermedia
INSERT INTO empresa_familias_profesionales (empresa_id, familia_profesional_id) VALUES (1, 1); -- Empresa de Tecnología con Tecnología
INSERT INTO empresa_familias_profesionales (empresa_id, familia_profesional_id) VALUES (2, 2); -- Salud y Vida con Salud

INSERT INTO demanda (requisitos, empresa_id, cantidad_Alumnos) VALUES ('Necesito un software de gestión de proyectos.', 1, 3); -- Demanda para Empresa de Tecnología
INSERT INTO demanda (requisitos, empresa_id, cantidad_Alumnos) VALUES ('Requiero servicios de consultoría en salud.', 2, 1); -- Demanda para Salud y Vida
INSERT INTO demanda (requisitos, empresa_id, cantidad_Alumnos) VALUES ('Busco un proveedor de hardware.', 2, 2); -- Otra demanda para Empresa de Tecnología
INSERT INTO demanda (requisitos, empresa_id, cantidad_Alumnos) VALUES ('Solicito formación en primeros auxilios.', 2, 4); -- Otra demanda para Salud y Vida





