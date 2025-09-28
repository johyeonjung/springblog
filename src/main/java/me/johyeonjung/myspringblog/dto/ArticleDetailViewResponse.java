package me.johyeonjung.myspringblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.johyeonjung.myspringblog.domain.ArticleTag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
public class ArticleDetailViewResponse {
    //추후 댓글까지 넣어야 함
    //단일 게시글 상세 조회 시 사용
    private Long id;
    private String title;
    Set<ArticleTag> tagNames;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ArticleDetailViewResponse(ArticleResponse dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.tagNames = dto.getTagNames();
        this.content = dto.getContent();
        this.authorName = dto.getAuthorName();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }
}
