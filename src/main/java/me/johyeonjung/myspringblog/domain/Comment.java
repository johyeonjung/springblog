package me.johyeonjung.myspringblog.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="author_id")
    private User author;

    //어느 글의 댓글인지
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="article_id")
    private Article article;

    //내용
    @Column(nullable=false, length=1000)
    private String content;

    //본인 댓글인지 권한 체크용
    public boolean isAuthor(Long userId) {
        return author !=null && author.getId().equals(userId);
    }

    //내용 수정시 사용할 도메인 메서드
    public void changeContent(String content) {
        this.content = content;
    }
}
