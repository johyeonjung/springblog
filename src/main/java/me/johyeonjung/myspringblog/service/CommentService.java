package me.johyeonjung.myspringblog.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.Comment;
import me.johyeonjung.myspringblog.domain.User;
import me.johyeonjung.myspringblog.dto.CommentRequest;
import me.johyeonjung.myspringblog.dto.CommentResponse;
import me.johyeonjung.myspringblog.repository.ArticleRepository;
import me.johyeonjung.myspringblog.repository.CommentRepository;
import me.johyeonjung.myspringblog.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;


    //댓글 작성
    public CommentResponse add(Long articleId, Long userId, CommentRequest request) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

       Comment comment = Comment.builder()
               .article(article)
               .author(user)
               .content(request.content())
               .build();

       Comment saved = commentRepository.save(comment);
       return CommentResponse.fromEntity(saved);
    }

    //댓글 목록 조회
    public List<CommentResponse> getComments(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(EntityNotFoundException::new);

        return commentRepository.findByArticleOrderByCreatedAtDesc(article)
                .stream()
                .map(CommentResponse::fromEntity)
                .toList();


    }

    //댓글 수정
    @Transactional
    public void edit(Long commentId, Long userId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(EntityNotFoundException::new);
        if(!comment.isAuthor(userId)) {
            throw new IllegalStateException("본인 댓글만 수정할 수 있습니다");
        }
        comment.changeContent(content);
    }

    //댓글 삭제
    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(EntityNotFoundException::new);
        if(!comment.isAuthor(userId)) {
            throw new IllegalStateException("본인 댓글만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }





}

