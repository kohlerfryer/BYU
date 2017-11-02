CREATE TABLE Person(
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

CREATE TABLE User( 
    id INTEGER AUTO_INCREMENT, 
    person_id INTEGER NOT NULL, 
    username VARCHAR(50) NOT NULL, 
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL, 
    PRIMARY KEY (id)
);

CREATE TABLE Event(
    id INTEGER AUTO_INCREMENT,
    lattitude INTEGER,
    longitude INTEGER,
    country VARCHAR(50),
    city VARCHAR(50),
    year YEAR(4),
    type VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE PersonEvent(
    id INTEGER AUTO_INCREMENT,
    event_id INTEGER NOT NULL,
    person_id INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Authentication(
    id INTEGER AUTO_INCREMENT,
    token VARCHAR(50) NOT NULL UNIQUE,
    user_id INTEGER NOT NULL,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);