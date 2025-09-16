package me.johyeonjung.myspringblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
//게시글 작성/ 수정 폼 화면에 데이터를 보여주기 위한 dto
public class ArticleFormResponse
{   private Long id;
    private String title;
    private String content;


    public ArticleFormResponse(ArticleResponse dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
