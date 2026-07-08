CREATE TABLE course
(
    id         UUID                        NOT NULL,
    title      VARCHAR(255)                NOT NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE enrollment
(
    id          UUID                        NOT NULL,
    user_id     UUID                        NOT NULL,
    course_id   UUID                        NOT NULL,
    enrolled_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_enrollment PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id         UUID         NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    user_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE enrollment
    ADD CONSTRAINT uc_23b5ad7768b18adc0a804ece3 UNIQUE (user_id, course_id);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_username UNIQUE (user_name);