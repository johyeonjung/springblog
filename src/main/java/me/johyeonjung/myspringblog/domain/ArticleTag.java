package me.johyeonjung.myspringblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.johyeonjung.myspringblog.repository.ArticleRepository;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
//Article과 Tag의 중간 테이블
@Getter
@NoArgsConstructor
@Entity
@Table(name = "article_tag")
public class ArticleTag extends Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;



//    public ArticleTag(Article article, Tag tag) {
//        this.article = article;
//        this.tag = tag;
//    }

}
