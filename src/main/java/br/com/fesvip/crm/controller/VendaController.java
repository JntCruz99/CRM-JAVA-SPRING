package br.com.fesvip.crm.controller;

import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.entity.enums.Status;
import br.com.fesvip.crm.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

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
    public ResponseEntity<Page<Venda>> obterTodasVendas(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Venda> vendas = vendaService.findAll(pageable);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarCliente(@PathVariable Long id, @RequestBody Venda vendaAtualizado) {
        Venda vendaAtualizadoSalvo = vendaService.update(id, vendaAtualizado);
        return new ResponseEntity<>(vendaAtualizadoSalvo, HttpStatus.OK);
    }

    @GetMapping("/last30days")
    public ResponseEntity<Page<Venda>> obterVendasUltimos30Dias(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Venda> vendas = vendaService.findAllLast30Days(pageable);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/last30daysuser")
    public ResponseEntity<Page<Venda>> userVendasUltimos30Dias(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Venda> vendas = vendaService.findAllLast30DaysByUser(pageable);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<Page<Venda>> obterVendasPendentes(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Page<Venda> vendasPendentes = vendaService.findByStatus(Status.PENDENTE, PageRequest.of(page, size));
        return new ResponseEntity<>(vendasPendentes, HttpStatus.OK);
    }

    @GetMapping("/convertidos")
    public ResponseEntity<Page<Venda>> obterVendasConvertido(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Page<Venda> vendasPendentes = vendaService.findByStatus(Status.CONVERTIDO, PageRequest.of(page, size));
        return new ResponseEntity<>(vendasPendentes, HttpStatus.OK);
    }

    @GetMapping("/nao-convertidos")
    public ResponseEntity<Page<Venda>> obterVendasNaoConvertido(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Page<Venda> vendasPendentes = vendaService.findByStatus(Status.NAO_CONVERTIDO, PageRequest.of(page, size));
        return new ResponseEntity<>(vendasPendentes, HttpStatus.OK);
    }
}
