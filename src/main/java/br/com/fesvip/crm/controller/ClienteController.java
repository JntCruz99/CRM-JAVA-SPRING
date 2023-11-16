package br.com.fesvip.crm.controller;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Cliente>> obterTodosClientes() {
        List<Cliente> clientes = clienteService.findAll();
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
}