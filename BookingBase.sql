USE booking;

 -- remove table if it already exists and start from scratch
DROP TABLE IF EXISTS RoomInfo;
DROP TABLE IF EXISTS HotelInfo;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Locations;
DROP TABLE IF EXISTS Rooms;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS Hotels;
DROP TABLE IF EXISTS Accounts;
 -- remove table if it already exists and start from scratch

-- selects
-- select * from Accounts;
-- select * from Hotels;
-- select * from Locations;
-- select * from HotelInfo;
-- select * from Rooms;
--

-- insert into Hotels (name, rating, img, status, phone_number, account_id) values("hotel", 5, "none", "new hotel", "551511300", 1);
-- insert into Locations (city, address, hotel_id) values ("Tbilisi", "Vaja", 1);
-- insert into Hotels (name, rating, img, status, phone_number, account_id) values("hotel1", 4, "none", "new hotel1", "551511300", 1);
-- insert into Hotels (name, rating, img, status, phone_number, account_id) values("hotel2", 3, "none", "new hotel2", "551511300", 1);
-- insert into Hotels (name, rating, img, status, phone_number, account_id) values("hotel3", 2, "none", "new hotel3", "551511300", 1);
-- insert into Hotels (name, rating, img, status, phone_number, account_id) values("hotel4", 1, "none", "new hotel4", "551511300", 1);

create table Accounts (
	account_id int primary key auto_increment,
    username char(128),
    password char(128),
    first_name char(128),
    last_name char(128),
    birth_date date,
	email char(128)
);

create table Hotels (
	hotel_id int primary key auto_increment,
	name char(128),
    rating int,
    img char(128),
    status char(128),
    phone_number char(20),
    account_id int
);

alter table Hotels add constraint fk_account_id foreign key(account_id) references Accounts(account_id);

create table HotelInfo (
    facility char(128),
    wifi boolean,
    parking boolean,
    beachfront boolean,
    woodfront boolean,
	hotel_id int
);

alter table HotelInfo add constraint fk1_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Locations (
	city char(128),
	address char(128),
	hotel_id int
);

alter table Locations add constraint fk2_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Rooms (
	room_id int primary key auto_increment,
	price_per_day int,
    img char(128),
    reserved_start date,
    reserved_end date,
    number_of_beds int,
    hotel_id int
);

alter table Rooms add constraint fk_hotel_id foreign key(hotel_id) references Hotels(hotel_id);


create table RoomInfo (
	wifi boolean,
	tv boolean,
	hot_water boolean,
	air_conditioning boolean,
	room_id int
);

alter table RoomInfo add constraint fk_room_id foreign key(room_id) references Rooms(room_id);

create table Reservation (
	reserved_id int primary key auto_increment,
    reserved_from date,
    reserved_to date,
    room_id int,
    account_id int
);

alter table Reservation add constraint fk1_room_id foreign key(room_id) references Rooms(room_id);
alter table Reservation add constraint fk2_room_id foreign key(account_id) references  Accounts(account_id);

create table comments (
    hotel_id int,
    username char(128),
    comm char(255)
);

alter table comments add constraint frk_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

# add data in DB

