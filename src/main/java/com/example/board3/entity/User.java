package com.example.board3.entity;

import com.example.board3.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    public User(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
