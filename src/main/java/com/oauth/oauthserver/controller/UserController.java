package com.oauth.oauthserver.controller;

import com.oauth.oauthserver.dto.UserDetailsDto;
import com.oauth.oauthserver.modal.UserEntity;
import com.oauth.oauthserver.services.JPAUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private JPAUserDetailsService jpaUserDetailsService;


    @GetMapping("/test")
    public ResponseEntity<UserDetailsDto> test() {
        return new ResponseEntity<>(new UserDetailsDto(new UserEntity()) , HttpStatus.OK );
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDto> addUser(@RequestBody UserDetailsDto dto) {
        jpaUserDetailsService.createUser(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/secured")
    public ResponseEntity<String> secured() {
        return new ResponseEntity<>("Secured Connection" , HttpStatus.OK );
    }

}
