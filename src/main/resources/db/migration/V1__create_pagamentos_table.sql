-- V1__create_pagamentos_table.sql
-- Migration inicial para a tabela `pagamentos` compatível com a entidade Pagamento

CREATE TABLE IF NOT EXISTS pagamentos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  valor DECIMAL(12,2) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  numero VARCHAR(19) NOT NULL,
  expiracao VARCHAR(7) NOT NULL,
  status VARCHAR(20) NOT NULL,
  pedido_id BIGINT NOT NULL,
  forma_de_pagamento_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_pagamentos_pedido (pedido_id),
  INDEX idx_pagamentos_forma_pagamento (forma_de_pagamento_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- **Observações:**

-- * Se seu schema `alura_food` já contém tabelas, lembre-se de usar `spring.flyway.baseline-on-migrate=true` para não quebrar o Flyway.
-- * Não inclui coluna para `codigoDeSeguranca` já que na entidade esse campo está marcado com `@Transient` (não deve persistir).
-- * Ajuste `ENGINE`/`CHARSET` conforme padrão do seu ambiente, se necessário.
