INSERT INTO profesor(id, nombre, apellidos, email, telefono)
VALUES (nextval('profesor_seq'),'Lucia', 'Sanchez Garcia', 'lucia@gmail.com', 6554321);

INSERT INTO usuario(id, username, password, role, profesor_id)
VALUES (nextval('usuario_seq'),'luciasangar', '123456789', 'profesor', 1);

INSERT INTO profesor(id, nombre, apellidos, email, telefono)
VALUES (nextval('profesor_seq'), 'Luis', 'Gómez Torres', 'lgomez@gmail.com', 678548923);

INSERT INTO usuario(id, username, password, role, profesor_id)
VALUES (nextval('usuario_seq'),'luisgoto', '56748392', 'profesor', 51);
