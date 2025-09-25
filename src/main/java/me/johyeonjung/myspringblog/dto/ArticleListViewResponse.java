package me.johyeonjung.myspringblog.dto;

import lombok.Getter;
import me.johyeonjung.myspringblog.domain.Article;
//여러 개의 게시글을 간단하게 표시하는 목록 조회용 DTO
@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;

    public ArticleListViewResponse(ArticleResponse dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.authorName = dto.getAuthorName();
    }
}
