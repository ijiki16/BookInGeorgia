USE booking;

 -- remove table if it already exists and start from scratch
DROP TABLE IF EXISTS RoomInfo;
DROP TABLE IF EXISTS HotelInfo;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Locations;
DROP TABLE IF EXISTS Images;
DROP TABLE IF EXISTS Rooms;
DROP TABLE IF EXISTS Hotels;
DROP TABLE IF EXISTS Accounts;
 -- remove table if it already exists and start from scratch

-- selects
-- select * from Accounts;
--
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
	wifi boolean,
    parking boolean,
    beachfront boolean,
    woodfront boolean,
	hotel_id int
);

alter table HotelInfo add constraint fk1_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Locations (
	address1 char(128),
	address2 char(128),
	hotel_id int
);

alter table Locations add constraint fk2_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Rooms (
	room_id int primary key auto_increment,
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
    room_id int
);

alter table Reservation add constraint fk1_room_id foreign key(room_id) references Rooms(room_id);

create table Images (
	id int primary key auto_increment,
    imgfile char(128),
    room_id int
);

alter table Images add constraint fk_img_id foreign key(room_id) references Rooms(room_id);
