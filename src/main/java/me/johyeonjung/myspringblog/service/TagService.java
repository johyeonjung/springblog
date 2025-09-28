package me.johyeonjung.myspringblog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Tag;
import me.johyeonjung.myspringblog.repository.ArticleTagRepository;
import me.johyeonjung.myspringblog.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class TagService {
    private final ArticleTagRepository articleTagRepository;

    public Optional<Tag> findByTag(String tag) {
        return articleTagRepository.findByTag(tag);
    }

    public Tag save(String tag) {

        return articleTagRepository.save(
                Tag.builder()
                        //Tag 클래스에 builder 넣어야 동작
                        .tag(tag)
                        .build());
    }


}