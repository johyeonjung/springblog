package me.johyeonjung.myspringblog.dto;

import lombok.Getter;
import me.johyeonjung.myspringblog.domain.Article;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(ArticleResponse dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
