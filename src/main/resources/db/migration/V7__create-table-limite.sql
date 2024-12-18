CREATE TABLE IF NOT EXISTS limite (
                                             id SERIAL PRIMARY KEY,
                                             userId BIGINT,
                                             valor DECIMAL(10, 2),
                                             categoria limiteEnum,
                                             dataAprovacao DATE,
                                             FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);
