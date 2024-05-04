--liquibase formatted sql
--changeset RobertGabdullin:4 runOnChange:true
CREATE TABLE IF NOT EXISTS Chat (
    chat_id         BIGINT,
    PRIMARY KEY (chat_id)
);
