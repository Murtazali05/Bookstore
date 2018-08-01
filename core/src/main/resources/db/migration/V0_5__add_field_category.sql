ALTER TABLE shop.category ADD parent_id INTEGER NULL;

ALTER TABLE shop.category
ADD CONSTRAINT fk_category
FOREIGN KEY (parent_id) REFERENCES shop.category (id);