package br.com.fesvip.crm.controller;

import br.com.fesvip.crm.entity.Chat;
import br.com.fesvip.crm.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<Chat> save(@RequestBody Chat chat) {
        Chat savedChat = chatService.save(chat);
        return new ResponseEntity<>(savedChat, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> findById(@PathVariable Long id) {
        Chat chat = chatService.findById(id);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Chat>> findAll() {
        List<Chat> chats = chatService.findAll();
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> update(@PathVariable Long id, @RequestBody Chat chatAtualizado) {
        Chat updatedChat = chatService.update(id, chatAtualizado);
        return new ResponseEntity<>(updatedChat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chatService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
