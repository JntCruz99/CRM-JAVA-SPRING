package br.com.fesvip.crm.service;

import br.com.fesvip.crm.entity.Mensagem;

import java.util.List;

public interface MensagemService {
    Mensagem save(Mensagem mensagem, Long clienteId);

    Mensagem findById(Long id);

    List<Mensagem> findAll();

    Mensagem update(Long id, Mensagem mensagemAtualizado);

    void delete(Long id);
}
