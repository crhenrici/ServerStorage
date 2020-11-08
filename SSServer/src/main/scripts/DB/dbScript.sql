-- Creating database
DROP DATABASE IF EXISTS prose;
CREATE DATABASE prose;

USE prose;

-- Creating tables
DROP TABLE IF EXISTS server;
CREATE TABLE server(
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    name varchar(50),
    desc varchar(255),
    full_capacity double,
    storage_reserved double,
    storage_free double,
    storage_ratio double,
    ram int,
    cpu_usage double,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS volume;
CREATE TABLE volume(
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    name varchar(50),
    desc varchar(255),
    server_id int UNSIGNED NOT NULL,
    storage_capacity double,
    latest_storage_used double,
    latest_storage_free double,
    latest_storage_ratio double,
    PRIMARY KEY (id),
    CONSTRAINT `fk_server_volume`
        FOREIGN KEY (server_id) REFERENCES server (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS volumes;
CREATE TABLE volumes(
  id int UNSIGNED NOT NULL AUTO_INCREMENT,
  volume_id int UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT `fk_volume_volumes`
        FOREIGN KEY (volume_id) REFERENCES volume (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS volume_history;
CREATE TABLE volume_history(
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    volume_id int UNSIGNED NOT NULL,
    storage_free double,
    storage_used double,
    storage_ratio double,
    PRIMARY KEY (id),
    CONSTRAINT `fk_volume_volume_history`
        FOREIGN KEY (volume_id) REFERENCES volume (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS server_history;
CREATE TABLE server_history(
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
	server_id int UNSIGNED NOT NULL,
	storage_free double,
	storage_reserved double,
	storage_ratio double,
	PRIMARY KEY (id),
	CONSTRAINT `fk_server_server_history`
		FOREIGN KEY (server_id) REFERENCES server (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

-- Create User
DROP USER IF EXISTS 'dbUser'@localhost;
CREATE USER 'dbUser'@localhost IDENTIFIED BY 'Somepassword';

-- DROP USER IF EXISTS 'dbUser'@'%';
-- CREATE USER 'dbUser'@'%' IDENTIFIED BY 'Somepassword';

-- Grant privileges
GRANT USAGE ON 'prose'.* TO 'dbUser'@localhost IDENTIFIED BY 'Somepassword';
GRANT SELECT ON 'prose'.* TO 'dbUser'@localhost IDENTIFIED BY 'Somepassword';

-- GRANT USAGE ON 'prose'.* TO 'dbUser'@'%' IDENTIFIED BY 'Somepassword';
-- GRANT SELECT ON 'prose'.* TO 'dbUser'@'%' IDENTIFIED BY 'Somepassword';