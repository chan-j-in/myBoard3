package com.example.board3.service;

import com.example.board3.dto.CommentRequestDto;
import com.example.board3.dto.CommentResponseDto;
import com.example.board3.entity.Board;
import com.example.board3.entity.Comment;
import com.example.board3.repository.BoardRepository;
import com.example.board3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        Comment comment = new Comment(requestDto, board);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public List<CommentResponseDto> readAllComments(Long id) {
        return commentRepository.findAllByBoardId(id);
    }
}
