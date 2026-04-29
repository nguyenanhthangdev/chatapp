package com.chatapp.service;

import com.chatapp.dto.CreateUserRequest;
import com.chatapp.dto.UserResponse;
import com.chatapp.entity.User;
import com.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // ⚠️ chưa mã hóa (sẽ làm ở auth)
                .build();

        userRepository.save(user);

        return mapToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .isOnline(user.getIsOnline())
                .lastSeen(user.getLastSeen())
                .build();
    }
}