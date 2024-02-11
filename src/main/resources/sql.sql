-- Table for Role Enum

CREATE TABLE companyuser
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE project
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    creatorid BIGINT       NOT NULL,
    CONSTRAINT fk_creator_project FOREIGN KEY (creatorid) REFERENCES companyuser (id)
);

CREATE TABLE document
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    description TEXT,
    topic       VARCHAR(255),
    creatorid   BIGINT       NOT NULL,
    projectid   BIGINT       NOT NULL,
    CONSTRAINT fk_creator_doc FOREIGN KEY (creatorid) REFERENCES companyuser (id),
    CONSTRAINT fk_project_doc FOREIGN KEY (projectid) REFERENCES project (id)
);

CREATE TABLE user_roles
(
    id         SERIAL PRIMARY KEY,
    project_id BIGINT       NOT NULL,
    user_id    BIGINT       NOT NULL,
    role       VARCHAR(255) NOT NULL,
    CONSTRAINT fk_project_user_roles FOREIGN KEY (project_id) REFERENCES project (id),
    CONSTRAINT fk_user_user_roles FOREIGN KEY (user_id) REFERENCES companyuser (id)
);

CREATE TABLE administrator
(
    id      SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user_administrator FOREIGN KEY (user_id) REFERENCES companyuser (id)
);

-- Inserting data
INSERT INTO companyuser (username, password)
VALUES ('admin', 'admin');

-- Adding a user to the administrator table
INSERT INTO administrator (user_id)
VALUES (1);
