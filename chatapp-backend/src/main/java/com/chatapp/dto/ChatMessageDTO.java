package com.chatapp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessageDTO {
    private UUID chatRoomId;
    private UUID senderId;
    private String username;
    private String content;
    private String type;
}