create table status
(
  id   serial not null
    constraint status_pkey
    primary key,
  name varchar(55) not null,
  code varchar(55) not null UNIQUE
);