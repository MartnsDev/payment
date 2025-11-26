-- V3__add_missing_columns_to_pagamentos.sql
-- Adiciona colunas esperadas pela entidade Pagamento, só quando não existirem.

-- forma_de_pagamento_id
SET @col_exists := (
  SELECT COUNT(*)
  FROM information_schema.COLUMNS
  WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'pagamentos'
    AND COLUMN_NAME = 'forma_de_pagamento_id'
);

SET @sql := IF(@col_exists = 0,
  'ALTER TABLE pagamentos ADD COLUMN forma_de_pagamento_id BIGINT',
  'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- codigo_de_seguranca
SET @col_exists := (
  SELECT COUNT(*)
  FROM information_schema.COLUMNS
  WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'pagamentos'
    AND COLUMN_NAME = 'codigo_de_seguranca'
);

SET @sql := IF(@col_exists = 0,
  "ALTER TABLE pagamentos ADD COLUMN codigo_de_seguranca VARCHAR(3)",
  'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- status
SET @col_exists := (
  SELECT COUNT(*)
  FROM information_schema.COLUMNS
  WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'pagamentos'
    AND COLUMN_NAME = 'status'
);

SET @sql := IF(@col_exists = 0,
  "ALTER TABLE pagamentos ADD COLUMN status VARCHAR(20)",
  'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Opcional: preenche valores nulos para linhas antigas (ajuste conforme seu domínio)
UPDATE pagamentos
  SET codigo_de_seguranca = '000'
  WHERE codigo_de_seguranca IS NULL;

UPDATE pagamentos
  SET status = 'CRIADO'
  WHERE status IS NULL;
