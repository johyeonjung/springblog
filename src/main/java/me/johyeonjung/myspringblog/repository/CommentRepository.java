package me.johyeonjung.myspringblog.repository;

import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 글 기준으로 댓글 목록 조회(최신순)
    List<Comment> findByArticleOrderByCreatedAtDesc(Article article);
}
