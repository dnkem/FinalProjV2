-- DML file

-- Populate Trainers Table
INSERT INTO trainers (first_name, last_name, email, password_key)
VALUES 
('Joe', 'Mams', 'J@', '123'),
('Shelly', 'Perks', 'She@', '321'),
('Moon', 'Fa', 'Cup@', 'lol');

-- Populate Rooms Table
INSERT INTO rooms (rName)
VALUES 
('Pilates Space'),
('Gym Base'),
('Yoga Room');

-- Populate Equipment Table
INSERT INTO equipment (room_id, eName, eStatus)
VALUES 
('1', 'Treadmill', 'clean'),
('1', 'Barbell', 'clean'),
('1', 'Row Machine', 'clean'),
('1', 'Pull Up Bars', 'OUT OF ORDER'),
('2', 'Yoga Ball', 'dirty'),
('2', 'Yoga Mat', 'dirty'),
('2', 'Jump Rope', 'clean'),
('2', 'Foam Roller', 'clean'),
('3', 'Pilates Map', 'OUT OF ORDER'),
('3', 'Resistance Band', 'dirty'),
('3', 'Ladder Barrel', 'clean'),
('3', 'Trapeze Table', 'OUT OF ORDER');

-- Populate Members Table
INSERT INTO members (first_name, last_name, password_key, email)
VALUES 
('Jesse', 'Wayne', 'sportsHero', 'jessway@gmail.com'),
('The', 'Rock', 'jOhNs', 'RockJ@gmail.com'),
('Dwayne', 'Wayne', '0nePiece', 'DwWayne@gmail.com');

-- Populate Equipment Table
INSERT INTO stats (member_id, weight_kg, height_cm, heart_rate, pull_ups_pb, run_speed)
VALUES 
(1, 40, 156, 180.0, 0, 3),
(2, 39, 160, 167.0, 3, 6),
(3, 70, 177, 170.0, 1, 6);

-- Populate Equipment Table
INSERT INTO goals (member_id, weight_kg, pull_ups_pb, run_speed)
VALUES 
(1, 47, 15, 10),
(2, 50, 10, 8),
(3, 75, 10, 9);

-- Populate routine table
INSERT INTO routine (member_id, item1, item2, item3)
VALUES 
(1,'stretch', 'yoga', 'cardio'),
(2,'run', 'push ups', 'cool down'),
(3,'weight lift', 'pilates', 'barbell swings');