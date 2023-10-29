package com.register.registertest.dto;

import com.register.registertest.entity.BoardEntity;
import com.register.registertest.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity(){
        BoardEntity build = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDTO(Long id,String title,String content,String writer,LocalDateTime createdDate,LocalDateTime modifiedDate){
        this.id=id;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
    }


}
