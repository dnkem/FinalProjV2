-- DDL File
create table members (
	member_id	    SERIAL	PRIMARY KEY,
	first_name		VARCHAR(255)		Not Null,
	last_name		VARCHAR(255)		Not Null,
	email		    VARCHAR(255)		Not Null		Unique,
    password_key    VARCHAR(255)		Not Null
);

create table trainers (
	trainer_id		SERIAL		Primary Key,
	first_name		VARCHAR(255)		Not Null,
	last_name		VARCHAR(255)		Not Null,
	email		    VARCHAR(255)		Not Null		Unique,
	password_key	VARCHAR(255)		Not Null
);

create table stats (
	member_id	    SERIAL	PRIMARY KEY,
	weight_kg		Integer		Not Null,
	height_cm		Integer     Not Null,
	heart_rate		Float,
    pull_ups_pb		Integer,
	run_speed		Integer,
    FOREIGN KEY(member_id) REFERENCES members
);

create table goals (
	member_id	    SERIAL	PRIMARY KEY,
	weight_kg		Integer,
	pull_ups_pb		Integer,
	run_speed		Integer,
    FOREIGN KEY(member_id) REFERENCES members
);

create table routine (
	member_id	    SERIAL	PRIMARY KEY,
	item1			VARCHAR(255)		Not Null,
	item2			VARCHAR(255)		Not Null,
	item3			VARCHAR(255)		Not Null,
    FOREIGN KEY(member_id) REFERENCES members
);

create table rooms (
	room_id	        SERIAL	PRIMARY KEY,
	rName		VARCHAR(255)
);

create table booking (
	booking_id		SERIAL		Primary Key,
	room_id		    Integer,
	trainer_id		Integer, 
	booked_date		Date,
	start_time		Time,
    end_time        Time,
	group_indiv		VARCHAR(255)		Not Null,
	attendees		Integer, -- has to be less than 10 for group
	FOREIGN KEY(room_id) REFERENCES rooms,
	Foreign Key(trainer_id) REFERENCES trainers
);

create table equipment (
	equipment_id		SERIAL		Primary Key,
    room_id             Integer,
	eName		    VARCHAR(255)		Not Null,
	eStatus			VARCHAR(255)		Not Null,
    FOREIGN KEY(room_id) REFERENCES rooms
);