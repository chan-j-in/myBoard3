package com.example.board3.dto;

import com.example.board3.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class BoardListResponseDto {

    private String title;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public BoardListResponseDto(Board board) {
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
    public BoardListResponseDto(Optional<Board> board) {
        this.title = board.get().getTitle();
        this.createdAt = board.get().getCreatedAt();
        this.modifiedAt = board.get().getModifiedAt();
    }
}
