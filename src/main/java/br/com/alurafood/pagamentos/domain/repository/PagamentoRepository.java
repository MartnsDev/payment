package br.com.alurafood.pagamentos.domain.repository;

import br.com.alurafood.pagamentos.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
