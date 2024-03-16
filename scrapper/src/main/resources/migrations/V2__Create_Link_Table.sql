--liquibase formatted sql
--changeset RobertGabdullin:1
CREATE TABLE Link (
    link_id         BIGINT GENERATED ALWAYS AS IDENTITY,
    url             VARCHAR(1024) NOT NULL,
    chat_id         BIGINT,
    update_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by       TEXT NOT NULL,
    FOREIGN KEY (chat_id) REFERENCES Chat(chat_id),
    PRIMARY KEY (link_id)
);
