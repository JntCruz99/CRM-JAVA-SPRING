package br.com.fesvip.crm.service.impl;

import br.com.fesvip.crm.entity.Chat;
import br.com.fesvip.crm.repository.ChatRepository;
import br.com.fesvip.crm.service.ChatService;
import br.com.fesvip.crm.service.exceptions.EntityNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat findById(Long id) {
        return chatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
    }

    @Override
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat update(Long id, Chat chatAtualizado) {
        Chat chatExistente = chatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));

        // Atualizar os campos do chatExistente com os valores do chatAtualizado
        chatExistente.setCliente(chatAtualizado.getCliente());
        chatExistente.setMensagens(chatAtualizado.getMensagens());

        return chatRepository.save(chatExistente);
    }

    @Override
    public void delete(Long id) {
        Chat chat = chatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Item não encontrado: " + id));

        chatRepository.delete(chat);
    }
}
