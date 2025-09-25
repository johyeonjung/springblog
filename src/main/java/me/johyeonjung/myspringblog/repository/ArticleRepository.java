package me.johyeonjung.myspringblog.repository;

import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Object> findByTag(String tag);
}