ALTER TABLE car ADD COLUMN color VARCHAR(50);    
ALTER TABLE car ADD COLUMN id_transmission VARCHAR(50) REFERENCES transmission(id_transmission);    
