TRUNCATE estacionamiento_it.parking_invoice;
TRUNCATE estacionamiento_it.parking_registry;
TRUNCATE estacionamiento_it.vehicle;
TRUNCATE estacionamiento_it.hibernate_sequence;

INSERT INTO estacionamiento_it.hibernate_sequence (next_val) VALUES (100);