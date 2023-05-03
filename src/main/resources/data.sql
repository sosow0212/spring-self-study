DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS member;

CREATE TABLE book
(
    id         INT AUTO_INCREMENT,
    title     VARCHAR(45) NOT NULL,
    content     VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE member
(
    id       BIGINT AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO member(id, email, password)
VALUES (1, 'test1@test.com', '!!abc123'),
       (2, 'test2@test.com', '!!abc123');
