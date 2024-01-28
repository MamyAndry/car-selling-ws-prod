-- Inserting data into Category table
INSERT INTO Category (id_category, name) VALUES
('CAT0001', 'Sedan'),
('CAT0002', 'SUV'),
('CAT0003', 'Hatchback');

INSERT INTO announcement_status(id, name) VALUES
(1, 'Unpublished'),
(10, 'Published'),
(20, 'Sold'),
(30, 'Rejected');

INSERT INTO gender (id, name) VALUES
(0, 'Femme'),
(1, 'Homme');

INSERT INTO location (id_location, name) VALUES
('LOC0001', 'Antananarivo'),
('LOC0002', 'Toamasina'),
('LOC0003', 'Antsirabe' );

INSERT INTO origin(id_origin, name) VALUES
('ORG0001', 'German'),
('ORG0002', 'French'),
('ORG0003', 'Spanish'),
('ORG0004', 'Japanese'),
('ORG0005', 'American'),
('ORG0006', 'Italian');
-- Inserting data into Brand table
INSERT INTO Brand (id_brand, name, id_origin) VALUES
('BRD0001', 'Toyota', 'ORG0004'),
('BRD0002', 'Ford', 'ORG0005'),
('BRD0003', 'Honda', 'ORG0004');

-- Inserting data into Fuel_type table
INSERT INTO Fuel_type (id_fuel_type, name) VALUES
('FUT0001', 'Petrol'),
('FUT0002', 'Diesel'),
('FUT0003', 'Electric');

-- Inserting data into Motorisation table
INSERT INTO Motorisation (id_motorisation, name) VALUES
('MOT0001', 'Automatic'),
('MOT0002', 'Manual'),
('MOT0003', 'Hybrid');

-- Inserting additional data into Model table
INSERT INTO Model (id_model, name, id_brand, id_category) VALUES
('MDL0001', 'Accord', 'BRD0003', 'CAT0001'),
('MDL0002', 'F-150', 'BRD0002', 'CAT0002'),
('MDL0003', 'Corolla', 'BRD0001', 'CAT0001'),
('MDL0004', 'Civic Type R', 'BRD0003', 'CAT0003'),
('MDL0005', 'Escape', 'BRD0002', 'CAT0002');

-- Inserting data into Gear_box table
INSERT INTO Gear_box (id_gear_box, name) VALUES
('GRB0001', 'Automatic'),
('GRB0002', 'Manual'),
('GRB0003', 'CVT'),
('GRB0004', 'DCT'),
('GRB0005', 'Tiptronic');

-- Inserting data into model_gear_box table
INSERT INTO model_gear_box (id_model_gear_box, id_model, id_gear_box) VALUES
(default, 'MDL0001', 'GRB0001'),
(default, 'MDL0002', 'GRB0002'),
(default, 'MDL0003', 'GRB0003'),
(default, 'MDL0004', 'GRB0004'),
(default, 'MDL0005', 'GRB0005');

-- Inserting data into model_motor table
INSERT INTO model_motor (id_model_motor, id_model, id_motorisation) VALUES
(default, 'MDL0001', 'MOT0001'),
(default, 'MDL0002', 'MOT0002'),
(default, 'MDL0003', 'MOT0003'),
(default, 'MDL0004', 'MOT0001'),
(default, 'MDL0005', 'MOT0002');

-- Inserting data into model_fuel_type table
INSERT INTO model_fuel_type (id_model_fuel_type, id_model, id_fuel_type) VALUES
(default, 'MDL0001', 'FUT0001'),
(default, 'MDL0002', 'FUT0002'),
(default, 'MDL0003', 'FUT0003'),
(default, 'MDL0004', 'FUT0001'),
(default, 'MDL0005', 'FUT0002');

INSERT INTO transmission (id_transmission, name) VALUES
('TRA0001', 'Automatic'),
('TRA0002', 'Manual'),
('TRA0003', 'Semi-automatic');

