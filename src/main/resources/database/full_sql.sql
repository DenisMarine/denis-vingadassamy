-- Supprimer les vues matérialisées si elles existent
DROP MATERIALIZED VIEW IF EXISTS party_participants_count;
DROP MATERIALIZED VIEW IF EXISTS user_average_rating;

-- Supprimer les tables si elles existent
DROP TABLE IF EXISTS bring_item;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS participant;
DROP TABLE IF EXISTS party;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS users;

-- Création des tables
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    region VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    address VARCHAR(100)
);

CREATE TABLE profile (
    profile_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    address_id INT UNIQUE NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    age INT NOT NULL DEFAULT 18,
    interests JSONB,
    delete_date TIMESTAMP WITH TIME ZONE,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
) PARTITION BY RANGE (age);

CREATE TABLE profile_age_18_25 PARTITION OF profile FOR VALUES FROM (18) TO (26);
CREATE TABLE profile_age_26_35 PARTITION OF profile FOR VALUES FROM (26) TO (36);
CREATE TABLE profile_age_36_50 PARTITION OF profile FOR VALUES FROM (36) TO (51);
CREATE TABLE profile_age_50_plus PARTITION OF profile FOR VALUES FROM (51) TO (150);

CREATE TABLE comment (
    comment_id SERIAL PRIMARY KEY,
    commented_profile INT NOT NULL,
    written_by INT NOT NULL,
    text VARCHAR(300),
    rating INT NOT NULL DEFAULT 5,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (commented_profile) REFERENCES profile(profile_id),
    FOREIGN KEY (written_by) REFERENCES profile(profile_id)
);

CREATE TABLE party (
                       party_id INT NOT NULL PRIMARY KEY,
                       created_by INT NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       address_id INT UNIQUE NOT NULL,
                       party_type VARCHAR(50) NOT NULL,
                       nb_places INT NOT NULL DEFAULT 1,
                       paid VARCHAR(50) NOT NULL,
                       price FLOAT NOT NULL,
                       creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                       update_date TIMESTAMP WITH TIME ZONE NOT NULL,
                       delete_date TIMESTAMP WITH TIME ZONE,
                       FOREIGN KEY (created_by) REFERENCES profile(profile_id),
                       FOREIGN KEY (address_id) REFERENCES address(address_id)
) PARTITION BY RANGE (creation_date);

CREATE TABLE party_before PARTITION OF party FOR VALUES FROM ('1900-01-01') TO ('2023-12-31');
CREATE TABLE party_2024 PARTITION OF party FOR VALUES FROM ('2024-01-01') TO ('2024-12-31');
CREATE TABLE party_future PARTITION OF party FOR VALUES FROM ('2025-01-01') TO ('9999-12-31');

CREATE TABLE participant (
    participant_id SERIAL PRIMARY KEY,
    profile_id INT NOT NULL,
    party_id INT NOT NULL,
    accepted BOOLEAN DEFAULT FALSE,
    has_paid BOOLEAN DEFAULT FALSE,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT unique_participant_in_party UNIQUE (profile_id, party_id),
    FOREIGN KEY (profile_id) REFERENCES profile(profile_id),
    FOREIGN KEY (party_id) REFERENCES party(party_id)
);

CREATE TABLE item (
    item_id SERIAL PRIMARY KEY,
    party_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    brought_by INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (party_id) REFERENCES party(party_id),
    FOREIGN KEY (brought_by) REFERENCES participant(participant_id)
);

CREATE TABLE bring_item (
    bring_id SERIAL PRIMARY KEY,
    party_id INT NOT NULL,
    type VARCHAR(100) NOT NULL,
    FOREIGN KEY (party_id) REFERENCES party(party_id),
    CONSTRAINT unique_bring_item_type_party UNIQUE (party_id, type)
);

-- Création des vues matérialisées
CREATE MATERIALIZED VIEW party_participants_count AS
    SELECT p.party_id, p.name AS party_name, COUNT(pt.profile_id) AS participant_count
    FROM party p
    LEFT JOIN participant pt ON p.party_id = pt.party_id
    GROUP BY p.party_id, p.name;

CREATE MATERIALIZED VIEW user_average_rating AS
    SELECT p.*, CAST(ROUND(AVG(c.rating), 2) AS FLOAT) AS average_rating
    FROM profile p
         JOIN comment c ON p.profile_id = c.commented_profile
    GROUP BY p.profile_id;

-- Création des index
CREATE INDEX user_email_idx ON users(email);
CREATE INDEX profile_user_id_idx ON profile(user_id);
CREATE INDEX profile_username_idx ON profile(username);
CREATE INDEX profile_address_id_idx ON profile(address_id);
CREATE INDEX comment_commented_profile_idx ON comment(commented_profile);
CREATE INDEX comment_written_by_idx ON comment(written_by);
CREATE INDEX party_created_by_idx ON party(created_by);
CREATE INDEX party_address_id_idx ON party(address_id);
CREATE INDEX party_type_idx ON party(party_type);
CREATE INDEX participant_party_id_idx ON participant(party_id);
CREATE INDEX item_party_id_idx ON item(party_id);
CREATE INDEX item_brought_by_idx ON item(brought_by);
CREATE INDEX bring_item_party_id_idx ON bring_item(party_id);
