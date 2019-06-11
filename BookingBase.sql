USE booking;

 -- remove table if it already exists and start from scratch
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Hotels;
DROP TABLE IF EXISTS Rooms;
DROP TABLE IF EXISTS Images;
DROP TABLE IF EXISTS RoomInfo;
DROP TABLE IF EXISTS HotelInfo;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Locations;
 -- remove table if it already exists and start from scratch

create table Accounts (
	account_id int primary key auto_increment,
    username char(64),
    password char(64),
    first_name char(64),
    last_name char(64),
    age decimal(3),
	email char(64)
);

create table Hotels (
	hotel_id int primary key auto_increment,
	name char(64),
    rating char(64),
    img char(64),
    status char(64),
    phone_number char(20),
    account_id int
);

alter table Hotels add constraint fk_account_id foreign key(account_id) references Accounts(account_id);

create table HotelInfo (
	wifi boolean,
    swimmingpool boolean,
    parking boolean,
    beachfront boolean,
    woodfront boolean,
	hotel_id decimal(10)
);


alter table HotelInfo add constraint fk1_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Locations (
	address1 char(64),
	address2 char(64),
	hotel_id decimal(10)
);

alter table Locations add constraint fk2_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Rooms (
	room_id decimal(10) primary key,
    reserved_start date,
    reserved_end date,
    hotel_id decimal(10)
);

alter table Rooms add constraint fk_hotel_id foreign key(hotel_id) references Hotels(hotel_id);


create table RoomInfo (
	room_id decimal(10)
);

alter table RoomInfo add constraint fk_room_id foreign key(room_id) references Rooms(room_id);

create table Reservation (
	reserved_id int primary key auto_increment,
    reserved_from date,
    reserved_to date,
    room_id int
);

alter table Reservation add constraint fk1_room_id foreign key(room_id) references Rooms(room_id);


create table HotelInfo (
	wifi boolean,
    swimmingpool boolean,
    parking boolean,
    beachfront boolean,
    woodfront boolean,
    facility char(64),
	hotel_id int
);


alter table HotelInfo add constraint fk1_hotel_id foreign key(hotel_id) references Hotels(hotel_id);

create table Images (
	id int primary key auto_increment,
    imgfile char(64),
    room_id int
);

alter table Images add constraint fk_img_id foreign key(room_id) references Rooms(room_id);
