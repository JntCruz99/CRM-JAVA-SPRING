package br.com.fesvip.crm.service;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Venda;

import java.util.List;

public interface ClienteService {

    Cliente save(Cliente cliente);

    Cliente findById(Long id);

    List<Cliente> findAll();

    Cliente update(Long id, Cliente clienteAtualizado);

    void delete(Long id);
}
