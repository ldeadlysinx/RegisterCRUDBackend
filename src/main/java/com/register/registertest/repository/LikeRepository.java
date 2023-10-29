package com.register.registertest.repository;

import com.register.registertest.entity.CommentEntity;
import com.register.registertest.entity.Like;
import com.register.registertest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByBoardEntityIdAndUserId(Long boardId, int userId);

    void deleteByBoardEntityIdAndUserId(Long boardId, int userId);

    Like findByBoardEntityIdAndUserId(Long boardId, int userId);

    long countByBoardEntityId(Long boardId);

    List<Like> findByUserId(int userId);
}
