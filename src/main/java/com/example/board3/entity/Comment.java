package com.example.board3.entity;

import com.example.board3.dto.BoardRequestDto;
import com.example.board3.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    public Comment(CommentRequestDto requestDto, Board board) {
        this.content = requestDto.getContent();
        this.board = board;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
