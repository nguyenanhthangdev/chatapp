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
@Table(name = "chat_rooms")
@Getter
@Setter
public class ChatRoom {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Boolean isGroup = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private UUID user1;
    private UUID user2;
}