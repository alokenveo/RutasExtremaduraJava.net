create database if not exists tw;

create user 'tw'@'localhost' identified by 'tw2425';
grant all privileges on tw.* to 'tw'@'localhost';
flush privileges;

use RutasExtremadura;


create table if not exists usuario(
  id_usuario int(11) not null auto_increment,
  nombre varchar(50) not null,
  apellidos varchar(50) not null,
  email varchar(50) not null,
  password varchar(50) not null,
  fecha_nacimiento date not null,
  fecha_registro date not null,
  telefono varchar(15) not null,
  tipo_usuario enum('admin','normal') default 'normal',
  primary key (id_usuario)
) engine=innodb default charset=utf8 auto_increment=2;