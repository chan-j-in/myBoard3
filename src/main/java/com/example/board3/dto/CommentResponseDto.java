package com.example.board3.dto;

import com.example.board3.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long boardId;

    private String content;

    public CommentResponseDto(Comment comment) {
        this.boardId = comment.getBoard().getId();
        this.content = comment.getContent();
    }
}
