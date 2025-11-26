package br.com.ms.pagamentos.domain.repository;

import br.com.ms.pagamentos.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
