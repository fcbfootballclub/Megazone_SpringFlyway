create table if not exists subject (
    subject_id bigint primary key auto_increment,
    subject_title varchar(50) unique not null
);

insert into subject (subject_title) values ('Theropithecus gelada');
insert into subject (subject_title) values ('Butorides striatus');
insert into subject (subject_title) values ('Mazama americana');
insert into subject (subject_title) values ('Axis axis');
insert into subject (subject_title) values ('Corvus brachyrhynchos');

