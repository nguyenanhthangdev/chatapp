package com.chatapp.service;

import com.chatapp.entity.FriendRequest;
import com.chatapp.repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.chatapp.dto.FriendNotificationDTO;
import com.chatapp.entity.ChatRoom;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendRequestService {

    private final FriendRequestRepository repo;
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    // GỬI LỜI MỜI
    public FriendRequest send(UUID senderId, UUID receiverId) {

        FriendRequest req = new FriendRequest();
        req.setSenderId(senderId);
        req.setReceiverId(receiverId);
        req.setStatus(FriendRequest.Status.PENDING);

        FriendRequest saved = repo.save(req);

// 🔥 SOCKET REALTIME
        FriendNotificationDTO dto = new FriendNotificationDTO();

        dto.setType("FRIEND_REQUEST");
        dto.setSenderId(senderId);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + receiverId,
                dto
        );

        return saved;
    }

    // LẤY DANH SÁCH PENDING
    public List<FriendRequest> getPending(UUID userId) {
        return repo.findByReceiverIdAndStatus(userId, FriendRequest.Status.PENDING);
    }

    // ACCEPT
    public void accept(UUID requestId) {
        FriendRequest req = repo.findById(requestId).orElseThrow();

        req.setStatus(FriendRequest.Status.ACCEPTED);
        repo.save(req);

        ChatRoom room = chatService.createPrivateRoom(
                req.getSenderId(),
                req.getReceiverId()
        );

// 🔥 SOCKET REALTIME
        FriendNotificationDTO dto = new FriendNotificationDTO();

        dto.setType("NEW_CHAT_ROOM");
        dto.setRoomId(room.getId());

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + req.getSenderId(),
                dto
        );

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + req.getReceiverId(),
                dto
        );
    }

    // REJECT
    public void reject(UUID requestId) {
        FriendRequest req = repo.findById(requestId).orElseThrow();

        req.setStatus(FriendRequest.Status.REJECTED);
        repo.save(req);
    }
}