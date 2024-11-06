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
                         interests JSON,
                         delete_date TIMESTAMP WITH TIME ZONE,
                         creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                         update_date TIMESTAMP WITH TIME ZONE NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(user_id),
                         FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE comment (
                         comment_id INT NOT NULL PRIMARY KEY,
                         commented_profile INT NOT NULL,
                         written_by INT NOT NULL,
                         text VARCHAR(300),
                         rating INT NOT NULL DEFAULT 5,
                         creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                         update_date TIMESTAMP WITH TIME ZONE NOT NULL,
                         FOREIGN KEY (profile_commented) REFERENCES profile(profile_id),
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
);

CREATE TABLE participant (
                             user_id INT NOT NULL,
                             party_id INT NOT NULL,
                             accepted BOOLEAN DEFAULT FALSE,
                             has_paid BOOLEAN DEFAULT FALSE,
                             creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                             PRIMARY KEY (user_id, party_id),
                             FOREIGN KEY (user_id) REFERENCES profile(profile_id),
                             FOREIGN KEY (party_id) REFERENCES party(party_id)
);

CREATE TABLE item (
                      item_id INT NOT NULL PRIMARY KEY,
                      party_id INT NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      type VARCHAR(100) NOT NULL,
                      brought_by INT NOT NULL,
                      number int NOT NULL DEFAULT 1,
                      FOREIGN KEY (party_id) REFERENCES party(party_id),
                      FOREIGN KEY (brought_by) REFERENCES profile(profile_id)
);

CREATE TABLE bring_item (
                            bring_id INT NOT NULL PRIMARY KEY,
                            party_id INT NOT NULL,
                            type VARCHAR(100) NOT NULL,
                            FOREIGN KEY (party_id) REFERENCES party(party_id),
                            CONSTRAINT unique_bring_item_type_party UNIQUE (party_id, type)
);
