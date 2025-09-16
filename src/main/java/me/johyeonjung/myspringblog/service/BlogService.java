package me.johyeonjung.myspringblog.service;

import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.User;
import me.johyeonjung.myspringblog.dto.ArticleRequest;
import me.johyeonjung.myspringblog.repository.ArticleRepository;
import me.johyeonjung.myspringblog.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class BlogService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    //블로그 글 추가 메서드
    public Article save(ArticleRequest request) {
        //작성자 엔티티 조회
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("작성자가 존재하지 않음"));
        //작성자 엔티티를 넘겨서 article 생성
        return articleRepository.save(request.toEntity(author));
    }


}
