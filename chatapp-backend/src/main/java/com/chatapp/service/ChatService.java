package com.chatapp.service;

import com.chatapp.entity.ChatRoom;
import com.chatapp.repository.ChatRoomRepository;
import com.chatapp.repository.ChatRoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom() {
        ChatRoom room = new ChatRoom();
        return chatRoomRepository.save(room);
    }

    public ChatService(ChatRoomRepository chatRepository) {
        this.chatRoomRepository = chatRepository;
    }

    public List<ChatRoom> getAllChats() {
        return chatRoomRepository.findAll();
    }
}