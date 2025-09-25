package me.johyeonjung.myspringblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleDetailViewResponse {
    //추후 댓글까지 넣어야 함
    //단일 게시글 상세 조회 시 사용
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ArticleDetailViewResponse(ArticleResponse dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.authorName = dto.getAuthorName();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }
}
