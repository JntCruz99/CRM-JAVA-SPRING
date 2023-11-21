package br.com.fesvip.crm.controller;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.save(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> obterTodosClientes(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Cliente> clientes = clienteService.findAll(pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/recentes")
    public ResponseEntity<Page<Cliente>> obterTodosClientesRecentes(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Cliente> clientes = clienteService.findAllOrderByDataDesc(pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteAtualizadoSalvo = clienteService.update(id, clienteAtualizado);
        return new ResponseEntity<>(clienteAtualizadoSalvo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Cliente>> searchClientes(
            @RequestParam(required = false) String searchTerm,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        Page<Cliente> clientes = clienteService.findByNomeOrNumeroTelefoneOrEmail(searchTerm, pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
