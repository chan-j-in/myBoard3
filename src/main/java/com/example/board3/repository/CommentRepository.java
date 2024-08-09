package com.example.board3.repository;

import com.example.board3.dto.CommentResponseDto;
import com.example.board3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new com.example.board3.dto.CommentResponseDto(c.id, c.content) " +
            "FROM Comment c WHERE c.board.id = :boardId")
    List<CommentResponseDto> findAllByBoardId(@Param("boardId") Long boardId);
}
