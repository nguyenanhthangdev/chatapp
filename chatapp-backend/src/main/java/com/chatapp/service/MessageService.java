package com.chatapp.service;

import com.chatapp.entity.Message;
import com.chatapp.entity.MessageStatus;
import com.chatapp.entity.MessageType;
import com.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message sendMessage(UUID chatRoomId, UUID senderId, String content) {

        Message msg = new Message();
        msg.setChatRoomId(chatRoomId);
        msg.setSenderId(senderId);
        msg.setContent(content);
        msg.setType(MessageType.TEXT);
        msg.setStatus(MessageStatus.SENT);

        return messageRepository.save(msg);
    }
}
