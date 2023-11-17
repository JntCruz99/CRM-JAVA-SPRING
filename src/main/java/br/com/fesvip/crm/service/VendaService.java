package br.com.fesvip.crm.service;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Usuario;
import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface VendaService {

    Venda save(Venda venda, Long clienteId, Authentication authentication);

    Venda findById(Long id);

    Page<Venda> findAll(Pageable pageable);

    Page<Venda> findAllLast30Days(Pageable pageable);

    public Page<Venda> findAllLast30DaysByUser(Pageable pageable);

    Page<Venda> findByStatus(Status status, Pageable pageable);

    Venda update(Long id, Venda vendaAtualizado);
    void delete(Long id);
}
