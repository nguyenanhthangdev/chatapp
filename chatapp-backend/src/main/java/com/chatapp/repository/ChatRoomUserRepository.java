package com.chatapp.repository;

import com.chatapp.entity.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, UUID> {}