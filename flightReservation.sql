CREATE DATABASE RESERVATION
USE RESERVATION

CREATE TABLE USER 
(
ID INT NOT NULL AUTO_INCREMENT,
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
EMAIL VARCHAR(40),
PASSWORD VARCHAR(256), 
PRIMARY KEY (ID),
UNIQUE KEY (EMAIL)
)

CREATE TABLE FLIGHT
(
  ID INT  NOT NULL AUTO_INCREMENT,
  FLIGHT_NUMBER VARCHAR(20)  NOT NULL, 
  OPERATING_AIRLINES VARCHAR(20)  NOT NULL,
  DEPARTURE_CITY VARCHAR(20)  NOT NULL,
  ARRIVAL_CITY VARCHAR(20)  NOT NULL,
  DATE_OF_DEPARTURE DATE  NOT NULL,
  ESTIMATED_DEPARTURE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
  PRIMARY KEY (ID)
)

CREATE TABLE PASSENGER
(
ID INT NOT NULL AUTO_INCREMENT,
FIRST_NAME VARCHAR(256),
LAST_NAME VARCHAR(256),
MIDDLE_NAME VARCHAR(256),
EMAIL VARCHAR(50),
PHONE VARCHAR(10),
PRIMARY KEY (ID)
)

CREATE TABLE IF NOT EXISTS RESERVATION
(
  ID INT NOT NULL AUTO_INCREMENT,
  CHECKED_IN TINYINT(1),
  NUMBER_OF_BAGS INT,
  PASSENGER_ID INT,
  FLIGHT_ID INT,
  CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (PASSENGER_ID) REFERENCES PASSENGER(ID) ON DELETE CASCADE,
  FOREIGN KEY (FLIGHT_ID) REFERENCES FLIGHT(ID)
)

SELECT * FROM USER
SELECT * FROM PASSENGER
SELECT * FROM FLIGHT
SELECT * FROM RESERVATION

DELETE FROM USER

TRUNCATE TABLE USER

DROP TABLE USER
DROP TABLE PASSENGER
DROP TABLE FLIGHT
DROP TABLE RESERVATION

DROP DATABASE RESERVATION

SELECT @@system_time_zone