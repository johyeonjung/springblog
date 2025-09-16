package me.johyeonjung.myspringblog.service;

import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.User;
import me.johyeonjung.myspringblog.dto.AddUserRequest;
import me.johyeonjung.myspringblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public Long save(AddUserRequest dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .displayName(dto.getDisplayName()) // 닉네임 저장
                .build();
        return userRepository.save(user).getId();
    }
}