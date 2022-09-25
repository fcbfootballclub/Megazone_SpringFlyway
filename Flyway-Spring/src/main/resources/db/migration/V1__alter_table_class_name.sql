use test;
create table if not exists class_name (
  class_id bigint auto_increment primary key ,
  class_name varchar(30) not null unique,
  grade int not null
);

create table if not exists student (
    student_id bigint primary key auto_increment,
    student_name varchar(30) not null,
    student_email varchar(50) not null unique,
    class_id bigint references class_name(class_id)
);

