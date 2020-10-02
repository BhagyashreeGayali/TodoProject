create database todo2;

use todo2;
create table task(taskid int primary key NOT NULL AUTO_INCREMENT,
			TaskName varchar(20),description varchar(20)
			,status varchar(20),userid int,constraint frfk foreign key (userid) REFERENCES  
					user(userid));
select * from task;

create table User(userid int primary key not null auto_increment, FirstName varchar(20),LastName varchar(20),Gender varchar(10),
					email varchar(25),mobile_no varchar(10),DOB date,
						password varchar(8),status varchar(20),RegistrationDate Date);
select * from user;

drop table task;

drop database todo2;

update task set TaskName='Exercise', description='Running' where taskid=1;

ALTER TABLE task
ADD TaskDate date;
