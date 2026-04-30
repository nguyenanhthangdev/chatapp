package com.chatapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_room_users")
@Getter
@Setter
public class ChatRoomUser {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID chatRoomId;

    private UUID userId;

    private LocalDateTime joinedAt = LocalDateTime.now();
}