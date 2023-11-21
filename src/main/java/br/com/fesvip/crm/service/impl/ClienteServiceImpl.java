package br.com.fesvip.crm.service.impl;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.repository.ClienteRepository;
import br.com.fesvip.crm.service.ClienteService;
import br.com.fesvip.crm.service.exceptions.EntityNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        cliente.setData(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }


    @Override
    public Cliente update(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));

        // Atualizar os campos do clienteExistente com os valores do clienteAtualizado
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setNumeroTelefone1(clienteAtualizado.getNumeroTelefone1());
        clienteExistente.setNumeroTelefone2(clienteAtualizado.getNumeroTelefone2());
        clienteExistente.setPlataforma(clienteAtualizado.getPlataforma());
        clienteExistente.setEmail(clienteAtualizado.getEmail());

        return clienteRepository.save(clienteExistente);
    }

    @Override
    public void delete(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Item não encontrado: " + id));

        clienteRepository.delete(cliente);
    }

    @Override
    public Page<Cliente> findByNomeOrNumeroTelefoneOrEmail(String searchTerm, Pageable pageable) {
        return clienteRepository.findByNomeOrNumeroTelefoneOrEmail(searchTerm, pageable);
    }
}
