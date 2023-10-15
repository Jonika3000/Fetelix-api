package com.example.fetelix.controllers;

import com.example.fetelix.constants.Roles;
import com.example.fetelix.dto.account.AuthResponseDto;
import com.example.fetelix.dto.account.GoogleAuthDto;
import com.example.fetelix.dto.account.LoginDto;
import com.example.fetelix.dto.account.RegisterDto;
import com.example.fetelix.models.User;
import com.example.fetelix.models.UserRole;
import com.example.fetelix.repositories.RoleRepository;
import com.example.fetelix.repositories.UserRepository;
import com.example.fetelix.repositories.UserRoleRepository;
import com.example.fetelix.services.AccountService;
import com.example.fetelix.services.GoogleApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;
    private final GoogleApiService googleApiService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto dto) {
        try {
            var auth = service.login(dto);
            return ResponseEntity.ok(auth);
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("google")
    public ResponseEntity<AuthResponseDto> googleLogin(@RequestBody GoogleAuthDto dto) {
        try {
            var userInfo = googleApiService.getUserInfo(dto.getAccess_token());
            var userAuth = userRepository.findByEmail(userInfo.getEmail());
            User user = null;
            if(!userAuth.isPresent()) {
                user = User
                        .builder()
                        .family_name(userInfo.getGiven_name())
                        .given_name(userInfo.getGiven_name())
                        .email(userInfo.getEmail())
                        .picture(userInfo.getPicture())
                        .locale(userInfo.getLocale())
                        .name(userInfo.getName())
                        .phone("00 00 000 00 00")
                        .password(passwordEncoder.encode("123456"))
                        .isGoogleAuth(true)
                        .build();
                userRepository.save(user);
                var role = roleRepository.findByName(Roles.Admin);
            var ur = UserRole
                    .builder()
                    .role(role)
                    .user(user)
                    .build();
            userRoleRepository.save(ur);
            }
            else
                user=userAuth.get();
            var auth = service.getUserToken(user);
            return ResponseEntity.ok(auth);
        }catch(Exception ex) {
            System.out.println("---Error----"+ ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
//    @PostMapping("register")
//    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto dto) {
//
//    }
}
