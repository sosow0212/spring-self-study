DROP TABLE IF EXISTS book;

CREATE TABLE book
(
    id         INT AUTO_INCREMENT,
    title     VARCHAR(45) NOT NULL,
    content     VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);
