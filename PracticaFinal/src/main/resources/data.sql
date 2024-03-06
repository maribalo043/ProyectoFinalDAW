insert into agente (nombre, numero_telefono) values ('Sergio', '123456789');
insert into agente (nombre, numero_telefono) values ('María', '098765432');
insert into agente (nombre, numero_telefono) values ('Juan', '987654321');
insert into agente (nombre, numero_telefono) values ('Ana', '012345678');


insert into cliente (nombre) values ('Andrés');
insert into cliente (nombre) values ('Diego');
insert into cliente (nombre) values ('Daniel');
insert into cliente (nombre) values ('Sara');


insert into agente_cliente (agente_id, cliente_id) values (1, 1);

insert into agente_cliente(agente_id, cliente_id) values (2, 2);

insert into agente_cliente (agente_id, cliente_id) values (3, 3);

insert into agente_cliente (agente_id, cliente_id) values (4, 4);


insert into agente_cliente (agente_id, cliente_id) values(1, 2);

insert into agente_cliente (agente_id, cliente_id) values (3, 2);

insert into agente_cliente(agente_id, cliente_id) values (4, 1);

insert into usuario(usuario, password) values('sergio', '$2a$12$6ish92kXsSu4NnsD0N0WneCB5Fuw9PvBtTYuIaS/eYRm7epJV1xdS');
insert into usuario(usuario, password) values('mario', '$2a$12$jP5Xkwm8.fsZjtMQkCj7YOjRg3dyTDPc8wwUA13/0DeuhXBsJ7R..');


