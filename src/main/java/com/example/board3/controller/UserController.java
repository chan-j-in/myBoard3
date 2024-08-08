package com.example.board3.controller;

import com.example.board3.UserDetailsImpl;
import com.example.board3.dto.LoginRequestDto;
import com.example.board3.dto.UserRequestDto;
import com.example.board3.entity.User;
import com.example.board3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserRequestDto register(@RequestBody UserRequestDto requestDto) {
        userService.register(requestDto);
        return requestDto;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @GetMapping("/api/userinfo")
    @ResponseBody
    public String getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails!=null) {
            System.out.println("로그인 된 상태입니다.");
            return userDetails.getUser().getUsername();
        }
        return "확인 불가";
    }
}
