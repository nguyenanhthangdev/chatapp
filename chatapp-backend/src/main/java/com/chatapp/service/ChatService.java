package com.chatapp.service;

import com.chatapp.dto.ChatRoomDTO;
import com.chatapp.entity.ChatRoom;
import com.chatapp.entity.User;
import com.chatapp.repository.ChatRoomRepository;
import com.chatapp.repository.ChatRoomUserRepository;
import com.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoom createChatRoom() {
        ChatRoom room = new ChatRoom();
        return chatRoomRepository.save(room);
    }

    public List<ChatRoom> getAllChats() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom createPrivateRoom(UUID u1, UUID u2) {

        ChatRoom room = new ChatRoom();
        room.setUser1(u1);
        room.setUser2(u2);
        room.setIsGroup(false);

        return chatRoomRepository.save(room);
    }

    public ChatService(ChatRoomRepository chatRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public List<ChatRoomDTO> getMyRooms(UUID userId) {

        List<ChatRoom> rooms = chatRoomRepository.findByUser1OrUser2(userId, userId);

        return rooms.stream().map(room -> {

            ChatRoomDTO dto = new ChatRoomDTO();
            dto.setId(room.getId());

            UUID otherId = room.getUser1().equals(userId)
                    ? room.getUser2()
                    : room.getUser1();

            User otherUser = userRepository.findById(otherId).orElse(null);

            dto.setOtherUsername(
                    otherUser != null ? otherUser.getUsername() : "Unknown"
            );

            return dto;
        }).toList();
    }
}