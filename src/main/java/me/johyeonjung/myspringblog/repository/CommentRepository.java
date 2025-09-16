package me.johyeonjung.myspringblog.repository;

import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleOrderByCreatedAtAsc(Article article);
}
