CREATE TABLE users (
                      user_id INT NOT NULL PRIMARY KEY,
                      email VARCHAR(100) UNIQUE NOT NULL,
                      password VARCHAR(100) NOT NULL
);

CREATE TABLE address (
                         address_id INT NOT NULL PRIMARY KEY,
                         city VARCHAR(100) NOT NULL,
                         region VARCHAR(100) NOT NULL,
                         country VARCHAR(100) NOT NULL,
                         address VARCHAR(100)
);

CREATE TABLE profile (
                         profile_id INT NOT NULL PRIMARY KEY,
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

CREATE TABLE profile_age_0_18 PARTITION OF profile FOR VALUES FROM (0) TO (18);
CREATE TABLE profile_age_18_25 PARTITION OF profile FOR VALUES FROM (18) TO (26);
CREATE TABLE profile_age_26_35 PARTITION OF profile FOR VALUES FROM (26) TO (36);
CREATE TABLE profile_age_36_50 PARTITION OF profile FOR VALUES FROM (36) TO (51);
CREATE TABLE profile_age_50_plus PARTITION OF profile FOR VALUES FROM (51) TO (150);

CREATE TABLE comment (
                         comment_id INT NOT NULL PRIMARY KEY,
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
                       price FLOAT,
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
                             participant_id INT NOT NULL PRIMARY KEY,
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
                      item_id INT NOT NULL PRIMARY KEY,
                      party_id INT NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      type VARCHAR(100) NOT NULL,
                      brought_by INT NOT NULL,
                      quantity int NOT NULL DEFAULT 1,
                      FOREIGN KEY (party_id) REFERENCES party(party_id),
                      FOREIGN KEY (brought_by) REFERENCES participant(participant_id)
);

CREATE TABLE bring_item (
                            bring_id INT NOT NULL PRIMARY KEY,
                            party_id INT NOT NULL,
                            type VARCHAR(100) NOT NULL,
                            FOREIGN KEY (party_id) REFERENCES party(party_id),
                            CONSTRAINT unique_bring_item_type_party UNIQUE (party_id, type)
);
