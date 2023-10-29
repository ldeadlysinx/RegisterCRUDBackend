package com.register.registertest.repository;

import com.register.registertest.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
    List<BoardEntity> findAll();
    List<BoardEntity> findByWriter(String writer);
}
