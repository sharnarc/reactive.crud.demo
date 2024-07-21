-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspection
CREATE TABLE IF NOT EXISTS student (
 id             SERIAL PRIMARY KEY,
 first_name      VARCHAR(100) NOT NULL,
 last_name        VARCHAR(100) NOT NULL,
 course_id  BIGINT  NOT NULL
);