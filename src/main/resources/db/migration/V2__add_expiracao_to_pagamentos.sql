-- V2__add_expiracao_to_pagamentos.sql
-- Adiciona a coluna expiracao de forma segura

ALTER TABLE pagamentos
  ADD COLUMN expiracao VARCHAR(7);

-- Opcional: setar um valor padrão para linhas antigas (ajuste conforme convénio)
UPDATE pagamentos
  SET expiracao = '00/0000'
  WHERE expiracao IS NULL;

-- Se você quiser forçar NOT NULL depois que povoar/validar:
-- ALTER TABLE pagamentos MODIFY expiracao VARCHAR(7) NOT NULL;
