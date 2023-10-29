package com.register.registertest.repository;

import com.register.registertest.entity.BoardEntity;
import com.register.registertest.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

    List<CommentEntity> findByBoardEntityId_Id(Long boardId);

    List<CommentEntity> findByCommentWriter(String writer);

    long countByBoardEntityId(Long boardId);
}
