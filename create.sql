create table flowBasicInfo (id int primary key auto_increment, name varchar(50) not null, userId int not null, flowNum int, bpmn text);
create table jobInfo (id int primary key auto_increment, userId int not null, flowBasicInfoId int not null, fileCharMap varchar(200), processInfo text);

create table file (id int primary key auto_increment, name varchar(50) not null, absPath varchar(1024) not null);
create table tool (id int primary key auto_increment, name varchar(50) not null);
create table user (id int primary key auto_increment, name varchar(50) not null, password varchar(50) not null);