-- insert into Hotels (name, rating, img, status, phone_number, account_id) values("hotel4", 1, "none", "new hotel4", "551511300", 1);
insert into Accounts (username, password, first_name, last_name, birth_date, email )
values ('iuri', '3859edd5b535a5258468fe518cb465fec14bc422eee7c28aee3361a166bee9e5e285ed5c16ca85c3c00794728a8c7c01116abbf8b35bb7abb8a651954a37da4f
', 'iuri', 'jikidze','1999-08-07', 'ijiki');
insert into Accounts (username, password, first_name, last_name, birth_date, email )
values ('devi', '19853b9c8296484b3c5180e447e7ddb9b3d6dde5a2f096e2a7c74fb4b7048b4315556180197cdb64b14b2d93320458a7bb17e248f29df81f52161295c28a2995
', 'devi', 'khositashvili','1999-08-07', 'dkhos');
insert into Accounts (username, password, first_name, last_name, birth_date, email )
values ('sandro', 'be20b1249e32cbcdd1f1c4e9bebaf35942fd4f200faa787814704e120af198a601f25ec420dd0b0953996d035607f70f72494c441f8c3986b2213c133363c314
', 'sandro', 'pertaia','1999-08-07', 'spert');
insert into Accounts (username, password, first_name, last_name, birth_date, email )
values ('nika', 'd5dba73a71de478751783a255e9092bf5a7e7ed9001386e623aad06c812efb4d3447e1fb648c0dcab0e7a892d94f87701f44069ed08e2c0b3561d587d3e8e146
', 'nika', 'basiashvili','1999-08-07', 'nbasi');

# Hotel Rooms
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('Rooms', 5, 'images/roomsH.jpg', 'good', '032 202 00 99', (select account_id from Accounts where email = 'ijiki'));

insert into Locations (city, address, hotel_id)
VALUES ('Tbilisi', 'Kostava St', (select hotel_id from Hotels where name = 'Rooms'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, false, false, (select hotel_id from Hotels where name = 'Rooms'));

# Radisson Blu Iveria
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('Radisson Blu', 4, 'images/Radisson.jpg', 'good', '+995 32 240 22 00', (select account_id from Accounts where email = 'ijiki'));

insert into Locations (city, address, hotel_id)
VALUES ('Tbilisi', 'Rose Revolution Square', (select hotel_id from Hotels where name = 'Radisson Blu'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, false, true, (select hotel_id from Hotels where name = 'Radisson Blu'));

# Fabrika Tbilisi
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('Fabrika Tbilisi', 5, 'images/Fabrika.jpg', '', '+995 32 202 03 99', (select account_id from Accounts where email = 'spert'));

insert into Locations (city, address, hotel_id)
VALUES ('Tbilisi', 'Egnate Ninoshvili St', (select hotel_id from Hotels where name = 'Fabrika Tbilisi'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, false, false, (select hotel_id from Hotels where name = 'Fabrika Tbilisi'));

# Tbilisi Marriott Hotel
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('Tbilisi Marriott', 5, 'images/Marriott.jpg', '', '+995 32 277 92 00', (select account_id from Accounts where email = 'dkhos'));

insert into Locations (city, address, hotel_id)
VALUES ('Tbilisi', 'Shota Rustaveli Ave', (select hotel_id from Hotels where name = 'Tbilisi Marriott'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, false, false, (select hotel_id from Hotels where name = 'Tbilisi Marriott'));

# The Biltmore Hotel Tbilisi
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('The Biltmore', 3, 'images/Biltmore.jpg', '', '+995 32 272 72 72', (select account_id from Accounts where email = 'nbasi'));

insert into Locations (city, address, hotel_id)
VALUES ('Tbilisi', 'Shota Rustaveli Ave', (select hotel_id from Hotels where name = 'The Biltmore'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, false, false, (select hotel_id from Hotels where name = 'The Biltmore'));

# Panorama 360°
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('Panorama 360°', 2, 'images/Panorama.jpg', '', '+995 571 92 28 88', (select account_id from Accounts where email = 'nbasi'));

insert into Locations (city, address, hotel_id)
VALUES ('Tbilisi', 'Merab Kostava St', (select hotel_id from Hotels where name = 'Panorama 360°'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, true, true, (select hotel_id from Hotels where name = 'Panorama 360°'));

# Sheraton Batumi
insert into Hotels (name, rating, img, status, phone_number, account_id)
values('Sheraton Batumi', 5, 'images/Sheraton.jpg', '', '0422 22 90 00', (select account_id from Accounts where email = 'nbasi'));

insert into Locations (city, address, hotel_id)
VALUES ('Batumi', 'Rustaveli St', (select hotel_id from Hotels where name = 'Sheraton Batumi'));

insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id)
VALUES ('', true, true, true, true, (select hotel_id from Hotels where name = 'Sheraton Batumi'));

# Hotel Rooms rooms
insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (94, 'images/rms1.jpg', '2019-05-07', '2022-10-23', 2, (select hotel_id from Hotels where name = 'Rooms'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, true, (select room_id from Rooms where img = 'images/rms1.jpg'));

insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (116, 'images/rms2.jfif', '2018-05-07', '2020-11-17', 2, (select hotel_id from Hotels where name = 'Rooms'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, true, true, true, (select room_id from Rooms where img = 'images/rms2.jfif'));

# Radisson rooms
insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (135, 'images/blu1.jpg', '2017-11-07', '2019-12-26', 2, (select hotel_id from Hotels where name = 'Radisson Blu'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, false, (select room_id from Rooms where img = 'images/blu1.jpg'));

insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (170, 'images/blu2.jpg', '2017-12-30', '2020-01-05', 4, (select hotel_id from Hotels where name = 'Radisson Blu'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, true, true, true, (select room_id from Rooms where img = 'images/blu2.jpg'));


# Marriot rooms
insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (190, 'images/marriot1.jpg', '2017-11-07', '2019-12-26', 2, (select hotel_id from Hotels where name = 'Tbilisi Marriott'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, false, (select room_id from Rooms where img = 'images/marriot1.jpg'));

insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (230, 'images/marriot2.jpg', '2017-12-30', '2020-01-05', 4, (select hotel_id from Hotels where name = 'Tbilisi Marriott'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, true, true, true, (select room_id from Rooms where img = 'images/marriot2.jpg'));

# Fabrika rooms
insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (90, 'images/fab1.jpg', '2016-07-07', '2020-03-26', 2, (select hotel_id from Hotels where name = 'Fabrika Tbilisi'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, false, (select room_id from Rooms where img = 'images/fab1.jpg'));

insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (50, 'images/fab2.jpg', '2016-07-30', '2020-03-05', 12, (select hotel_id from Hotels where name = 'Fabrika Tbilisi'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, true, (select room_id from Rooms where img = 'images/fab2.jpg'));


# The Biltmore rooms
insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (320, 'images/bilt1.jpg', '2015-06-07', '2020-03-26', 2, (select hotel_id from Hotels where name = 'The Biltmore'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, false, (select room_id from Rooms where img = 'images/bilt1.jpg'));

insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (350, 'images/bilt2.jpg', '2015-06-30', '2020-03-05', 2, (select hotel_id from Hotels where name = 'The Biltmore'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, true, (select room_id from Rooms where img = 'images/bilt2.jpg'));


# Panorama 360° rooms
insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (15, 'images/pano1.jpg', '2015-06-07', '2020-03-26', 2, (select hotel_id from Hotels where name = 'Panorama 360°'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, false, (select room_id from Rooms where img = 'images/pano1.jpg'));

insert into Rooms (price_per_day, img, reserved_start, reserved_end, number_of_beds, hotel_id)
VALUES (30, 'images/pano2.jfif', '2015-06-30', '2020-03-05', 2, (select hotel_id from Hotels where name = 'Panorama 360°'));

insert into RoomInfo (wifi, tv, hot_water, air_conditioning, room_id)
VALUES (true, false, true, true, (select room_id from Rooms where img = 'images/pano2.jfif'));