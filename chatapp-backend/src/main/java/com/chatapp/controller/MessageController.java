package com.chatapp.controller;

import com.chatapp.dto.SendMessageRequest;
import com.chatapp.entity.Message;
import com.chatapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public Message send(@RequestBody SendMessageRequest request) {
        return messageService.sendMessage(
                request.getChatRoomId(),
                request.getSenderId(),
                request.getContent()
        );
    }

    @GetMapping("/{roomId}")
    public List<Message> getMessages(@PathVariable UUID roomId) {
        return messageService.getMessages(roomId);
    }
}