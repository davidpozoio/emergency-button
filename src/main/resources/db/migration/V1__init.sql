CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    gender VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL,
    identification CHAR(10) NOT NULL,
    role VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS alert(
    id SERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    details VARCHAR(100) NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL,
    created_at TIMESTAMP,
    user_id serial NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);