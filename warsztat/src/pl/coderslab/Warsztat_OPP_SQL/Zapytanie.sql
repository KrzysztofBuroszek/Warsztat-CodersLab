CREATE DATABASE workshop2;
USE workshop2;

# +------------------+--------------+------+-----+---------+----------------+
# | Field            | Type         | Null | Key | Default | Extra          |
# +------------------+--------------+------+-----+---------+----------------+
# | id               | int(11)      | NO   | PRI | NULL    | auto_increment |
# | email            | varchar(255) | NO   | UNI | NULL    |                |
# | username         | varchar(255) | NO   |     | NULL    |                |
# | password         | varchar(60)  | NO   |     | NULL    |                |
# +------------------+--------------+------+-----+---------+----------------+
# dodawanie użytkownika,
# zmiana danych,
# pobieranie po id,
# usuwanie po id,
# pobieranie wszystkich użytkowników.

CREATE TABLE users (
    id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL  UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
DESCRIBE users;

-- Zapytanie:
# dodawanie użytkownika,
INSERT INTO users (email, username, password) VALUES ('aburoszek@gmial.com', 'Anna', 'Hasło');
# zmiana danych,
UPDATE users SET username= 'Anna' WHERE id = 1;
# pobieranie po id,
SELECT * FROM users WHERE id = 1;
# usuwanie po id,
DELETE FROM users WHERE id=1;
# pobieranie wszystkich użytkowników.
SELECT *FROM users;
ALTER TABLE users ADD userName VARCHAR(255) not null;
SELECT count(*) FROM users;

UPDATE users SET id = 3 and email = 'kk' AND userName= 'MAgda' AND password = 'ddddd' WHERE id = 0;