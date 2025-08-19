package me.johyeonjung.myspringblog.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.User;
import me.johyeonjung.myspringblog.dto.ArticleRequest;
import me.johyeonjung.myspringblog.dto.ArticleResponse;
import me.johyeonjung.myspringblog.repository.ArticleRepository;
import me.johyeonjung.myspringblog.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ArticleResponse create(ArticleRequest req) {
        User author = userRepository.findById(req.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("작성자 정보를 찾을 수 없음"));

        Article article = Article.builder()
                .author(author)
                .title(req.getTitle())
                .content(req.getContent())
                .build();

        Article saved = articleRepository.save(article);
        return toResponse(saved);
    }
    public ArticleResponse get(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없음"));
        return toResponse(article);
    }

    public List<ArticleResponse> getAll() {
        return articleRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ArticleResponse update(Long id, Long currentUserId, ArticleRequest req) {
        Article article = articleRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("게시글을 찾을 수 없음"));

        if(!article.isAuthor(currentUserId)) {
            throw new IllegalStateException("본인 글만 수정할 수 있습니다.");
        }

        article.update(req.getTitle(), req.getContent());
        return toResponse(article);
    }

    @Transactional
    public void delete(Long id, Long currentUserId) {
        Article article = articleRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("게시글을 찾을 수 없습니다"));

        if (!article.isAuthor(currentUserId)) {
            throw new IllegalStateException("본인 글만 삭제할 수 있습니다.");
        }

        articleRepository.delete(article);
    }

    private ArticleResponse toResponse(Article a) {
        return ArticleResponse.builder()
                .id(a.getId())
                .authorId(a.getAuthor().getId())
                .authorName(a.getAuthor().getUsername())
                .title(a.getTitle())
                .content(a.getContent())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();
    }

}
