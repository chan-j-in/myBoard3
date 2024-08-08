package com.example.board3.service;

import com.example.board3.dto.BoardListResponseDto;
import com.example.board3.dto.BoardRequestDto;
import com.example.board3.dto.BoardResponseDto;
import com.example.board3.entity.Board;
import com.example.board3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    public List<BoardListResponseDto> findAllBoard() {
        try {
            List<Board> boardList = boardRepository.findAll();

            List<BoardListResponseDto> responseDtoList = boardList.stream()
                    .map(BoardListResponseDto::new)
                    .toList();
            return responseDtoList;
        } catch (Exception e) {
            throw new IllegalArgumentException("DB Empty Data Exception");
        }
    }

    public BoardResponseDto findOneBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("조회 실패")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board.update(requestDto);
        return board.getId();
    }

    @Transactional
    public Long deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return id;
    }

}
