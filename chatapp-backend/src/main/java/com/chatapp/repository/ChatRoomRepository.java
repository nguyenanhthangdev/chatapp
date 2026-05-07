package com.chatapp.repository;

import com.chatapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    List<ChatRoom> findByUser1OrUser2(UUID user1, UUID user2);
}