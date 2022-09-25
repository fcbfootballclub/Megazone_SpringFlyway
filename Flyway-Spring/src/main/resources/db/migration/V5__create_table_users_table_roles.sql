create table if not exists users (
                                     user_id bigint auto_increment primary key,
                                     user_name varchar(50) unique not null,
    password varchar(50) not null,
    email varchar(50) not null unique,
    is_active tinyint not null
    );

create table if not exists roles (
                                     role_id bigint auto_increment primary key,
                                     role_title varchar(20) not null unique
    );

create table if not exists user_roles (
                                          user_id bigint,
                                          role_id bigint,
                                          foreign key (user_id) references users(user_id),
    foreign key (role_id) references roles(role_id)
    )