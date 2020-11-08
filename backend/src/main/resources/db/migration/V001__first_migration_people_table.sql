create table people (
	id bigint not null auto_increment primary key,
    name varchar(60),
    cpf varchar(15),
    date_birth date,
    email varchar(255)
);