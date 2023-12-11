package br.com.fesvip.crm.service;

import br.com.fesvip.crm.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatService {

    Chat save(Chat chat);

    Chat findById(Long id);

    List<Chat> findAll();

    Chat update(Long id, Chat chatAtualizado);

    void delete(Long id);

}
