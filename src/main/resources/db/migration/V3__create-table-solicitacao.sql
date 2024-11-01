CREATE TABLE IF NOT EXISTS solicitacao (
                                           id BIGSERIAL PRIMARY KEY,
                                           userId BIGINT NOT NULL,
                                           status statusEnum,
                                           valor DECIMAL(19, 2) NOT NULL,
                                           dataSolicitacao DATE NOT NULL,
                                           historicoCredito VARCHAR(255),
                                           FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);

