package me.johyeonjung.myspringblog.repository;

import me.johyeonjung.myspringblog.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("""
        select a
        from Article a
        where lower(a.title)   like lower(concat('%', :q, '%'))
           or lower(a.content) like lower(concat('%', :q, '%'))
    """)
    Page<Article> search(@Param("q") String q, Pageable pageable);
}