
CREATE MATERIALIZED VIEW books_per_author AS
SELECT a.id AS author_id,
       COUNT(b.id) AS num_books
FROM author a
         LEFT JOIN book b ON b.author_id = a.id
GROUP BY a.id;


CREATE MATERIALIZED VIEW authors_per_country AS
SELECT c.id AS country_id,
       c.name AS country_name,
       COUNT(a.id) AS num_authors
FROM country c
         LEFT JOIN author a ON a.country_id = c.id
GROUP BY c.id, c.name;
