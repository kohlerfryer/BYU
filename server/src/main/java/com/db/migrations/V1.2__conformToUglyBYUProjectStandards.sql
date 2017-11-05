DROP TABLE authentication;
DROP TABLE user;
DROP TABLE personEvent;
DROP TABLE event;
DROP TABLE eventType;
DROP TABLE person;

CREATE TABLE person(
    id INTEGER AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL, 
    last_name VARCHAR(50) NOT NULL, 
    gender CHAR(1) NOT NULL,
    father_id INTEGER,
    mother_id INTEGER,
    spouse_id INTEGER,
    PRIMARY KEY (id),
    CHECK (gender = ‘f’ OR gender =‘m’)
);

CREATE TABLE user( 
    id INTEGER AUTO_INCREMENT, 
    person_id INTEGER NOT NULL, 
    username VARCHAR(50) NOT NULL, 
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL, 
    PRIMARY KEY (id)
);

CREATE TABLE event(
    id INTEGER AUTO_INCREMENT,
    latitude INTEGER,
    longitude INTEGER,
    country VARCHAR(50),
    city VARCHAR(50),
    year INTEGER,
    person_id INTEGER NOT NULL,
    type VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE authentication(
    id INTEGER AUTO_INCREMENT,
    token VARCHAR(50) NOT NULL UNIQUE,
    user_id INTEGER NOT NULL,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);