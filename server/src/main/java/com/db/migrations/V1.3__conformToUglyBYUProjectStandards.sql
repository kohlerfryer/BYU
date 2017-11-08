ALTER TABLE person MODIFY id VARCHAR(250);
ALTER TABLE person MODIFY first_name TEXT;
ALTER TABLE person MODIFY last_name TEXT;
ALTER TABLE person MODIFY gender TEXT;
ALTER TABLE person MODIFY father_id TEXT;
ALTER TABLE person MODIFY mother_id TEXT;
ALTER TABLE person MODIFY spouse_id TEXT;
ALTER TABLE person ADD descendant TEXT;

ALTER TABLE user DROP id;
ALTER TABLE user DROP person_id;
ALTER TABLE user MODIFY username VARCHAR(250) PRIMARY KEY;
ALTER TABLE user MODIFY email TEXT;
ALTER TABLE user MODIFY password TEXT;
ALTER TABLE user ADD first_name TEXT;
ALTER TABLE user ADD last_name TEXT;
ALTER TABLE user ADD gender TEXT;
ALTER TABLE user ADD person_id TEXT;

ALTER TABLE event MODIFY id VARCHAR(250);
ALTER TABLE event MODIFY latitude REAL;
ALTER TABLE event MODIFY longitude REAL;
ALTER TABLE event MODIFY country TEXT;
ALTER TABLE event MODIFY city TEXT;
ALTER TABLE event MODIFY year TEXT;
ALTER TABLE event MODIFY person_id TEXT;
ALTER TABLE event MODIFY type TEXT;
ALTER TABLE event ADD descendant TEXT;

ALTER TABLE authentication MODIFY id VARCHAR(250);
ALTER TABLE authentication MODIFY user_id TEXT;
ALTER TABLE authentication MODIFY time_stamp TEXT;
ALTER TABLE authentication DROP INDEX token;
ALTER TABLE authentication MODIFY token TEXT;
