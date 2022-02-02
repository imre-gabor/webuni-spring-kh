create table image (id int8 not null, bytes bytea, airport_id int8, primary key (id));
create table image_aud (id int8 not null, rev int4 not null, revtype int2, bytes bytea, primary key (id, rev));
create table airport_image_aud (rev int4 not null, airport_id int8 not null, id int8 not null, revtype int2, primary key (rev, airport_id, id));
alter table if exists image add constraint FK_image_to_airport foreign key (airport_id) references airport;
alter table if exists image_aud add constraint FK_image_to_revinfo foreign key (rev) references revinfo;
alter table if exists airport_image_aud add constraint FK_airport_image_aud_to_revinfo foreign key (rev) references revinfo;