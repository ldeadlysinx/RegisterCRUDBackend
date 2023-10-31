package com.register.registertest.repository;

import com.register.registertest.entity.BoardEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Cacheable("boardsByTitleContaining")
    List<BoardEntity> findByTitleContaining(String keyword);
    List<BoardEntity> findAll();
    List<BoardEntity> findByWriter(String writer);

    @Query("SELECT b FROM BoardEntity b WHERE b.title LIKE %:keyword%")
    List<BoardEntity> findCustomByTitleContaining(@Param("keyword") String keyword);

}
