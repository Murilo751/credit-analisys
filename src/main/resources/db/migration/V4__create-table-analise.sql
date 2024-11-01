CREATE TABLE analise (
                         id SERIAL PRIMARY KEY,
                         descricao VARCHAR(255),
                         resultado statusEnum,
                         dataAnalise DATE,
                         solicitacaoId BIGINT,
                         FOREIGN KEY (solicitacaoId) REFERENCES solicitacao(id)
                     );