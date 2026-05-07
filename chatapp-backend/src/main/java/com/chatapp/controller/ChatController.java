package com.chatapp.controller;

import com.chatapp.dto.ChatMessageDTO;
import com.chatapp.dto.ChatRoomDTO;
import com.chatapp.dto.MessageDTO;
import com.chatapp.entity.ChatRoom;
import com.chatapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom() {
        return chatService.createChatRoom();
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChats() {
        return ResponseEntity.ok(chatService.getAllChats());
    }

    @GetMapping("/my/{userId}")
    public List<ChatRoomDTO> getMyRooms(@PathVariable UUID userId) {
        return chatService.getMyRooms(userId);
    }
}