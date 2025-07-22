--author Edilbek

CREATE TABLE APP_USER.users (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR2(255),
    phone_number NUMBER(11) DEFAULT NULL,
    user_first_name VARCHAR2(255) NOT NULL,
    user_last_name VARCHAR2(255) NOT NULL,
    last_active_time DATE NOT NULL,
    create_time DATE DEFAULT SYSDATE NOT NULL,
    update_time DATE DEFAULT SYSDATE NOT NULL
);