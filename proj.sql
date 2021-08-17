create database projecthello;
use projecthello;
show tables;
drop database projecthello;
-- ------------------- begin employee
insert into employee value (1,"bara","2000-12-12","Gypsum","jr",50,Null);
insert into employee value (2,"basel","2000-12-12","Photograph","Photographer",50,Null);
insert into employee value (3,"mohammad","2000-12-12","Manufacturing","nothing",50,Null);
insert into employee value (4,"abd","2000-12-12","Finishing","jr",50,Null);

insert into employee value (5,"yousef","2000-12-12","Gypsum","jr",50,Null);
insert into employee value (6,"jamal","2000-12-12","Photograph","Photographer",50,Null);
insert into employee value (7,"ali","2000-12-12","Manufacturing","nothing",50,Null);
insert into employee value (8,"firas","2000-12-12","Finishing","jr",50,Null);

CREATE TABLE employee ( employee_ID int NOT NULL ,employee_name varchar(35) DEFAULT NULL,date_of_birth date DEFAULT NULL
						,job_type varchar(20) DEFAULT NULL, experiance varchar(30) DEFAULT NULL
                        ,salary_hourly_rate double DEFAULT NULL, salary_per_month double DEFAULT NULL
                        ,PRIMARY KEY (employee_ID));
select*from employee;
drop table employee;   
 -- ----------------- begin sections    
 -- Gypsum, Photograph, Manufacturing, Finishing, Delivery
insert into sections value (1,1,"Gypsum","Photograph","10:25:00","13:25:00","2020-12-12"),
 (1,2,"Photograph","Manufacturing","14:25:00","16:00:00","2020-12-12"),
 (1,3,"Manufacturing","Finishing","10:15:00","14:25:00","2020-12-13"),
(1,4,"Finishing","Delivery","15:25:00","16:25:00","2020-12-13"),

(2,1,'Gypsum','Photograph','10:00:00','12:00:00','2020-11-06'),
(2,2,"Photograph","Manufacturing","12:25:00","13:00:00","2020-11-06"),
(2,3,"Manufacturing","Finishing","13:15:00","16:25:00","2020-11-06"),

(3,1,'Gypsum','Photograph','11:00:00','12:00:00','2020-10-02'),
(3,2,"Photograph","Manufacturing","12:25:00","13:00:00","2020-10-02");
CREATE TABLE sections (model_ID int NOT NULL,employee_ID int NOT NULL
					   ,current_stage varchar(20) NOT NULL,next_stage varchar(20) NOT NULL
					   ,start_time time DEFAULT NULL,finish_time time DEFAULT NULL
                       ,date_worked date not null 
                       
                       ,PRIMARY KEY (model_ID,current_stage,next_stage,employee_ID)
                       ,FOREIGN KEY (employee_ID) REFERENCES employee (employee_ID) on delete cascade on update no action ,
                       FOREIGN KEY (model_ID) REFERENCES Model (MID) on delete cascade on update no action,

                       CONSTRAINT checktime CHECK (start_time<finish_time),
                       
                       CONSTRAINT checkCstage CHECK (current_stage="Gypsum" or current_stage="Photograph" 
                       or current_stage="Manufacturing"or current_stage="Finishing" ),
                       
                       CONSTRAINT checkRstage CHECK (next_stage="Delivery" or next_stage="Photograph" 
                       or next_stage="Manufacturing"or next_stage="Finishing" ),
                       
                       CONSTRAINT checkCRstage CHECK ( (current_stage="Gypsum" and next_stage="Photograph") or 
                       (current_stage="Photograph" and next_stage="Manufacturing") or 
                       (current_stage="Manufacturing" and next_stage="Finishing") or 
                       (current_stage="Finishing" and next_stage="Delivery") )
                       ) ;

-- we have to make model id and the receive date forign key
 -- we have to make entry date also forign key
drop table sections;
select * from sections;

-- clock table
CREATE TABLE clock (employee_ID int NOT NULL,entry_date date not null,
					entry_time time DEFAULT NULL,out_time time DEFAULT NULL
                    ,PRIMARY KEY (employee_ID,entry_date)
                    ,FOREIGN KEY (employee_ID) REFERENCES employee (employee_ID) ON DELETE CASCADE on update no action
                    ,CONSTRAINT checktimeE CHECK (entry_time<out_time)
                    );
                    
                    select * from clock;
				
