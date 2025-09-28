package me.johyeonjung.myspringblog.service;

import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.ArticleTag;
import me.johyeonjung.myspringblog.domain.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleTagService {

    private final TagService tagService;
    private final ArticleTagRepository articleTagRepository;

    public Long saveTag(Article article, Tag tag) {
        return articleTagRepository.save(new ArticleTag(article,tag)).getId();
    }

}
