package br.com.fesvip.crm.service.impl;

import br.com.fesvip.crm.entity.Cliente;
import br.com.fesvip.crm.entity.Usuario;
import br.com.fesvip.crm.entity.Venda;
import br.com.fesvip.crm.entity.enums.Status;
import br.com.fesvip.crm.repository.VendaRepository;
import br.com.fesvip.crm.service.ClienteService;
import br.com.fesvip.crm.service.UsuarioService;
import br.com.fesvip.crm.service.VendaService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fesvip.crm.service.exceptions.EntityNotFoundExceptions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Venda save(Venda venda, Long clienteId, Authentication authentication) {

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Usuario usuario = usuarioService.findByUsername(username);
            Cliente cliente = clienteService.findById(clienteId);

            venda.setCliente(cliente);
            venda.setUsuario(usuario);
            venda.setData(LocalDateTime.now());

            return vendaRepository.save(venda);
        }
        return null;
    }

    @Override
    public Venda findById(Long id) {
        return vendaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Venda com Id " + id + " não encontrada"));
    }

    @Override
    public Page<Venda> findAll(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    @Override
    public Page<Venda> findAllLast30Days(Pageable pageable) {
        LocalDateTime startDate = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
        return vendaRepository.findByDataAfter(startDate, pageable);
    }

    @Override
    public Page<Venda> findAllLast30DaysByUser(Pageable pageable) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        LocalDateTime startDate = LocalDateTime.now().minus(30, ChronoUnit.DAYS);

        return vendaRepository.findByUsuarioUsernameAndDataAfter(username, startDate, pageable);
    }

    @Override
    public Page<Venda> findByStatus(Status status, Pageable pageable) {
        return vendaRepository.findByStatus(status, pageable);
    }


    @Override
    public Venda update(Long id, Venda vendaAtualizado) {
        Venda vendaExistente = vendaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));

        vendaExistente.setData(LocalDateTime.now());
        vendaExistente.setStatus(vendaAtualizado.getStatus());
        vendaExistente.setObs(vendaAtualizado.getObs());
        vendaExistente.setCurso(vendaAtualizado.getCurso());
        vendaExistente.setValor(vendaAtualizado.getValor());

        return vendaRepository.save(vendaExistente);
    }

    @Override
    public void delete(Long id) {
        Venda venda = vendaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Venda com Id " + id + " não encontrada"));

        vendaRepository.delete(venda);
    }
}