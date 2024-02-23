drop database if exists module3;
create database if not exists module3;
use module3;

create table classroom(
	id int auto_increment primary key,
    `name` varchar(255)
);

create table student(
	id int primary key auto_increment,
    `name` varchar(255) not null,
    email varchar(255) not null,
    date_birth date,
    address varchar(255),
    phone varchar(255),
    c_id int,
    foreign key(c_id) references classroom(id)
);
insert into classroom
values (1, 'C0923H1'),
(2, 'C1023G1'),
(3, 'C1123H1');
insert into student
values (1, 'Huỳnh Dụng', 'thanhdung@gmail.com', '1994-03-26', 'Quảng Ngãi', '0356230012', 1);

insert into student
values (3, 'Huỳnh', 'thanhdung@gmail.com', '1994-03-26', 'Quảng Ngãi', '0356230012', 1);

select student.*, classroom.name as nameClass
from student
join classroom on classroom.id = student.c_id
having name  like 'Huỳnh';

