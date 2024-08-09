package com.example.board3.controller;

import com.example.board3.dto.CommentRequestDto;
import com.example.board3.dto.CommentResponseDto;
import com.example.board3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{id}/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @PathVariable("id") Long id) {
        return commentService.createComment(requestDto, id);
    }

    @GetMapping("/boards/{id}/comments-list")
    public List<CommentResponseDto> getAllComments(@PathVariable("id") Long id) {
        return commentService.readAllComments(id);
    }
}
