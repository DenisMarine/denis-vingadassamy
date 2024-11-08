CREATE MATERIALIZED VIEW user_average_rating AS
    SELECT p.*, CAST(ROUND(AVG(c.rating), 2) AS FLOAT) AS average_rating
    FROM profile p
        JOIN comment c ON p.profile_id = c.commented_profile
    GROUP BY p.profile_id;