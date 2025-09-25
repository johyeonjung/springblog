package me.johyeonjung.myspringblog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Comment;
import me.johyeonjung.myspringblog.dto.CommentRequest;
import me.johyeonjung.myspringblog.dto.CommentResponse;
import me.johyeonjung.myspringblog.repository.UserRepository;
import me.johyeonjung.myspringblog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/comments")
public class CommentController {

    private final CommentService commentService;
    //댓글 작성
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long articleId,
            @AuthenticationPrincipal(expression = "id") Long userId,
            @RequestBody @Valid CommentRequest request
    ) {
        CommentResponse response = commentService.add(articleId, userId, request);
        return ResponseEntity.ok(response);
    }

    //댓글 목록 조회
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long articleId) {
        return ResponseEntity.ok(commentService.getComments(articleId));
    }

    //댓글 수정

    @ PutMapping("/{commentId}")
    public ResponseEntity<Void> editComment(
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal(expression = "id") Long userId,
            @RequestBody @Valid CommentRequest request
    ) {
        commentService.edit(commentId, userId, request.content());
        return ResponseEntity.noContent().build();
    }

    //댓글 삭제
    @DeleteMapping ("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal(expression = "id") Long userId
    ) {
        commentService.delete(commentId,userId);
        return ResponseEntity.noContent().build();
    }


}
