create table users(
	id int primary key auto_increment,
	name varchar(64),
	age int,
	remark text
) engine=InnoDB;