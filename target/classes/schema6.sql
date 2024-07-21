CREATE TABLE IF NOT EXISTS users
(
    user_id varchar(100) NOT NULL,
    common_name varchar(100) NOT NULL ,
    user_status varchar(10) NOT NULL,
    lock_status varchar(10),
    carriers varchar(100),
    entitlements varchar(2000) NOT NULL,
    registered_date varchar(100) NOT NULL,
    modified_date timestamp NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);
CREATE INDEX modifytime_index IF NOT EXISTS ON users (modified_date ASC NULLS LAST);
CREATE TABLE IF NOT EXISTS user_setting
(
       account_id varchar(100) NOT NULL,
       settings_id varchar(100) NOT NULL,
       settings_value varchar(100) NOT NULL,
       created_on timestamp  NOT NULL DEFAULT (now() AT TIME ZONE 'UTC'),
       CONSTRAINT user_setting_pkey PRIMARY KEY (account_id, settings_id)
);