CREATE TABLE person(
    id INTEGER AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL, 
    last_name VARCHAR(50) NOT NULL, 
    gender CHAR(1) NOT NULL,
    father_id INTEGER,
    mother_id INTEGER,
    spouse_id INTEGER,
    PRIMARY KEY (id),
    CHECK (gender = ‘f’ OR gender =‘m’),
    FOREIGN KEY(father_id) REFERENCES Person(id),
    FOREIGN KEY(mother_id) REFERENCES Person(id),
    FOREIGN KEY(spouse_id) REFERENCES Person(id)
);

CREATE TABLE user( 
    id INTEGER AUTO_INCREMENT, 
    person_id INTEGER NOT NULL, 
    username VARCHAR(50) NOT NULL, 
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL, 
    PRIMARY KEY (id),
    FOREIGN KEY(person_id) REFERENCES Person(id)
);

CREATE TABLE eventType(
    id INTEGER AUTO_INCREMENT,
    type VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE event(
    id INTEGER AUTO_INCREMENT,
    latitude INTEGER,
    longitude INTEGER,
    country VARCHAR(50),
    city VARCHAR(50),
    event_type_id INTEGER,
    year YEAR(4),
    PRIMARY KEY (id),
    FOREIGN KEY(event_type_id) REFERENCES EventType(id)
);

CREATE TABLE personEvent(
    id INTEGER AUTO_INCREMENT,
    event_id INTEGER NOT NULL,
    person_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(event_id) REFERENCES Event(id),
    FOREIGN KEY(person_id) REFERENCES Person(id)
);

CREATE TABLE authentication(
    id INTEGER AUTO_INCREMENT,
    token VARCHAR(50) NOT NULL UNIQUE,
    user_id INTEGER NOT NULL,
    time_stamp TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES User(id)
);