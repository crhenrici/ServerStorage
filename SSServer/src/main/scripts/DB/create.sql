drop table if exists volume_history;
drop table if exists volume;
drop table if exists server_history;
drop table if exists server;
create table server
(
    id        bigint not null auto_increment,
    cpu_usage double precision,
    name      varchar(255),
    ram       integer,
    primary key (id)
) engine = InnoDB;

create table server_history
(
    id        bigint not null auto_increment,
    cpu_usage double precision,
    ram       integer,
    server_id bigint not null,
    primary key (id)
) engine = InnoDB;

create table volume
(
    id                      bigint not null auto_increment,
    description             varchar(255),
    full_capacity           double precision,
    latest_storage_free     double precision,
    latest_storage_ratio    double precision,
    latest_storage_reserved double precision,
    name                    varchar(255),
    server_id               bigint not null,
    primary key (id)
) engine = InnoDB;

create table volume_history
(
    id               bigint not null auto_increment,
    storage_free     double precision,
    storage_ratio    double precision,
    storage_reserved double precision,
    volume_id        bigint not null,
    primary key (id)
) engine = InnoDB;

alter table server_history
    add constraint FKsrmo3m2xd3sgc1crffv00g2ro foreign key (server_id) references server (id);
alter table volume
    add constraint FK9nkqouc07kpu45ee8pq2ferfw foreign key (server_id) references server (id);
alter table volume_history
    add constraint FKjctfi3utn164hv3p1b4qku2gl foreign key (volume_id) references volume (id);
