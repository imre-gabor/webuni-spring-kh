create sequence address_seq start with 1 increment by 50;
create table address (id bigint not null, city varchar(255), country varchar(255), street varchar(255), zip varchar(255), primary key (id));
alter table airport add column address_id bigint;
alter table airport add constraint FK_airport_to_address foreign key (address_id) references address;
