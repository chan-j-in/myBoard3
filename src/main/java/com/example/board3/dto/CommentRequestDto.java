package com.example.board3.dto;

import com.example.board3.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;

    private Long boardId;

    public CommentRequestDto(Comment comment) {
        this.content = comment.getContent();
        this.boardId = comment.getBoard().getId();
    }
}
