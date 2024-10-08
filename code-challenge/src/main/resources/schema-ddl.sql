create table users(
    id bigint primary key auto_increment,
    user_id varchar(255) unique,
    provider_id varchar(50),
    nickname varchar(50),
    score integer,
    created_at datetime default current_timestamp on update current_timestamp,
    updated_at datetime default current_timestamp on update current_timestamp
);

create table user_problems(
    id bigint primary key auto_increment,
    user_id bigint,
    problem_id bigint,
    answer text,
    # user_problem_status_id integer,
    status tinyint,
    created_at datetime default current_timestamp on update current_timestamp,
    updated_at datetime default current_timestamp on update current_timestamp
);

# create table user_problem_status(
#     id integer primary key auto_increment,
#     name varchar(50)
# );

create table problems(
    id bigint primary key auto_increment,
    level integer,
    title varchar(255),
    description text,
    limitation varchar(255),
    input_output varchar(255),
    created_at datetime default current_timestamp on update current_timestamp,
    updated_at datetime default current_timestamp on update current_timestamp
);

create table testcases(
    id bigint primary key auto_increment,
    problem_id bigint,
    content varchar(255),
    data_type tinyint,
    type tinyint
);

create table problem_pictures(
    id bigint primary key auto_increment,
    problem_id bigint,
    name varchar(100),
    url varchar(255)
);

# create table levels(
#     id integer primary key auto_increment,
#     name varchar(20)
# );

create table user_badges(
    id bigint primary key auto_increment,
    user_id bigint,
    badge_id integer,
    created_at datetime default current_timestamp on update current_timestamp
);

create table badges(
    id integer primary key auto_increment,
    name varchar(100),
    description varchar(255)
);

alter table user_problems add foreign key(user_id) references users(id);
alter table user_problems add foreign key(problem_id) references problems(id);
# alter table user_problems add foreign key(user_problem_status_id) references user_problem_status(id);
# alter table problems add foreign key(level_id) references levels(id);
alter table testcases add foreign key(problem_id) references problems(id);
alter table problem_pictures add foreign key(problem_id) references problems(id);
alter table user_badges add foreign key(user_id) references users(id);
alter table user_badges add foreign key(badge_id) references badges(id);