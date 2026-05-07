package com.chatapp.repository;

import com.chatapp.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {

    List<FriendRequest> findByReceiverIdAndStatus(UUID receiverId, FriendRequest.Status status);
}