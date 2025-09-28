package me.johyeonjung.myspringblog.repository;

import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleTagRepository extends JpaRepository<Article, Long> {
    Optional<Tag> findByTag(String tag);

}