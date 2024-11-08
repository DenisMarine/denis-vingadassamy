package com.party.Party.config;

import com.party.Party.entity.User;
import com.party.Party.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner createUserIfNotExists(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User user = new User();
                user.setPassword(passwordEncoder.encode("admin"));
                user.setEmail("admin@admin.com");
                userRepository.save(user);
            }
        };
    }
}
