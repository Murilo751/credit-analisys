CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     phone VARCHAR(50),
                                     email VARCHAR(255) UNIQUE,
                                     birthday DATE
);
