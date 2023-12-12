package br.com.fesvip.crm.service.impl;

import br.com.fesvip.crm.entity.Chat;
import br.com.fesvip.crm.entity.Mensagem;
import br.com.fesvip.crm.entity.Usuario;
import br.com.fesvip.crm.repository.MensagemRepository;
import br.com.fesvip.crm.service.ChatService;
import br.com.fesvip.crm.service.MensagemService;
import br.com.fesvip.crm.service.UsuarioService;
import br.com.fesvip.crm.service.exceptions.EntityNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Mensagem save(Mensagem mensagem, Long chatId) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Usuario usuario = usuarioService.findByUsername(username);

        Chat chat = chatService.findById(chatId);
        mensagem.setChat(chat);
        mensagem.setUsuario(usuario);
        mensagem.setData(LocalDateTime.now());


        List<Mensagem> mensagemsDoChat = chat.getMensagens();
        mensagemsDoChat.add(mensagem);
        chat.setMensagens(mensagemsDoChat);
        chatService.save(chat);

        return mensagemRepository.save(mensagem);
    }

    @Override
    public Mensagem findById(Long id) {
        return mensagemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
    }

    @Override
    public List<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    @Override
    public Mensagem update(Long id, Mensagem mensagemAtualizado) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Mensagem mensagem = mensagemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Item não encontrado: " + id));

        mensagemRepository.delete(mensagem);
    }
}
