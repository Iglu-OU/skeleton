CREATE TABLE todo_list (
  id BIGSERIAL PRIMARY KEY,
  name text
);

CREATE TABLE todo_item (
  id BIGSERIAL PRIMARY KEY,
  name text,
  checked BOOLEAN NOT NULL,
  created_by BIGINT
);
