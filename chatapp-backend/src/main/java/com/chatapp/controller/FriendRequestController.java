package com.chatapp.controller;

import com.chatapp.entity.FriendRequest;
import com.chatapp.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendRequestController {

    private final FriendRequestService service;

    // GỬI LỜI MỜI
    @PostMapping("/send")
    public FriendRequest send(@RequestParam UUID senderId,
                              @RequestParam UUID receiverId) {
        return service.send(senderId, receiverId);
    }

    // DANH SÁCH REQUEST
    @GetMapping("/pending/{userId}")
    public List<FriendRequest> getPending(@PathVariable UUID userId) {
        return service.getPending(userId);
    }

    // ACCEPT
    @PostMapping("/accept/{id}")
    public void accept(@PathVariable UUID id) {
        service.accept(id);
    }

    // REJECT
    @PostMapping("/reject/{id}")
    public void reject(@PathVariable UUID id) {
        service.reject(id);
    }
}