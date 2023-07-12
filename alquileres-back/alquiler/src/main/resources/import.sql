INSERT INTO usuarios (username,password,email,nombre,apellido,dni,telefono) VALUES('Damiang66', '12345', 'damian@gmail.com','damian','adamo','29788268','2614520055')
INSERT INTO usuarios (username,password,email,nombre,apellido,dni,telefono) VALUES('Camila', '12345', 'camila@gmail.com','camila','adamo','29788268','2614520055');
INSERT INTO usuarios (username,password,email,nombre,apellido,dni,telefono) VALUES('Josefina', '12345', 'josefina@gmail.com','josefina','adamo','29788268','2614520055');
INSERT INTO usuarios (username,password,email,nombre,apellido,dni,telefono) VALUES('Fran', '12345', 'fran@gmail.com','fran','adamo','29788268','2614520055');
INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES('ROLE_PROPIETARIO');


INSERT INTO propiedades (propietarios,ubicacion,precio) value ('Casa de campo', 100);
INSERT INTO propiedades (propietarios,ubicacion,precio) value ('Casa Quinta', 222.20);

INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES(3,1);

