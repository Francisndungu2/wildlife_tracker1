SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS endangered_animals(
	id SERIAL PRIMARY KEY,
    animal_id int,
    animal_name VARCHAR ,
    animal_age VARCHAR,
    animal_health VARCHAR
);

CREATE TABLE IF NOT EXISTS animals(
	id SERIAL PRIMARY KEY,
    animal_id int,
    animal_name VARCHAR
);

CREATE TABLE IF NOT EXISTS sightings(
    id SERIAL PRIMARY KEY,
    animal_id int,
    animal_location VARCHAR ,
    ranger_name VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
