package com.register.registertest.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends TimeEntity{
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 100,nullable = false)
    private String writer;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();

//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL) // 댓글과의 One-to-Many 관계 설정
//
//    private List<CommentEntity> comments = new ArrayList<>();


    @Builder
    public BoardEntity(Long id,String title,String content,String writer){
        this.id=id;
        this.title=title;
        this.content=content;
        this.writer=writer;
    }
}
