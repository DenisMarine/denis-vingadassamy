CREATE MATERIALIZED VIEW party_participants_count AS
       SELECT p.party_id, p.name AS party_name, COUNT(pt.profile_id) AS participant_count
       FROM party p
           LEFT JOIN participant pt ON p.party_id = pt.party_id
       GROUP BY p.party_id, p.name;