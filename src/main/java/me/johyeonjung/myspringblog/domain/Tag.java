package me.johyeonjung.myspringblog.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag")
public class Tag extends Article {

    @Column(name = "tag_id")
    private Long id;

    @Column(name = "tag_name", nullable = false)
    private String tag;

    // 한 해시태그에 여러 개의 게시글이 담겨올 수 있음
    @ToString.Exclude
    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new LinkedHashSet<>();


}
