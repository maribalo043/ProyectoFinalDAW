INSERT INTO categorias (nombre,activa) VALUES ('Mini',true);
INSERT INTO categorias (nombre,activa) VALUES ('Guajes',true);
INSERT INTO categorias (nombre,activa) VALUES ('Promesas',true);
INSERT INTO categorias (nombre,activa) VALUES ('Estrellas',true);
INSERT INTO categorias (nombre,activa) VALUES ('Ok Masters',true);

INSERT INTO partidos (resultado,pista,categoria_id) VALUES ('6-1','hola que tal',1);
INSERT INTO partidos (resultado,pista,categoria_id) VALUES ('6-2','hola que tal',1);
INSERT INTO partidos (resultado,pista,categoria_id) VALUES ('6-3','hola que tal',2);
INSERT INTO partidos (resultado,pista,categoria_id) VALUES ('6-4','hola que tal',2);

INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,categoria_id) VALUES ('Jaimitos','skaskhca',698585856,5);
INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,categoria_id) VALUES ('Pepitos','vksvdvljs',696969699,4);
INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,categoria_id) VALUES ('Manolitas','vdflahfla',696969682,2);
INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,categoria_id) VALUES ('Jositos','vdflahfla',696969682,1);

INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567A','Mario',1234,'L',false,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567B','Mario',1234,'S',false,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234587D','Mario',1234,'XS',false,2);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234564D','Mario',1234,'M',false,2);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567C','Mario',1234,'2XL',true,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567D','Mario',1234,'3XL',false,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1634567D','Mario',1234,'S',true,2);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234527D','Mario',1234,'3XS',false,2);

//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (1,1,'1º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (1,2,'1º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (2,3,'2º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (2,4,'2º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (3,1,'3º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (3,3,'3º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (4,2,'4º Partido');
//INSERT INTO juega (partido_id,equipo_id,fecha) VALUES (4,4,'4º Partido');
