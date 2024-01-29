-- ALTER TABLE car ADD COLUMN color VARCHAR(50);    
-- ALTER TABLE car ADD COLUMN id_transmission VARCHAR(50) REFERENCES transmission(id_transmission);    


ALTER TABLE sale RENAME COLUMN id_users TO id_seller;

ALTER TABLE commission RENAME COLUMN percentage_ TO percentage;

ALTER TABLE model_fuel_type ADD CONSTRAINT model_fuel_type_unique UNIQUE(id_model,id_fuel_type);
ALTER TABLE model_motor ADD CONSTRAINT model_motor_unique UNIQUE(id_model,id_motorisation);
ALTER TABLE model_gear_box ADD CONSTRAINT model_gerar_box_unique UNIQUE(id_model,id_gear_box);
