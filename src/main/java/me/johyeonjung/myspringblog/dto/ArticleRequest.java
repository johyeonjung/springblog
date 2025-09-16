package me.johyeonjung.myspringblog.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.User;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ArticleRequest {
    private Long authorId;
    @NotBlank
    @Size(max=200)
    private String title;

    @NotBlank
    private String content;

    public Article toEntity(User author) {
        return Article.builder()
                .author(author) //여기서 author 필드에 User 객체 매핑
                .title(title)
                .content(content)
                .build();
    }
}
