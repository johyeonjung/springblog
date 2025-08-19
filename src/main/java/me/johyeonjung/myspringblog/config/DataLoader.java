package me.johyeonjung.myspringblog.config;


import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.User;
import me.johyeonjung.myspringblog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if(userRepository.count() == 0) {
            userRepository.save(
                    User.builder()
                            .username("tester")
                            .displayName("테스터")
                            .password("pass")
                            .build()
            );
        }
    }
}
