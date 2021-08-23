# 아래 SQL은 테스트 코드를 수행해서 나온 로그에서 가져왔다. 바로 MySQL > dev database에서 실행한다.
create table posts (id bigint not null auto_increment, created_date datetime(6), modified_date datetime(6), author varchar(255), content TEXT not null, title varchar(500) not null, primary key (id)) engine=InnoDB;

create table user (id bigint not null auto_increment, created_date datetime(6), modified_date datetime(6), email varchar(255) not null, name varchar(255) not null, picture varchar(255), role varchar(255) not null, primary key (id)) engine=InnoDB;
