package com.chatapp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageDTO {
    private UUID id;
    private UUID chatRoomId;
    private UUID senderId;
    private String content;
    private String type; // TEXT, IMAGE
    private String status; // SENT, SEEN
    private String username;
    private LocalDateTime createdAt;
}
