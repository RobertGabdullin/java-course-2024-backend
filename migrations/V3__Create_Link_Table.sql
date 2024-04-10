--liquibase formatted sql
--changeset RobertGabdullin:3
CREATE TABLE IF NOT EXISTS Link (
    link_id         BIGINT GENERATED ALWAYS AS IDENTITY,
    url             TEXT NOT NULL,
    update_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by       TEXT NOT NULL,
    PRIMARY KEY (link_id)
);
