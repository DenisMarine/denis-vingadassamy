CREATE MATERIALIZED VIEW user_average_rating AS
       SELECT p.user_id, p.username, AVG(c.rating) AS average_rating
       FROM profile p
           JOIN comment c ON p.profile_id = c.commented_profile
       GROUP BY p.user_id, p.username;
