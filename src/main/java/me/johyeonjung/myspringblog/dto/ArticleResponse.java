package me.johyeonjung.myspringblog.dto;

import lombok.*;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.ArticleTag;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ArticleResponse {
    private  Long id;
    private Long authorId;
    private String authorName;
    private String title;
    Set<ArticleTag> tagNames;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.authorId = article.getAuthor().getId();
        this.tagNames =  article.getArticleTags();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.authorName = article.getAuthor().getUsername();

    }
}
