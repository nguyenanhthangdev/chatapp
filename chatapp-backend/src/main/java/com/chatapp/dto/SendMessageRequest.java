package com.chatapp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SendMessageRequest {
    private UUID chatRoomId;
    private UUID senderId;
    private String content;
}