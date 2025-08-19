package me.johyeonjung.myspringblog.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name="articles",
        indexes={
               @Index(name="idx_article_created_at", columnList="created_at"),
                @Index(name="idx_article_title", columnList="title")
        }
)

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Article extends BaseTimeEntity {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;

        //작성자
        @ManyToOne(fetch=FetchType.LAZY, optional=false)
        @JoinColumn(name="author_id")
        private User author;

        //제목
        @Column(nullable = false, length = 200)
        private String title;

        //내용
        @Column(nullable=false, columnDefinition = "TEXT")
        private String content;

        //편의 메서드
        public void update(String title, String content) {
                this.title=title;
                this.content=content;
        }

        public boolean isAuthor(Long userId) {
                return author != null && author.getId().equals(userId);
        }

}