INSERT INTO users (id_users, id_gender, username, name, first_name, birthdate, email, password, is_admin) VALUES 
('USR0001', 0, 'mhr_di', 'Diana', 'Rakotomaharo', '2004-12-16', 'dianarakoto9@gmail.com', '1234567huit', true),
('USR0002', 1, 'mmms', 'Mamisoa', 'Ratsimbazafy', '2002-04-24', 'rmams@gmail.com', 'mamisoa', false);

INSERT INTO Car_status(id_car_status, name) VALUES 
(0, 'In working condition'),
(1, 'Non-operational');

INSERT INTO Car (id_car, door_number, kilometrage, color, id_transmission, id_model_motor, id_model_fuel_type, id_users, id_model, id_car_status, id_model_gear_box) VALUES
('CAR0001', 4, 50000.00, 'Blue', 'TRA0001', 1, 1, 'USR0001', 'MDL0001', 1, 1),
('CAR0002', 2, 70000.50, 'Silver', 'TRA0002', 2, 2, 'USR0002', 'MDL0002', 0, 2),
('CAR0003', 4, 30000.75, 'Red', 'TRA0003', 3, 3, 'USR0001', 'MDL0003', 1, 3),
('CAR0004', 2, 60000.25, 'Black', 'TRA0001', 1, 1, 'USR0002', 'MDL0004', 0, 4),
('CAR0005', 4, 40000.00, 'White', 'TRA0002', 2, 2, 'USR0001', 'MDL0005', 1, 5);

-- Inserting data into Announcement table
INSERT INTO Announcement (id_announcement, status, price, description, id_location, date_addition, date_validation, id_car) VALUES
('ANN0001', 1, 15000.00, 'Well-maintained Honda Accord for sale', 'LOC0001', CURRENT_TIMESTAMP, NULL, 'CAR0001'),
('ANN0002', 1, 25000.00, 'Ford F-150 in excellent condition', 'LOC0003', CURRENT_TIMESTAMP, NULL, 'CAR0002'),
('ANN0003', 1, 18000.50, 'Sporty red Honda Civic Type R', 'LOC0001', CURRENT_TIMESTAMP, NULL, 'CAR0003'),
('ANN0004', 1, 20000.75, 'Powerful Civic Type R with low mileage', 'LOC0002', CURRENT_TIMESTAMP, NULL, 'CAR0004'),
('ANN0005', 1, 22000.00, 'White Ford Escape, great fuel efficiency', 'LOC0002', CURRENT_TIMESTAMP, NULL, 'CAR0005');

-- Inserting data into sale table
INSERT INTO sale (id_sale, date_sale, price_payed, id_announcement, id_seller) VALUES
('VEN0001', CURRENT_TIMESTAMP, 14500.00, 'ANN0001', 'USR0002'),
('VEN0002', CURRENT_TIMESTAMP, 24000.00, 'ANN0002', 'USR0001'),
('VEN0003', CURRENT_TIMESTAMP, 17500.50, 'ANN0003', 'USR0002'),
('VEN0004', CURRENT_TIMESTAMP, 19500.75, 'ANN0004', 'USR0001'),
('VEN0005', CURRENT_TIMESTAMP, 21500.00, 'ANN0005', 'USR0002');

-- Inserting data into Favorite table
INSERT INTO Favorite (id_favorite, id_announcement, id_users) VALUES
('FAV0001', 'ANN0001', 'USR0001'),
('FAV0002', 'ANN0002', 'USR0002'),
('FAV0003', 'ANN0003', 'USR0001'),
('FAV0004', 'ANN0004', 'USR0002'),
('FAV0005', 'ANN0005', 'USR0001');

INSERT INTO commission(id_commission, boundary_inferior, boundary_superior, percentage) VALUES
('COM0001', 0, 15000.00, 15),
('COM0002', 15000.00, 30000.00, 20),
('COM0003', 30000.00, 50000.00, 30);


INSERT INTO month VALUES 
(1, 'January'),    
(2, 'February'),
(3, 'March'),
(4, 'April'),
(5, 'May'),
(6, 'June'),
(7, 'July'),
(8, 'August'),
(9, 'September'),
(10, 'October'),
(11, 'November'),
(12, 'December');