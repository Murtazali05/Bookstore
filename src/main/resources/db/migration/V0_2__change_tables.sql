CREATE UNIQUE INDEX category_code_uindex ON shop.category (code);

ALTER TABLE shop.book DROP genre;
ALTER TABLE shop.book ADD created_at date NOT NULL default current_date;