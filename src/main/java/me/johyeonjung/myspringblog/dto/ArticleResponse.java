package me.johyeonjung.myspringblog.dto;

import lombok.*;
import me.johyeonjung.myspringblog.domain.Article;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ArticleResponse {
    private  Long id;
    private Long authorId;
    private String authorName;
    private String title;
    private String tag;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.tag = article.getTag();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.authorName = article.getAuthor().getUsername();

    }
}
