--liquibase formatted sql
--changeset RobertGabdullin:4 runOnChange:true
CREATE TABLE IF NOT EXISTS Link (
    link_id         BIGINT GENERATED ALWAYS AS IDENTITY,
    url             TEXT NOT NULL,
    updated_at       TIMESTAMP,
    checked_at      TIMESTAMP,
    PRIMARY KEY (link_id)
);
