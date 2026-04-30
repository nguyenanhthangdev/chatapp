package com.chatapp.service;

import com.chatapp.entity.ChatRoom;
import com.chatapp.repository.ChatRoomRepository;
import com.chatapp.repository.ChatRoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomUserRepository chatRoomUserRepository;

    public ChatRoom createChatRoom() {
        ChatRoom room = new ChatRoom();
        return chatRoomRepository.save(room);
    }
}