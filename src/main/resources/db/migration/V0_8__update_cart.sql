ALTER TABLE shop.cart DROP CONSTRAINT cart_pkey;
ALTER TABLE shop.cart ADD CONSTRAINT cart_pk PRIMARY KEY (user_id, book_id);

ALTER TABLE shop.cart DROP id;
