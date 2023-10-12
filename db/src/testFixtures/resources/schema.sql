-- File is imported by Spring Boot on startup

DROP TABLE IF EXISTS invitation;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS uniform;
DROP TABLE IF EXISTS opponent;
DROP TABLE IF EXISTS venue;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS club;

CREATE TABLE club
(
    id           bigint NOT NULL AUTO_INCREMENT,
    name         varchar(255) DEFAULT NULL,
    objectStatus int          DEFAULT NULL,
    lat double DEFAULT NULL,
    lng double DEFAULT NULL,
    createDate   datetime     DEFAULT NULL,
    updateDate   datetime     DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX ix_club_objectstatus ON club (objectStatus);
CREATE INDEX ix_club_createDate ON club (createDate);


CREATE TABLE team
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    url          VARCHAR(255),
    club_id      BIGINT      NOT NULL,
    objectStatus INT,
    createDate   TIMESTAMP,
    updateDate   TIMESTAMP,
    CONSTRAINT name_club_id_unique UNIQUE (name, club_id),
    CONSTRAINT FK_team_club_id FOREIGN KEY (club_id) REFERENCES club (id)
);

CREATE INDEX ix_team_club ON team (club_id);
CREATE INDEX ix_team_objectstatus ON team (objectStatus);
CREATE INDEX ix_team_createDate ON team (createDate);

CREATE TABLE users
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(80)  NOT NULL,
    enabled         BOOLEAN      NOT NULL,
    password        VARCHAR(50)  NOT NULL,
    fullname        VARCHAR(50)  NOT NULL,
    initialPassword VARCHAR(50),
    invitationSent  BOOLEAN,
    status          VARCHAR(255) NOT NULL,
    club_id         BIGINT       NOT NULL,
    objectStatus    INT,
    secondEmail     VARCHAR(255),
    phoneNumber     VARCHAR(255),
    createDate      TIMESTAMP,
    updateDate      TIMESTAMP,
    lastLogin       TIMESTAMP,
    CONSTRAINT username_unique UNIQUE (username),
    CONSTRAINT fullname_club_id_unique UNIQUE (fullname, club_id),
    CONSTRAINT FK_users_club_id FOREIGN KEY (club_id) REFERENCES club (id)
);

CREATE INDEX ix_users_club ON users (club_id);
CREATE INDEX ix_users_status ON users (status);
CREATE INDEX ix_users_enabled ON users (enabled);
CREATE INDEX ix_users_username ON users (username);
CREATE INDEX ix_users_objectstatus ON users (objectStatus);
CREATE INDEX ix_users_fullname ON users (fullname);
CREATE INDEX ix_users_createDate ON users (createDate);


CREATE TABLE venue
(
    id           BIGINT PRIMARY KEY,
    address      VARCHAR(255)     DEFAULT NULL,
    lat          DOUBLE PRECISION DEFAULT NULL,
    lng          DOUBLE PRECISION DEFAULT NULL,
    name         VARCHAR(80) NOT NULL,
    club_id      BIGINT      NOT NULL,
    objectStatus INT              DEFAULT NULL,
    createDate   TIMESTAMP        DEFAULT NULL,
    updateDate   TIMESTAMP        DEFAULT NULL,
    FOREIGN KEY (club_id) REFERENCES club (id)
);

-- Index statements:
CREATE INDEX ix_venue_club ON venue (club_id);
CREATE INDEX ix_venue_objectstatus ON venue (objectStatus);
CREATE INDEX ix_venue_createDate ON venue (createDate);

CREATE TABLE opponent
(
    id           BIGINT PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    url          VARCHAR(255),
    objectStatus INTEGER,
    club_id      BIGINT,
    createDate   TIMESTAMP,
    updateDate   TIMESTAMP,
    FOREIGN KEY (club_id) REFERENCES club (id)
);

CREATE INDEX ix_opponent_club ON opponent (club_id);
CREATE INDEX ix_opponent_objectstatus ON opponent (objectStatus);
CREATE INDEX ix_opponent_createDate ON opponent (createDate);

