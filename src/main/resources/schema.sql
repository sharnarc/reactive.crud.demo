
CREATE TABLE IF NOT EXISTS project (
 id             SERIAL PRIMARY KEY,
 project_id    VARCHAR(100) NOT NULL,
 project_name      VARCHAR(100) NOT NULL,
 project_desc        VARCHAR(100) NOT NULL,
 dept_id  INTEGER
);

CREATE TABLE IF NOT EXISTS department (
    id             SERIAL PRIMARY KEY,
    first_name        VARCHAR(100) NOT NULL,
    dept_id  INTEGER
    );

CREATE TABLE IF NOT EXISTS employees (
    id VARCHAR(255) PRIMARY KEY,
    department_ids JSONB
    );

CREATE TABLE IF NOT EXISTS departments (
    id VARCHAR(255) PRIMARY KEY
    -- other department fields...
    );

CREATE TABLE IF NOT EXISTS Store (
    store_id INTEGER PRIMARY KEY,
    store_name  VARCHAR(100) NOT NULL,
    product VARCHAR(100) NOT NULL,
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Branch (
                          id SERIAL PRIMARY KEY,
                          branch_number VARCHAR(255),
                          branch_name VARCHAR(255),
                          city VARCHAR(255),
    store_id INTEGER REFERENCES Store(store_id)
    -- Other employee fields...
);