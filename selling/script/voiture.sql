CREATE TABLE Category(
   id_category VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_category)
);


CREATE TABLE Transmission(
   id_transmission VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_transmission)
);

CREATE TABLE Fuel_type(
   id_fuel_type VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_fuel_type)
);

CREATE TABLE Motorisation(
   id_motorisation VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_motorisation)
);

CREATE TABLE Car_status(
   id_car_status INTEGER,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_car_status)
);

CREATE TABLE Gear_box(
   id_gear_box VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_gear_box)
);

CREATE TABLE gender(
   id integer primary key,
   name VARCHAR(100)
);

CREATE TABLE Users(
   id_users VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   first_name VARCHAR(50) NOT NULL,
   birthdate DATE NOT NULL,
   username VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(50) NOT NULL,
   is_admin BOOLEAN DEFAULT false,
   date_registration DATE DEFAULT NOW(),
   id_gender integer references gender(id),
   PRIMARY KEY(id_users)
);

CREATE TABLE Commission(
   id_commission VARCHAR(50) ,
   boundary_inferior DOUBLE PRECISION,
   boundary_superior DOUBLE PRECISION,
   percentage INTEGER,
   PRIMARY KEY(id_commission)
);

CREATE TABLE profit(
   id_profit VARCHAR(50) ,
   rising DOUBLE PRECISION,
   date_addition DATE,
   id_users VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_profit),
   FOREIGN KEY(id_users) REFERENCES Users(id_users)
);

CREATE TABLE announcement_status (
   id INTEGER PRIMARY KEY,
   name VARCHAR(50)
);


CREATE TABLE Origin(
   id_origin VARCHAR(10) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_origin)
);

CREATE TABLE Location(
   id_location VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_location)
);

CREATE TABLE Brand(
   id_brand VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   id_origin VARCHAR(10),
   PRIMARY KEY(id_brand),
   FOREIGN KEY(id_origin) REFERENCES Origin(id_origin)
);

CREATE TABLE Model(
   id_model VARCHAR(50) ,
   name VARCHAR(50) NOT NULL,
   id_brand VARCHAR(50) ,
   id_category VARCHAR(50) ,
   PRIMARY KEY(id_model),
   FOREIGN KEY(id_brand) REFERENCES Brand(id_brand),
   FOREIGN KEY(id_category) REFERENCES Category(id_category)
);

CREATE TABLE model_gear_box(
   id_model_gear_box SERIAL,
   id_model VARCHAR(50) ,
   id_gear_box VARCHAR(50) ,
   PRIMARY KEY(id_model_gear_box),
   FOREIGN KEY(id_model) REFERENCES Model(id_model),
   FOREIGN KEY(id_gear_box) REFERENCES Gear_box(id_gear_box)
);

CREATE TABLE model_motor(
   id_model_motor SERIAL,
   id_model VARCHAR(50) ,
   id_motorisation VARCHAR(50) ,
   PRIMARY KEY(id_model_motor),
   FOREIGN KEY(id_model) REFERENCES Model(id_model),
   FOREIGN KEY(id_motorisation) REFERENCES Motorisation(id_motorisation)
);

CREATE TABLE model_fuel_type(
   id_model_fuel_type SERIAL,
   id_model VARCHAR(50) ,
   id_fuel_type VARCHAR(50) ,
   PRIMARY KEY(id_model_fuel_type),
   FOREIGN KEY(id_model) REFERENCES Model(id_model),
   FOREIGN KEY(id_fuel_type) REFERENCES Fuel_type(id_fuel_type)
);

CREATE TABLE Car(
   id_car VARCHAR(50),
   door_number INTEGER,
   kilometrage DOUBLE PRECISION ,
   id_model_motor INTEGER,
   id_model_fuel_type INTEGER,
   id_users VARCHAR(50) ,
   id_model VARCHAR(50) ,
   color VARCHAR(50),
   id_car_status INTEGER,
   id_model_gear_box INTEGER,
   id_transmission VARCHAR(50) REFERENCES transmission(id_transmission),
   PRIMARY KEY(id_car),
   FOREIGN KEY(id_model_motor) REFERENCES model_motor(id_model_motor),
   FOREIGN KEY(id_model_fuel_type) REFERENCES model_fuel_type(id_model_fuel_type),
   FOREIGN KEY(id_users) REFERENCES Users(id_users),
   FOREIGN KEY(id_model) REFERENCES Model(id_model),
   FOREIGN KEY(id_car_status) REFERENCES Car_status(id_car_status),
   FOREIGN KEY(id_model_gear_box) REFERENCES model_gear_box(id_model_gear_box)
);

CREATE TABLE Photo(
   id SERIAL,
   picture TEXT NOT NULL,
   id_car VARCHAR(50),
   PRIMARY KEY(id),
   FOREIGN KEY(id_car) REFERENCES Car(id_car)
);


CREATE TABLE Announcement(
   id_announcement VARCHAR(50) ,
   date_addition TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   date_validation DATE,
   status INTEGER NOT NULL REFERENCES announcement_status(id),
   price DOUBLE PRECISION,
   description VARCHAR(255) NOT NULL,
   id_location VARCHAR(50) ,
   id_car VARCHAR(50) ,
   PRIMARY KEY(id_announcement),
   UNIQUE(id_car),
   FOREIGN KEY(id_location) REFERENCES Location(id_location),
   FOREIGN KEY(id_car) REFERENCES Car(id_car)
);

CREATE TABLE Sale(
   id_sale VARCHAR(50) ,
   date_sale DATE,
   price_payed DOUBLE PRECISION ,
   status INTEGER,
   date_validation DATE,
   id_announcement VARCHAR(50) ,
   id_users VARCHAR(50) ,
   PRIMARY KEY(id_sale),
   FOREIGN KEY(id_announcement) REFERENCES Announcement(id_announcement),
   FOREIGN KEY(id_users) REFERENCES Users(id_users)
);

CREATE TABLE Favorite(
   id_favorite VARCHAR(50) ,
   id_users VARCHAR(50) ,
   id_announcement VARCHAR(50) ,
   PRIMARY KEY(id_favorite),
   FOREIGN KEY(id_users) REFERENCES Users(id_users),
   FOREIGN KEY(id_announcement) REFERENCES Announcement(id_announcement)
);

CREATE TABLE fund(
   id_fund VARCHAR(50) ,
   rising DOUBLE PRECISION,
   date_addition DATE,
   id_sale VARCHAR(50) ,
   PRIMARY KEY(id_fund),
   FOREIGN KEY(id_sale) REFERENCES sale(id_sale)
);

CREATE TABLE month (
    id_month INTEGER,
    month VARCHAR(50)
);

ALTER TABLE sale RENAME COLUMN id_users TO id_seller;
