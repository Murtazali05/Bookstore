ALTER TABLE shop."order" RENAME COLUMN status TO status_id;
ALTER TABLE shop."order" ALTER COLUMN status_id TYPE INTEGER USING status_id::INTEGER;

ALTER TABLE shop."order"
ADD CONSTRAINT order_status_id_fk
FOREIGN KEY (status_id) REFERENCES shop.status (id);