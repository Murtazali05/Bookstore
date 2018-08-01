create table role
(
  id   serial not null
    constraint role_pkey
    primary key,
  name varchar(45)
);

create table photo
(
  id            serial       not null
    constraint photo_pkey
    primary key,
  path          varchar(255) not null,
  size          integer,
  uploaded_date timestamp,
  hash_file     varchar(255)
);

create table book
(
  id          serial       not null
    constraint book_pkey
    primary key,
  title       varchar(255) not null,
  description text         not null,
  genre       varchar(45)  not null,
  pubyear     date         not null,
  price       numeric      not null,
  photo_id    integer      not null
    constraint fk_book_photo_1
    references photo
);

create table author
(
  id       serial       not null
    constraint author_pkey
    primary key,
  fio      varchar(255) not null,
  about    text         not null,
  photo_id integer      not null
    constraint fk_author_photo_1
    references photo
);

create table "user"
(
  id                serial       not null
    constraint user_pkey
    primary key,
  surname           varchar(45)  not null,
  name              varchar(45)  not null,
  email             varchar(55)  not null,
  password          varchar(255) not null,
  birthday          date,
  confirmation      boolean,
  confirm_code      varchar,
  registration_date timestamp,
  role_id           integer      not null
    constraint fk_user_role_1
    references role,
  photo_id          integer      not null
    constraint fk_user_photo_1
    references photo
);

create table cart
(
  id      serial  not null
    constraint cart_pkey
    primary key,
  user_id integer not null
    constraint fk_cart_user_1
    references "user",
  book_id integer not null
    constraint fk_cart_book_1
    references book,
  count   integer not null
);

create table book_author
(
  book_id   integer not null
    constraint fk_book_author_book_1
    references book,
  author_id integer not null
    constraint fk_book_author_author_1
    references author,
  constraint book_author_pkey
  primary key (book_id, author_id)
);

create table "order"
(
  id              serial       not null
    constraint order_pkey
    primary key,
  user_id         integer      not null
    constraint fk_order_user_1
    references "user",
  order_date      timestamp    not null,
  address         varchar(255) not null,
  delivery_method varchar(255) not null,
  payment_method  varchar(255) not null,
  status          varchar(45)  not null
);

create table order_details
(
  order_id integer not null
    constraint fk_order_book_order_1
    references "order",
  book_id  integer not null
    constraint fk_order_book_book_1
    references book,
  count    integer not null,
  constraint order_details_pkey
  primary key (order_id, book_id)
);

create table category
(
  id   serial      not null
    constraint category_pkey
    primary key,
  name varchar(55) not null,
  code varchar(255)
);

create table genre
(
  id   serial not null
    constraint genre_pkey
    primary key,
  name varchar(255)
);

create table book_category
(
  book_id     integer not null
    constraint fk_book_category_book_1
    references book,
  category_id integer not null
    constraint fk_book_category_category_1
    references category,
  constraint book_category_pkey
  primary key (book_id, category_id)
);

create table book_genre
(
  book_id  integer not null
    constraint fk_book_genre_book_1
    references book,
  genre_id integer not null
    constraint fk_book_genre_genre_1
    references genre,
  constraint book_genre_pkey
  primary key (book_id, genre_id)
);
