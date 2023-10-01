package com.example.fetelix.services;

import com.example.fetelix.configuration.security.JwtService;
import com.example.fetelix.dto.account.AuthResponseDto;
import com.example.fetelix.dto.account.LoginDto;
import com.example.fetelix.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto login(LoginDto dto) {
        var user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();
        var isValid = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if(!isValid)
            throw new UsernameNotFoundException("User not found");

        var jwtToken = jwtService.generateAccessToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
