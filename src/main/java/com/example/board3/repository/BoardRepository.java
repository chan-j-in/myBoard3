package com.example.board3.repository;

import com.example.board3.dto.BoardListResponseDto;
import com.example.board3.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<BoardListResponseDto> findAllByOrderByModifiedAtDesc();
}
