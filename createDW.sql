create database `ELECTRONICA-DW`;

use `ELECTRONICA-DW`;

drop table if exists customers;

drop table if exists products;

drop table if exists stores;

drop table if exists suppliers;

drop table if exists timedimension;

drop table if exists sales;

create table customers(

    CustomerID int primary key,
    CustomerName varchar(100),
    Gender varchar(6)
    
);

create table products(

    ProductID int primary key,
    productName varchar(500),
	productPrice float
    
);

create table stores(

    storeID int primary key,
    storeName varchar(100)
    
);

create table suppliers(

	supplierID int primary key,
    supplierName varchar(150)
    
);

create table timeDimension(

	timeID int primary key auto_increment,
    days int,
    weeks int,
    months int,
    quarterr int,
    years int
);

create table sales(

	salesID int primary key auto_increment,
    CustomerID int,
    ProductID int,
	storeID int,
    supplierID int,
    timeID int,
	QuantityOrdered int,
    revenue float,
    foreign key (CustomerID) references customers(customerID),
	foreign key (ProductID) references products(ProductID),
	foreign key (storeID) references stores(storeID),
	foreign key (supplierID) references suppliers(supplierID),
	foreign key (timeID) references timeDimension(timeID)
    
);


