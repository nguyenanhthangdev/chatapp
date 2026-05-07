package com.chatapp.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ChatRoomDTO {
    private UUID id;
    private String name;
    private UUID otherUserId;
    private String otherUsername;
}