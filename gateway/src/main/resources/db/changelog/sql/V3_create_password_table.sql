--author Edilbek

CREATE TABLE APP_USER.password (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id NUMBER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES APP_USER.USERS(id),
    password VARCHAR(255) NOT NULL,
    password_strength_level NUMBER(1, 0) NOT NULL,
    create_time DATE DEFAULT SYSDATE NOT NULL,
    update_time DATE DEFAULT SYSDATE NOT NULL
);