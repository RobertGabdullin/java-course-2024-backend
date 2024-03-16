--liquibase formatted sql
--changeset RobertGabdullin:1
CREATE TABLE Chat (
    chat_id         BIGINT GENERATED ALWAYS AS IDENTITY,
    username        VARCHAR(255),
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    PRIMARY KEY (chat_id)
);
