-- Computer Science II
-- Assignment 4.0
--
-- Name(s):  Immanuel Soh
--           isoh2@huskers.unl.edu
--
-- Date: 2024/03/29
--
-- This is a database for YRLess' new test database for its sales management system
--


drop table if exists Email;
drop table if exists SaleItem;
drop table if exists Sale;
drop table if exists Store;
drop table if exists Person;
drop table if exists Item;
drop table if exists Address;
drop table if exists Zip;
drop table if exists State;

create table State (
  stateId int not null primary key auto_increment,
  state varchar(200) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Zip (
  zipId int not null primary key auto_increment,
  zip varchar(200) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Address (
  addressId int not null primary key auto_increment,
  street varchar(200) not null,
  city varchar(200) not null,
  stateId int not null,
  zipId int not null,
  foreign key (stateId) references State(stateId),
  foreign key (zipId) references Zip(zipId)
)engine=InnoDB,collate=latin1_general_cs;

create table Item (
  itemId int not null primary key auto_increment,
  code varchar(200) not null,
  type varchar(200) not null,
  name varchar(200) not null,
  baseCost double not null
)engine=InnoDB,collate=latin1_general_cs;

create table Person (
  personId int not null primary key auto_increment,
  uuid varchar(200) not null,
  firstName varchar(200) not null,
  lastName varchar(200) not null,
  addressId int not null,
  foreign key (addressId) references Address(addressId)
)engine=InnoDB,collate=latin1_general_cs;

create table Store (
  storeId int not null primary key auto_increment,
  storeCode varchar(200) not null,
  personId int not null,
  addressId int not null,
  foreign key (personId) references Person(personId),
  foreign key (addressId) references Address(addressId)
)engine=InnoDB,collate=latin1_general_cs;

create table Sale (
  saleId int not null primary key auto_increment,
  storeId int not null,
  customerId int not null,
  employeeId int not null,
  saleCode varchar(200) not null,
  saleDate varchar(200) not null,
  foreign key (storeId) references Store(storeId),
  foreign key (customerId) references Person(personId),
  foreign key (employeeId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table SaleItem (
  saleitemId int not null primary key auto_increment,
  serviceHours double,
  employeeID int,
  beginningLeaseDate varchar(200),
  endingLeaseDate varchar(200),
  boughtGB double default 0.00,
  vpDays int,
  vpNumber varchar(200),
  itemId int not null,
  saleId int not null,
  foreign key (itemId) references Item(itemId),
  foreign key (employeeID) references Person(personId),
  foreign key (saleId) references Sale (saleId)
)engine=InnoDB,collate=latin1_general_cs;

create table Email (
  emailId int not null primary key auto_increment,
  personId int not null,
  emailAddress varchar(255) not null,
  foreign key (personId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;


INSERT INTO State (stateId, state) VALUES ('1', 'CT');
INSERT INTO State (stateId, state) VALUES ('2', 'PA');
INSERT INTO State (stateId, state) VALUES ('3', 'OH');
INSERT INTO State (stateId, state) VALUES ('4', 'GA');
INSERT INTO State (stateId, state) VALUES ('5', 'TX');
INSERT INTO State (stateId, state) VALUES ('6', 'CA');

INSERT INTO Zip (zipId, zip) VALUES ('1', '06606');
INSERT INTO Zip (zipId, zip) VALUES ('2', '16510');
INSERT INTO Zip (zipId, zip) VALUES ('3', '44125');
INSERT INTO Zip (zipId, zip) VALUES ('4', '30033');
INSERT INTO Zip (zipId, zip) VALUES ('5', '30343');
INSERT INTO Zip (zipId, zip) VALUES ('6', '76016');
INSERT INTO Zip (zipId, zip) VALUES ('7', '94089');
INSERT INTO Zip (zipId, zip) VALUES ('8', '93386');

INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('1', '166 Gateway Alley', 'Bridgeport', '1', '1');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('2', '266 Becker Way', 'Erie', '2', '2');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('3', '86 8th Alley', 'Cleveland', '3', '3');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('4', '5420 Dryden Drive', 'Decatur', '4', '4');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('5', '4 Katie Parkway', 'Atlanta', '4', '5');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('6', '085 Esch Lane', 'Arlington', '5', '6');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('7', '6750 Mallard Plaza', 'Sunnyvale', '6', '7');
INSERT INTO Address (addressId,  street,  city,  stateId,  zipId) VALUES ('8', '1 Hauk Hill', 'Bakersfield', '6', '8');

INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('1', 'e001', 'P', 'Nokia Drop', '69.99');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('2', 'e002', 'P', 'iPhone L', '12999.99');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('3', 'e003', 'P', 'Potato Spudphone', '0.69');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('4', 's001', 'S', 'SIM Card Transplant', '199.99');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('5', 's002', 'S', 'Instant Delivery', '59.99');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('6', 's003', 'S', 'Neural Interface Setup', '29.99');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('7', 'p001', 'D', '6G Basic', '4.99');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('8', 'p002', 'D', '42G Meaning', '42.42');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('9', 'p003', 'V', 'Unlimited + Ads', '10.0');
INSERT INTO Item (itemId,  code,  type,  name,  baseCost) VALUES ('10', 'p004', 'V', 'Unlimited No Ads', '30.0');

INSERT INTO Person (personId,  uuid,  firstName,  lastName,  addressId) VALUES ('1', 'cacf478a-ad64-464b-a25d-b648575c6302', 'Lilly', 'Wattinham', '1');
INSERT INTO Person (personId,  uuid,  firstName,  lastName,  addressId) VALUES ('2', '645ecf63-3937-404f-8fd0-ef239b0fdc6a', 'Torrin', 'Cavolini', '2');
INSERT INTO Person (personId,  uuid,  firstName,  lastName,  addressId) VALUES ('3', 'de31c87f-627d-45c2-91af-5f794b1a92ad', 'Bartholomeo', 'Stairmond', '3');
INSERT INTO Person (personId,  uuid,  firstName,  lastName,  addressId) VALUES ('4', 'b812569c-5996-4d46-8a04-d8644ba57e34', 'Scot', 'Stairmond', '4');
INSERT INTO Person (personId,  uuid,  firstName,  lastName,  addressId) VALUES ('5', '98036273-8126-4173-a345-684a99746cf5', 'Damiano', 'Kitter', '5');

INSERT INTO Store (storeId,  storeCode, personId, addressId) VALUES ('1', 'onr62v', '2', '6');
INSERT INTO Store (storeId,  storeCode, personId, addressId) VALUES ('2', 'pf4fee', '3', '7');
INSERT INTO Store (storeId,  storeCode, personId, addressId) VALUES ('3', 'gtpoq2', '4', '8');

INSERT INTO Sale (saleId, storeId, customerId, employeeId, saleCode, saleDate) VALUES ('1', '1', '1', '2', 's001', '2024-02-28');
INSERT INTO Sale (saleId, storeId, customerId, employeeId, saleCode, saleDate) VALUES ('2', '2', '1', '3', 's002', '2024-02-28');
INSERT INTO Sale (saleId, storeId, customerId, employeeId, saleCode, saleDate) VALUES ('3', '1', '1', '2', 's003', '2024-02-29');

INSERT INTO SaleItem (saleitemId, serviceHours, employeeID, beginningLeaseDate, endingLeaseDate, boughtGB, vpDays, vpNumber, itemId, saleId) VALUES ('1', '0.0', null, null, null, '0.0', '0', null, '3', '1');
INSERT INTO SaleItem (saleitemId, serviceHours, employeeID, beginningLeaseDate, endingLeaseDate, boughtGB, vpDays, vpNumber, itemId, saleId) VALUES ('2', '3.0', '2', null, null, '0.0', '0', null, '6', '1');
INSERT INTO SaleItem (saleitemId, serviceHours, employeeID, beginningLeaseDate, endingLeaseDate, boughtGB, vpDays, vpNumber, itemId, saleId) VALUES ('3', '0.0', null, '2024-02-28', '2025-02-28', '0.0', '0', null, '2', '2');
INSERT INTO SaleItem (saleitemId, serviceHours, employeeID, beginningLeaseDate, endingLeaseDate, boughtGB, vpDays, vpNumber, itemId, saleId) VALUES ('4', '0.0', null, null, null, '100.0', '0', null, '8', '2');
INSERT INTO SaleItem (saleitemId, serviceHours, employeeID, beginningLeaseDate, endingLeaseDate, boughtGB, vpDays, vpNumber, itemId, saleId) VALUES ('5', '0.0', null, null, null, '0.0', '0', null, '1', '3');
INSERT INTO SaleItem (saleitemId, serviceHours, employeeID, beginningLeaseDate, endingLeaseDate, boughtGB, vpDays, vpNumber, itemId, saleId) VALUES ('6', '0.0', null, null, null, '0.0', '300', '123-456-7890', '10', '3');

INSERT INTO Email (emailId, personId, emailAddress) VALUES ('1', '1', 'lwattinham0@purevolume.com');
INSERT INTO Email (emailId, personId, emailAddress) VALUES ('2', '2', 'tcavolini1@archive.org');
INSERT INTO Email (emailId, personId, emailAddress) VALUES ('3', '3', 'bstairmond2@telegraph.co.uk');
INSERT INTO Email (emailId, personId, emailAddress) VALUES ('4', '4', 'sbenoist3@upenn.edu');
INSERT INTO Email (emailId, personId, emailAddress) VALUES ('5', '5', 'dkitter4@etsy.com');
INSERT INTO Email (emailId, personId, emailAddress) VALUES ('6', '5', 'dakitten@battlekatz.net');