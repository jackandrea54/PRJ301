create database SE1704
use SE1704

create table admin( admin char(30) primary key,
					password char(32) not null)
--
create table Category(
				cateID int primary key identity(1,1),
				cateName nvarchar(100),
				status int
				)
--
create table Product(
		pid varchar(30) primary key,
		pname nvarchar(200) not null,
		quantity int check(quantity >= 0),
		price money check(price >= 0),
		image varchar(100),
		description nvarchar(max),
		status int,
		cateID int foreign key references Category(cateID)
)
---
create table Customer (
		cid int identity(1,1) primary key,
		cname nvarchar(50) not null,
		username varchar(30) unique,
		password varchar(32) not null,
		address nvarchar(max),
		phone varchar(20),
		status int
)
---
create table Bill(
		bid int identity(1,1) primary key,
		dateCreate dateTime default getDate(),
		recAddress nvarchar(max),
		recPhone varchar(20),
		note nvarchar(max),
		totalMoney money,
		status int,
		cid int foreign key references Customer(cid)
)
--
create table BillDetail(
		bid int foreign key references Bill(bid),
		pid varchar(30) foreign key references Product(pid),
		buyQuantity int,
		buyPrice money,
		subtotal money,
		primary key(bid, pid)
)

