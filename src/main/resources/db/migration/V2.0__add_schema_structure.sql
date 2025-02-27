CREATE TABLE client (
    id bigint PRIMARY KEY NOT NULL,
    first_name varchar(31) not null,
    middle_name varchar(31) not null
);

CREATE TABLE author (
    id bigint PRIMARY KEY NOT NULL,
    name varchar(255) not null,
    date_of_birth bigint not null
);
CREATE TABLE bookcase (
    id bigint PRIMARY KEY NOT NULL,
    number integer not null
);

CREATE TABLE users (
    id bigint PRIMARY KEY NOT NULL,
    email varchar(63) unique not null,
    username varchar(31) not null,
    password varchar(255) not null,
    role varchar(15) not null,
    client_id bigint
);
ALTER TABLE users
    ADD CONSTRAINT fk_user_client
        FOREIGN KEY (client_id)
            REFERENCES client(id);

CREATE TABLE shelf (
    id bigint PRIMARY KEY NOT NULL,
    name varchar(31) not null,
    bookcase_id bigint
);
ALTER TABLE shelf
    ADD CONSTRAINT fk_shelf_bookcase
        FOREIGN KEY (bookcase_id)
            REFERENCES bookcase(id);

CREATE TABLE book (
    id bigint PRIMARY KEY NOT NULL,
    genre varchar(63) not null,
    is_read boolean not null,
    image_name varchar(255) not null,
    name varchar(255) not null,
    author_id bigint,
    client_id bigint,
    shelf_id bigint
);
ALTER TABLE book
    ADD CONSTRAINT fk_book_author
        FOREIGN KEY (author_id)
            REFERENCES author(id),
    ADD CONSTRAINT fk_book_client
        FOREIGN KEY (client_id)
            REFERENCES client(id),
    ADD CONSTRAINT fk_book_shelf
        FOREIGN KEY (shelf_id)
            REFERENCES shelf(id);