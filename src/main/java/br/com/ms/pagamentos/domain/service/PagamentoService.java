package br.com.ms.pagamentos.domain.service;

import br.com.ms.pagamentos.api.dto.PagamentoDTO;
import br.com.ms.pagamentos.domain.model.Pagamento;
import br.com.ms.pagamentos.domain.model.Status;
import br.com.ms.pagamentos.domain.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ModelMapper modelMapper;

    public PagamentoService(PagamentoRepository pagamentoRepository, ModelMapper modelMapper) {
        this.pagamentoRepository = pagamentoRepository;
        this.modelMapper = modelMapper;
    }

    // leitura paginada — sem transação obrigatória (somente leitura)
    public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
        return pagamentoRepository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDTO.class));
    }

    public PagamentoDTO obterPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado: " + id));

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    // criação em transação
    @Transactional
    public PagamentoDTO criarPagamento(PagamentoDTO dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamento = pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    // atualização segura: busca entidade existente, aplica mudanças do DTO e salva
    @Transactional
    public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto) {
        Pagamento existente = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado: " + id));

        // Mapeia propriedades do DTO para a entidade existente.
        // modelMapper.map(dto, existente) poderia sobrescrever id/status; aqui só mapeamos campo a campo de forma explícita
        existente.setValor(dto.getValor());
        existente.setNome(dto.getNome());
        existente.setNumero(dto.getNumero());
        existente.setExpiracao(dto.getExpiracao());
        // codigoDeSeguranca não é persistido (está @Transient) — se vier no DTO, é só para validação no controller
        if (dto.getStatus() != null) {
            existente.setStatus(dto.getStatus());
        }
        if (dto.getPedidoId() != null) {
            existente.setPedidoId(dto.getPedidoId());
        }
        if (dto.getFormaDePagamentoId() != null) {
            existente.setFormaDePagamentoId(dto.getFormaDePagamentoId());
        }

        Pagamento atualizado = pagamentoRepository.save(existente);
        return modelMapper.map(atualizado, PagamentoDTO.class);
    }

    @Transactional
    public void excluirPagamento(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pagamento não encontrado: " + id);
        }
        pagamentoRepository.deleteById(id);
    }
}
