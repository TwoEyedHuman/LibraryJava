CREATE TABLE books (
	book_sk serial,
	title varchar(128),
	author varchar(128),
	pages int
)
;

INSERT INTO books (title, author, pages) VALUES
('The Checklist Manifesto', 'Atul Gawande', '208'),
('Killers of the Flower Moon', 'David Grann', '359'),
('American Gods', 'Neil Gaiman', '635')
;