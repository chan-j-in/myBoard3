package com.example.board3.service;

import com.example.board3.dto.LoginRequestDto;
import com.example.board3.dto.UserRequestDto;
import com.example.board3.entity.User;
import com.example.board3.jwt.JwtTokenProvider;
import com.example.board3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String PATTERN = "^[a-zA-Z0-9]*$";
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 12;
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 32;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public boolean checkSignupValueCondition(UserRequestDto requestDto){

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        boolean checkValueCondition = true;
        if( !(Pattern.matches(PATTERN,username) && username.length()>=MIN_USERNAME_LENGTH && username.length()<=MAX_USERNAME_LENGTH) ){
            System.out.println("닉네임이 잘못되었습니다");
            checkValueCondition=false;
        }
        else if( !(Pattern.matches(PATTERN,password) && password.length()>=MIN_PASSWORD_LENGTH && password.length()<=MAX_PASSWORD_LENGTH) ){
            System.out.println("비밀번호가 잘못되었습니다");
            checkValueCondition=false;
        }
        else if( !password.equals(passwordCheck) ){
            System.out.println("비밀번호 확인과 일치하지 않습니다");
            checkValueCondition=false;
        }
        return checkValueCondition;
    }

    @Transactional
    public UserRequestDto register(UserRequestDto requestDto) {

        if(!checkSignupValueCondition(requestDto)) {
            throw new IllegalArgumentException("회원가입 정보가 정확하지 않습니다.");
        };

        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 id가 존재합니다.");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = new User(requestDto);
        userRepository.save(user);
        return requestDto;
    }

    @Transactional
    public String login(LoginRequestDto requestDto) {
        User member = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원입니다."));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(member.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername());
    }
}
