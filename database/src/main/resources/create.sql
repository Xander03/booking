CREATE TABLE IF NOT EXISTS USER
(
  id UUID PRIMARY KEY NOT NULL,
  login varchar(255) NOT NULL,
  password varchar(255) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS USER_id_uindex ON USER (id);
CREATE UNIQUE INDEX IF NOT EXISTS USER_login_uindex ON USER (login);

CREATE TABLE IF NOT EXISTS HOTEL
(
  id UUID PRIMARY KEY NOT NULL,
  name varchar(255) NOT NULL,
  address varchar(255) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS HOTEL_id_uindex ON HOTEL (id);

CREATE TABLE IF NOT EXISTS ROOM
(
  id UUID PRIMARY KEY NOT NULL,
  floor int NOT NULL,
  places int NOT NULL,
  user_id varchar(36),
  hotel_id varchar(36) NOT NULL,
  CONSTRAINT ROOM_HOTEL_ID_fk FOREIGN KEY (hotel_id) REFERENCES HOTEL (ID) ON DELETE CASCADE,
  CONSTRAINT ROOM_USER_ID_fk FOREIGN KEY (user_id) REFERENCES USER (ID) ON DELETE SET NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS ROOM_id_uindex ON ROOM (id);

CREATE TABLE IF NOT EXISTS ROLE
(
  id int PRIMARY KEY NOT NULL,
  name varchar(255) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS ROLE_id_uindex ON ROLE (id);
CREATE UNIQUE INDEX IF NOT EXISTS ROLE_name_uindex ON ROLE (name);

CREATE TABLE IF NOT EXISTS USER_ROLE
(
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,
  CONSTRAINT USER_ROLE_pk PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS USER_ROLE
(
  user_id UUID NOT NULL,
  role_id int NOT NULL,
  CONSTRAINT USER_ROLE_pk PRIMARY KEY (user_id, role_id),
  CONSTRAINT USER_ROLE_USER_ID_fk FOREIGN KEY (user_id) REFERENCES USER (ID) ON DELETE CASCADE,
  CONSTRAINT USER_ROLE_ROLE_ID_fk FOREIGN KEY (role_id) REFERENCES ROLE (ID) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS USER_ROLE_user_id_role_id_index ON USER_ROLE (user_id, role_id);
