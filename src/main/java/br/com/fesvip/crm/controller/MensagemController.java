package br.com.fesvip.crm.controller;


import br.com.fesvip.crm.entity.Mensagem;
import br.com.fesvip.crm.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

    @Autowired
    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Mensagem> save(@RequestBody Mensagem mensagem, @PathVariable Long clienteId) {
        Mensagem savedMensagem = mensagemService.save(mensagem, clienteId);
        return new ResponseEntity<>(savedMensagem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> findById(@PathVariable Long id) {
        Mensagem mensagem = mensagemService.findById(id);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Mensagem>> findAll() {
        List<Mensagem> mensagens = mensagemService.findAll();
        return new ResponseEntity<>(mensagens, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> update(@PathVariable Long id, @RequestBody Mensagem mensagemAtualizado) {
        Mensagem updatedMensagem = mensagemService.update(id, mensagemAtualizado);
        return new ResponseEntity<>(updatedMensagem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mensagemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

