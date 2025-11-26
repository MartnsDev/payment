-- V3__add_missing_columns_to_pagamentos.sql
-- Adiciona colunas que a entidade Pagamento espera e evita erro se já existirem
ALTER TABLE pagamentos
  ADD COLUMN IF NOT EXISTS forma_de_pagamento_id BIGINT,
  ADD COLUMN IF NOT EXISTS codigo_de_seguranca VARCHAR(3),
  ADD COLUMN IF NOT EXISTS status VARCHAR(20);

-- Opcional: preencher valores nulos para linhas antigas (ajuste conforme seu domínio)
UPDATE pagamentos
  SET codigo_de_seguranca = '000'
  WHERE codigo_de_seguranca IS NULL;

UPDATE pagamentos
  SET status = 'CRIADO'
  WHERE status IS NULL;
