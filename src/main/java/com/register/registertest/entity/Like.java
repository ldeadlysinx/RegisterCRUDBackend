package com.register.registertest.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;



@Entity
@Getter
@Setter
@Table(name = "like_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public Long getBoardId() {
        return boardEntity != null ? boardEntity.getId() : null;
    }

}
