package com.example.board3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String username;

    private String password;

    private String passwordCheck;
}
