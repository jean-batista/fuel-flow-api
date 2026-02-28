CREATE TABLE IF NOT EXISTS fuel_type (
	id UUID PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	price_per_liter DECIMAL(10,3) NOT NULL
);

CREATE TABLE IF NOT EXISTS fuel_pump (
	id UUID PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	fuel_type_id UUID NOT NULL,
	
	CONSTRAINT fuel_pump_fuel_type FOREIGN KEY (fuel_type_id) REFERENCES fuel_type (id)
);

CREATE TABLE IF NOT EXISTS supply (
	id UUID PRIMARY KEY,
	fuel_pump_id UUID NOT NULL,
	supply_date TIMESTAMP NOT NULL,
	total_price DECIMAL(19,2) NOT NULL,
	liters DECIMAL(12,3) NOT NULL,
	
	CONSTRAINT supply_fuel_pump FOREIGN KEY (fuel_pump_id) REFERENCES fuel_pump (id)
);