-- create the database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'hotels')
 BEGIN
     CREATE DATABASE hotels
 END
GO

-- visitors
create table guests (
  id int IDENTITY PRIMARY KEY NOT NULL,
  name nvarchar(40) not null,
  address nvarchar(256),
  post_code varchar(16),
  company nvarchar(256),
  email varchar(128) not null,
  phone varchar(128) not null,
  fax varchar(128) not null,
  name_01 nvarchar(40),
  name_02 nvarchar(40),
  name_03 nvarchar(40),
  name_04 nvarchar(40),
  note nvarchar(80)
);

-- visitor types
create table guest_types (
  id int IDENTITY PRIMARY KEY NOT NULL,
  name nvarchar(40) not null,
  note varchar(40)
);

-- many-to-many mapping for guest and guest types
create table map_guest_types(
  guest_id int not null,
  guest_type_id int not null,
  Constraint UQ_guest_type UNIQUE(guest_id, guest_type_id)
);

create table rooms(
id int IDENTITY NOT NULL Primary Key,
hotel_id int not null,
room_type nvarchar(40) not null,
price nvarchar(40) not null,
booking_state nvarchar(20),
note nvarchar(max),
COnstraint UNIQUE_ID UNIQUE(hotel_id, room_type)
);

-- table for hotel guests for expos
create table expo_hotel_guests (
id int IDENTITY PRIMARY KEY NOT NULL,
expo_id int not null,
hotel_id int not null,
guest_id int not null,
note nvarchar(max),
CONSTRAINT uq UNIQUE(expo_id, hotel_id, guest_id)
);

-- car rental
create table car_renting(
  id int IDENTITY PRIMARY KEY NOT NULL,
  car nvarchar(30) not null,
  usage nvarchar(40) not null,
  price nvarchar(20) not null,
  note nvarchar(128)
);

--tour guiding
create table tour_guiding
(
  id int IDENTITY PRIMARY KEY NOT NULL,
  route nvarchar(40) not null,
  language nvarchar(20) not null,
  price nvarchar(20) not null
)


-- hotel room booking
USE [hotels]
GO

/****** Object:  Table [dbo].[room_booking]    Script Date: 01/17/2014 21:18:23 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[room_booking](
  [id] [int] IDENTITY(1,1) NOT NULL,
  [room_id] [int] NOT NULL,
  [room_count] [tinyint] NOT NULL,
  [guest_id] [int] NOT NULL,
  [checkin_date] datetime NOT NULL,
  [checkout_date] datetime NOT NULL,
  [payment_mode_id] int,
  [note] nvarchar(40)
PRIMARY KEY CLUSTERED 
(
  [id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [UQ_room_guest] UNIQUE NONCLUSTERED 
(
  [room_id] ASC,
  [guest_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

create table payment_modes(
  id int IDENTITY PRIMARY KEY NOT NULL,
  name nvarchar(10) not null,
  CONSTRAINT UQ_name UNIQUE (name)
)

-- booking table for car rental
create table car_booking (
  id int IDENTITY PRIMARY KEY NOT NULL,
  guest_id int not null,
  car_rental_id int not null,
  book_count tinyint not null,
  book_date datetime not null default GETDATE (), 
  note nvarchar(128)
);
-- booking table for tour guiding
create table tour_guiding_bookings (
  [id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
  [guest_id] [int] NOT NULL,
  [tour_guiding_id] [int] NOT NULL,
  [book_count] [tinyint] NOT NULL,
  [book_date] [datetime] NOT NULL default GETDATE(),
  [note] [nvarchar](128) NULL,
  [when_to_use] [datetime] NULL
);

-- users table
create table admin_users (
  id int IDENTITY PRIMARY KEY NOT NULL,
  [user_name] varchar(255) NOT NULL UNIQUE,
  user_password varchar(255) NOT NULL,
  display_name nvarchar(255),
  email varchar(255) NOT NULL,
  created_on datetime NOT NULL DEFAULT GETDATE(),
  note nvarchar(255)
);