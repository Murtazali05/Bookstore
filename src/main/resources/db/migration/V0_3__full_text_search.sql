CREATE OR REPLACE FUNCTION make_tsvector(title TEXT, description TEXT)
   RETURNS tsvector AS $$
BEGIN
  RETURN (setweight(to_tsvector('russian', title), 'A') ||
    setweight(to_tsvector('russian', description), 'B'));
END
$$ LANGUAGE 'plpgsql' IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_fts_books ON book
  USING gin(make_tsvector(title, description));