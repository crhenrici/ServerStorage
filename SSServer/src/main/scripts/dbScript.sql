--Creating database
DROP DATABASE IF EXISTS prose;
CREATE DATABASE prose;

--Creating tables
DROP TABLE IF EXISTS server;
CREATE TABLE server(
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    name varchar(50),
    ram int,
    cpu_usage int,
);

DROP TABLE IF EXISTS volume;
CREATE TABLE volume(
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    server_id int UNSIGNED NOT NULL,
    storage_capacity int,
    latest_storage_used int,
    latest_storage_free int,
    CONSTRAINT `fk_server_volume`
        FOREIGN KEY (server_id) REFERENCES server (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS volume_history;
CREATE TABLE volume_history(
    volume_id int UNSIGNED NOT NULL,
    storage_free int,
    storage_used int,
    CONSTRAINT `fk_volume_volume_history`
        FOREIGN KEY (volume_id) REFERENCES volume (id)
        ON DELETE CASCADE,
        ON UPDATE RESTRICT
);

--Create User
DROP USER IF EXISTS 'dbUser'@localhost;
CREATE USER 'dbUser'@localhost IDENTIFIED BY 'Somepassword';

--DROP USER IF EXISTS 'dbUser'@'%';
--CREATE USER 'dbUser'@'%' IDENTIFIED BY 'Somepassword';

--Grant privileges
GRANT USAGE ON 'prose'.* TO 'dbUser'@localhost IDENTIFIED BY 'Somepassword';
GRANT SELECT ON 'prose'.* T0 'dbUser'@localhost IDENTIFIED BY 'Somepassword';

--GRANT USAGE ON 'prose'.* TO 'dbUser'@'%' IDENTIFIED BY 'Somepassword';
--GRANT SELECT ON 'prose'.* T0 'dbUser'@'%' IDENTIFIED BY 'Somepassword';