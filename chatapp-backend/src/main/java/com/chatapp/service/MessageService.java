package com.chatapp.service;

import com.chatapp.dto.ChatMessageDTO;
import com.chatapp.entity.Message;
import com.chatapp.entity.MessageStatus;
import com.chatapp.entity.MessageType;
import com.chatapp.entity.User;
import com.chatapp.repository.MessageRepository;
import com.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message sendMessage(UUID chatRoomId, UUID senderId, String content) {

        // 👇 lấy user từ DB
        User user = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message msg = new Message();
        msg.setChatRoomId(chatRoomId);
        msg.setSenderId(senderId);
        msg.setContent(content);
        msg.setType(MessageType.TEXT);
        msg.setStatus(MessageStatus.SENT);

        // 👇 set username từ DB
        msg.setUsername(user.getUsername());

        return messageRepository.save(msg);
    }

    public Message saveFromSocket(ChatMessageDTO dto) {

        User user = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message msg = new Message();
        msg.setChatRoomId(dto.getChatRoomId());
        msg.setSenderId(dto.getSenderId());
        msg.setContent(dto.getContent());
        msg.setUsername(user.getUsername());

        return messageRepository.save(msg);
    }

    public List<Message> getMessages(UUID roomId) {
        return messageRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId);
    }
}
