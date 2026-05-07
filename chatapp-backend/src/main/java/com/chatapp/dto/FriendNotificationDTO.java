package com.chatapp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FriendNotificationDTO {

    private String type;

    private UUID roomId;

    private UUID senderId;
    private String senderUsername;
}