CREATE TABLE uniform
(
    id           BIGINT PRIMARY KEY,
    objectStatus INTEGER,
    name         VARCHAR(255),
    shirt        VARCHAR(255),
    shorts       VARCHAR(255),
    socks        VARCHAR(255),
    team_id      BIGINT,
    createDate   TIMESTAMP,
    updateDate   TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE INDEX ix_uniform_team ON uniform (team_id);
CREATE INDEX ix_uniform_objectstatus ON uniform (objectStatus);
CREATE INDEX ix_uniform_createDate ON uniform (createDate);

CREATE TABLE player
(
    id           BIGINT PRIMARY KEY NOT NULL,
    notification BOOLEAN            NOT NULL,
    optional     BOOLEAN            NOT NULL,
    retired      BOOLEAN            NOT NULL,
    team_id      BIGINT             NOT NULL,
    user_id      BIGINT             NOT NULL,
    objectStatus INT      DEFAULT NULL,
    createDate   DATETIME DEFAULT NULL,
    updateDate   DATETIME DEFAULT NULL,
    FOREIGN KEY (team_id) REFERENCES team (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Indexes
CREATE INDEX ix_player_retired ON player (retired);
CREATE INDEX ix_player_notification ON player (notification);
CREATE INDEX ix_player_user ON player (user_id);
CREATE INDEX ix_player_team ON player (team_id);
CREATE INDEX ix_player_optional ON player (optional);
CREATE INDEX ix_player_objectstatus ON player (objectStatus);
CREATE INDEX ix_player_createDate ON player (createDate);



CREATE TABLE event
(
    eventType      VARCHAR(31)  NOT NULL,
    id             BIGINT PRIMARY KEY,
    comment        VARCHAR(255) DEFAULT NULL,
    invitationSent BOOLEAN      NOT NULL,
    summary        VARCHAR(100) NOT NULL,
    kickOff        TIME         DEFAULT NULL,
    club_id        BIGINT       NOT NULL,
    team_id        BIGINT       NOT NULL,
    venue_id       BIGINT       DEFAULT NULL,
    opponent_id    BIGINT       DEFAULT NULL,
    objectStatus   INT          DEFAULT NULL,
    surfaceList    VARCHAR(255) DEFAULT NULL,
    uniform_id     BIGINT       DEFAULT NULL,
    createdBy_id   BIGINT       DEFAULT NULL,
    canceled       BOOLEAN      DEFAULT NULL,
    dateTime       TIMESTAMP    DEFAULT NULL,
    createDate     TIMESTAMP    DEFAULT NULL,
    updateDate     TIMESTAMP    DEFAULT NULL,
    dateTimeEnd    TIMESTAMP    DEFAULT NULL,
    FOREIGN KEY (venue_id) REFERENCES venue (id),
    FOREIGN KEY (club_id) REFERENCES club (id),
    FOREIGN KEY (opponent_id) REFERENCES opponent (id),
    FOREIGN KEY (team_id) REFERENCES team (id),
    FOREIGN KEY (uniform_id) REFERENCES uniform (id),
    FOREIGN KEY (createdBy_id) REFERENCES users (id)
);

CREATE INDEX ix_event_club ON event (club_id);
CREATE INDEX ix_event_invitationsent ON event (invitationSent);
CREATE INDEX ix_event_objectstatus ON event (objectStatus);
CREATE INDEX ix_event_team ON event (team_id);
CREATE INDEX ix_event_canceled ON event (canceled);
CREATE INDEX ix_event_datetime ON event (dateTime);
CREATE INDEX ix_event_createDate ON event (createDate);


CREATE TABLE invitation
(
    id                         BIGINT PRIMARY KEY,
    date                       TIMESTAMP,
    guestName                  VARCHAR(50),
    invitationSent             BOOLEAN      NOT NULL,
    status                     VARCHAR(255) NOT NULL,
    event_id                   BIGINT       NOT NULL,
    user_id                    BIGINT,
    objectStatus               INT,
    invitationSentDate         TIMESTAMP,
    noResponseReminderSent     BOOLEAN,
    noResponseReminderSentDate TIMESTAMP,
    unsureReminderSent         BOOLEAN,
    unsureReminderSentDate     TIMESTAMP,
    createDate                 TIMESTAMP,
    updateDate                 TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX ix_invitation_event ON invitation (event_id);
CREATE INDEX ix_invitation_user ON invitation (user_id);
CREATE INDEX ix_invitation_status ON invitation (status);
CREATE INDEX ix_invitation_invitationsent ON invitation (invitationSent);
CREATE INDEX ix_invitation_objectstatus ON invitation (objectStatus);
CREATE INDEX ix_invitation_date ON invitation (date);
CREATE INDEX ix_invitation_noresponseremindersentdate ON invitation (noResponseReminderSentDate);
CREATE INDEX ix_invitation_invitationsentdate ON invitation (invitationSentDate);
CREATE INDEX ix_invitation_noresponseremindersent ON invitation (noResponseReminderSent);
CREATE INDEX ix_invitation_unsureremindersentdate ON invitation (unsureReminderSentDate);
CREATE INDEX ix_invitation_unsureremindersent ON invitation (unsureReminderSent);
CREATE INDEX ix_invitation_createDate ON invitation (createDate);
