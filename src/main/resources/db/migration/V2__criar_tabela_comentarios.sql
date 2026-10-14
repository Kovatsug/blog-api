CREATE TABLE IF NOT EXISTS comentario (
    id BINARY(16) NOT NULL,
    data DATE NOT NULL,
    comentario TEXT NOT NULL,
    post_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_comentario_post
        FOREIGN KEY (post_id) REFERENCES post(id)
);