select C.employee_ID,E.employee_name,E.job_type,timediff(C.out_time,C.entry_time),E.salary_hourly_rate
From clock C,employee E
where E.employee_ID=C.employee_ID and C.entry_date<"to" and C.entry_date>"from";

insert into clock values 
 (1,"2020-12-12","08:25:00","18:25:00"),
 (2,"2020-12-12","09:25:00","18:00:00"),
 (3,"2020-12-12","09:00:00","17:30:00"),
 (4,"2020-12-12","08:40:00","18:25:00"),
 (5,"2020-12-12","08:25:00","18:25:00"),
 (6,"2020-12-12","09:25:00","18:00:00"),
 (7,"2020-12-12","09:00:00","17:30:00"),
 (8,"2020-12-12","08:40:00","18:25:00"),
 
 (1,"2020-12-13","08:25:00","18:25:00"),
 (2,"2020-12-13","09:25:00","18:00:00"),
 (3,"2020-12-13","09:00:00","17:30:00"),
 (4,"2020-12-13","08:40:00","18:25:00"),
 (5,"2020-12-13","08:25:00","18:25:00"),
 (6,"2020-12-13","09:25:00","18:00:00"),
 (7,"2020-12-13","09:00:00","17:30:00"),
 (8,"2020-12-13","08:40:00","18:25:00"),
 
 (1,"2020-11-06","08:25:00","18:25:00"),
 (2,"2020-11-06","09:25:00","18:00:00"),
 (3,"2020-11-06","09:00:00","17:30:00"),
 (4,"2020-11-06","08:40:00","18:25:00"),
 (5,"2020-11-06","08:25:00","18:25:00"),
 (6,"2020-11-06","09:25:00","18:00:00"),
 (7,"2020-11-06","09:00:00","17:30:00"),
 (8,"2020-11-06","08:40:00","18:25:00"),
 
 (1,"2020-11-07","08:25:00","18:25:00"),
 (2,"2020-11-07","09:25:00","18:00:00"),
 (3,"2020-11-07","09:00:00","17:30:00"),
 (4,"2020-11-07","08:40:00","18:25:00"),
 (5,"2020-11-07","08:25:00","18:25:00"),
 (6,"2020-11-07","09:25:00","18:00:00"),
 (7,"2020-11-07","09:00:00","17:30:00"),
 (8,"2020-11-07","08:40:00","18:25:00"),(8,"2020-11-08","08:40:00","18:25:00"),
 
 (1,"2020-10-02","08:25:00","18:25:00"),
 (2,"2020-10-02","09:25:00","18:00:00"),
 (3,"2020-10-02","09:00:00","17:30:00"),
 (4,"2020-10-02","08:40:00","18:25:00"),
 (5,"2020-10-02","08:25:00","18:25:00"),
 (6,"2020-10-02","09:25:00","18:00:00"),
 (7,"2020-10-02","09:00:00","17:30:00"),
 (8,"2020-10-02","08:40:00","18:25:00"),
 
 (1,"2020-10-03","08:25:00","18:25:00"),
 (2,"2020-10-03","09:25:00","18:00:00"),
 (3,"2020-10-03","09:00:00","17:30:00"),
 (4,"2020-10-03","08:40:00","18:25:00"),
 (5,"2020-10-03","08:25:00","18:25:00"),
 (6,"2020-10-03","09:25:00","18:00:00"),
 (7,"2020-10-03","09:00:00","17:30:00"),
 (8,"2020-10-03","08:40:00","18:25:00") ;

select*from clock;
drop table clock;



-- doctor table
create table doctor (
	demail varchar(50) primary key not null,
    dname varchar(50) not null,
    dnumber int not null,
	clinic varchar(50) not null
    );
    
insert into doctor 
values ('ali1@gmail.com', 'ali1', 1, 'clinic1'),
	   ('ali2@gmail.com', 'ali2', 2, 'clinic2'),
	   ('ali3@gmail.com', 'ali3', 3, 'clinic3'),
       ('ali4@gmail.com', 'ali4', 4, 'clinic4');
select * from doctor;

-- patient table
create table patient (
	gender varchar(50) not null,
    pname varchar(50) not null,
    demail varchar(50) not null,
	pid int primary key auto_increment not null, 
    pcost varchar(50) not null,
	foreign key(demail) references doctor(demail)
    );
    
