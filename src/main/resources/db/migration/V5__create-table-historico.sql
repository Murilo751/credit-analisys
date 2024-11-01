CREATE TABLE IF NOT EXISTS historico (
                                         id SERIAL PRIMARY KEY,
                                         pontuacao INT,
                                         pagamentosPontuais INT,
                                         incidentes INT,
                                         dividas DECIMAL(10, 2),
                                         userId BIGINT,
                                         FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);
