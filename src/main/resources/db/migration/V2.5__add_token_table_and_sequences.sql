CREATE TABLE refresh_token (
    id bigint PRIMARY KEY NOT NULL,
    token varchar(255) not null unique,
    expiry_date timestamp with time zone not null ,
    user_id bigint
);

ALTER TABLE refresh_token
    ADD CONSTRAINT fk_token_user
        FOREIGN KEY (user_id)
            REFERENCES users(id);

CREATE SEQUENCE refresh_token_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    CACHE 10;