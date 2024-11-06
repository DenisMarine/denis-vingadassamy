CREATE INDEX user_email_idx ON user(email);

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