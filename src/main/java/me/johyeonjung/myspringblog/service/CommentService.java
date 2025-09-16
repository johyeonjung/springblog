package me.johyeonjung.myspringblog.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.domain.Comment;
import me.johyeonjung.myspringblog.domain.User;
import me.johyeonjung.myspringblog.dto.CommentRequest;
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

    @Transactional
    public Comment add(Long articleId, Long userId, CommentRequest request) {
        Article article= articleRepository.findById(articleId)
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 글입니다"));
        User user=userRepository.findById(userId)
                .orElseThrow( () -> new EntityNotFoundException("존재하지 않는 유저입니다"));
        Comment comment=Comment.builder()
                .article(article)
                .author(user)
                .content(request.content())
                .build();
        return commentRepository.save(comment);
    }


    @Transactional(readOnly = true)
    public List<Comment> getComments(Long articleId) {
        Article article=articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 글입니다"));
        return commentRepository.findByArticleOrderByCreatedAtAsc(article);
    }

    @Transactional
    public void edit(Long commentId, Long userId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( () -> new EntityNotFoundException("존재하지 않는 댓글입니다"));
        if (!comment.isAuthor(userId)) {
            throw new IllegalStateException("본인 댓글만 수정할 수 있습니다");
        }
        comment.changeContent(content);
    }

    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( () -> new EntityNotFoundException("존재하지 않는 댓글입니다"));
        if (!comment.isAuthor(userId)) {
            throw new IllegalStateException("본인 댓글만 삭제할 수 있습니다");
        }
        commentRepository.delete(comment);
    }
}
