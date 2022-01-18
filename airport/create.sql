create sequence hibernate_sequence start 1 increment 1;
create table address (id int8 not null, city varchar(255), country varchar(255), street varchar(255), zip varchar(255), primary key (id));
create table airport (id int8 not null, iata varchar(255), name varchar(20), address_id int8, primary key (id));
create table flight (id int8 not null, flight_number varchar(255), takeoff_time timestamp, landing_id int8, takeoff_id int8, primary key (id));
alter table if exists airport add constraint FKlabd5pksv56twbgly520u0fm5 foreign key (address_id) references address;
alter table if exists flight add constraint FKlo8um1qhyvn4ec5tt90u49g9g foreign key (landing_id) references airport;
alter table if exists flight add constraint FKnvldg0a2nu9ivpbmebljf4g1i foreign key (takeoff_id) references airport;