insert into patient 
values ('female', 'mohammad', 'ali1@gmail.com', 1, 100),
	('female', 'basil', 'ali1@gmail.com', 2, 100),
    ('female', 'baraa', 'ali1@gmail.com', 3, 100),
    ('female', 'abd', 'ali1@gmail.com', 4, 100);
select * from patient;


create table Model(
	MID int not null primary key,
    material_type varchar(15) not null,
    unit_number int not null,
    color varchar(12) not null,
    DEmail varchar(30) not null,
    cost real not null,
    handle_to_sections_in date not null,
    foreign key (DEmail) references Doctor(DEmail),
    foreign key (material_type) references price4material(material_type));
    drop table Model;
insert into Model value  (1,"Metal",5,"red","ali1@gmail.com",5000,"2020-12-11");
insert into Model value  (2,"Zircon",2,"red","ali1@gmail.com",7500,"2020-11-05");
insert into Model value  (3,"Emax",14,"red","ali1@gmail.com",13000,"2020-10-01");

create table price4material(
	material_type varchar(15) primary key,
    price real);
insert into price4material values
	('Zircon', 150),
    ('Metal', 110),
    ('Emax', 80),
    ('Temporary', 50);
select * from price4material;





create table sections2clock (model_ID int NOT NULL,employee_ID int NOT NULL ,
PRIMARY KEY (model_ID,employee_ID),
FOREIGN KEY (employee_ID) REFERENCES sections (employee_ID) on delete cascade,
FOREIGN KEY (employee_ID) REFERENCES clock (employee_ID) on delete cascade,
FOREIGN KEY (model_ID) REFERENCES sections (model_ID) on delete cascade);

select * from sections2clock;
drop table sections2clock;
 
 
 -- -------------------------
 select S.employee_ID,E.employee_name,TimeDIFF(S.finish_time,S.start_time),E.job_type
From employee E , sections S
where S.employee_ID=E.employee_ID and S.employee_ID=1 and S.date_worked>'2000-01-01' and S.date_worked<'2000-12-31';
                       
  select count(E.employee_ID)
from employee E;
select E.employee_ID
from employee E;

select * from sections,clock where sections.date_worked=clock.entry_date and sections.employee_ID=clock.employee_ID;

-- entry_time ,,,out_time
select  S.model_ID,S.current_stage,S.next_stage,S.start_time,S.finish_time,S.date_worked,S.employee_ID
From Sections S,Clock C,Model M, employee E
where S.employee_ID=E.employee_ID and S.employee_ID=C.employee_ID and S.date_worked = C.entry_date and M.MID=S.model_ID and S.start_time>C.entry_time and S.start_time<C.out_time and 
S.finish_time>C.entry_time and S.finish_time<C.out_time and M.handle_to_sections_in<S.date_worked and S.current_stage=E.job_type 
order by 1,7;


select S.model_ID,S.current_stage,S.next_stage
From sections S,Model M
where M.MID=S.model_ID;

select M.MID
from Model M;                     
                       
                       
                       
  select M.MID,M.unit_number,TimeDIFF(S.finish_time,S.start_time),M.handle_to_sections_in,S.date_worked,S.next_stage
From Model M , sections S
where M.MID=S.model_ID and M.MID=1 and S.date_worked>"2020-01-01" and S.date_worked<"2020-12-31";   

 select M.MID,M.unit_number,TimeDIFF(S.finish_time,S.start_time),M.handle_to_sections_in,S.date_worked,S.next_stage
From Model M , sections S
where M.MID=S.model_ID and M.MID=1 and S.date_worked>'2000-01-01' and S.date_worked<'2000-12-31';
     
select (sum(TimeDIFF(C.out_time,C.entry_time)))/3600,C.employee_ID
From clock C
where C.employee_ID=1;

select sum(C.out_time)/3600,sum(C.entry_time)/3600,C.employee_ID
From clock C
where C.employee_ID=1;
update Sections set model_ID= 2,current_stage= 'Finishing',
next_stage= 'Delivery',start_time= '15:00:00',finish_time= '16:40:00',
date_of_work= '2000-12-12',employee_ID= 4 where model_ID=2 ; 




select E.employee_ID,E.job_type,C.entry_time,C.out_time,C.entry_date
from clock C,employee E
where C.employee_ID=E.employee_ID;

                    

select * from Model;
select count(M.MID)
from Model M;

			-- hhhh