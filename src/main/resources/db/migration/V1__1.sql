CREATE TABLE course
(
    id         UUID NOT NULL,
    title      VARCHAR(255),
    start_date TIMESTAMP WITHOUT TIME ZONE,
    end_date   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE enrollments
(
    course_id UUID NOT NULL,
    user_id   UUID NOT NULL,
    CONSTRAINT pk_enrollments PRIMARY KEY (course_id, user_id)
);

CREATE TABLE "user"
(
    id         UUID NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    user_name  VARCHAR(255),
    email      VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE enrollments
    ADD CONSTRAINT fk_enr_on_j_course FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE enrollments
    ADD CONSTRAINT fk_enr_on_j_user FOREIGN KEY (user_id) REFERENCES "user" (id);