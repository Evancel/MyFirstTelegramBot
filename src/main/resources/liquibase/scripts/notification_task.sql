--liquibase formatted sql

-- changeset amargolina:1
CREATE TABLE notification_task (
                       id SERIAL,
                       chat_id  NUMERIC,
                       message TEXT,
                       date TIMESTAMP
)