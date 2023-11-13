package br.com.fesvip.crm.controller;

import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/{clienteId}")
    public ResponseEntity<Venda> salvarVenda(Authentication authentication, @RequestBody Venda venda, @PathVariable Long clienteId) {
        Venda vendaSalva = vendaService.save(venda, clienteId, authentication);
        return new ResponseEntity<>(vendaSalva, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> obterVendaPorId(@PathVariable Long id) {
        Venda venda = vendaService.findById(id);
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> obterTodasVendas() {
        List<Venda> vendas = vendaService.findAll();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
