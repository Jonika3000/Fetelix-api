package com.example.fetelix.controllers;

import com.example.fetelix.dto.account.AuthResponseDto;
import com.example.fetelix.dto.account.GoogleAuthDto;
import com.example.fetelix.dto.account.LoginDto;
import com.example.fetelix.dto.account.RegisterDto;
import com.example.fetelix.services.AccountService;
import com.example.fetelix.services.GoogleApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//            var auth = service.login(dto);
            var user = googleApiService.getUserInfo(dto.getAccess_token());
            var auth = AuthResponseDto.builder().build();
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
