package br.com.fesvip.crm.service;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    Cliente save(Cliente cliente);

    Cliente findById(Long id);

    Page<Cliente> findAll(Pageable pageable);

    Cliente update(Long id, Cliente clienteAtualizado);

    Page<Cliente> findAllOrderByDataDesc(Pageable pageable);

    void delete(Long id);

    Page<Cliente> findByNomeOrNumeroTelefoneOrEmail(String searchTerm, Pageable pageable);
}
