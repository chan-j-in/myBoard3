package com.example.board3.controller;

import com.example.board3.UserDetailsImpl;
import com.example.board3.dto.BoardListResponseDto;
import com.example.board3.dto.BoardRequestDto;
import com.example.board3.dto.BoardResponseDto;
import com.example.board3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public BoardResponseDto createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(userDetails.getUser(), requestDto);
    }

    @GetMapping("/boards")
    public List<BoardListResponseDto> getAllBoards() {
        return boardService.findAllBoard();
    }

    @GetMapping("/boards/{id}")
    public BoardResponseDto getOneBoard(@PathVariable("id") Long id) {
        return boardService.findOneBoard(id);
    }

    @PutMapping("/boards/{id}")
    public Long updateBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(userDetails.getUser(), id, requestDto);
    }

    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        return boardService.deleteBoard(userDetails.getUser(), id);
    }

    @GetMapping("/boards/check/{id}/{inputPassword}")
    public boolean checkPassword(@PathVariable Long id, @PathVariable String inputPassword) {
        return boardService.checkPassword(id, inputPassword);
    }
}
