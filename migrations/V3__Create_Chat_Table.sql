--liquibase formatted sql
--changeset RobertGabdullin:3
CREATE TABLE IF NOT EXISTS Chat (
    chat_id         BIGINT GENERATED ALWAYS AS IDENTITY,
    username        TEXT,
    first_name      TEXT,
    last_name       TEXT,
    PRIMARY KEY (chat_id)
);
