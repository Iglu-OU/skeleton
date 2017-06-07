CREATE TABLE todo_list (
  id BIGSERIAL PRIMARY KEY,
  name text
);

CREATE TABLE todo_item (
  id BIGSERIAL PRIMARY KEY,
--   todo_list_id BIGINT NOT NULL REFERENCES todo_list (id),
  name text,
  checked BOOLEAN NOT NULL,
  deleted BOOLEAN NOT NULL,
  created_by BIGINT
);
