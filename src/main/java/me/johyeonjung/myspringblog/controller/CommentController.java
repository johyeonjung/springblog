package me.johyeonjung.myspringblog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Comment;
import me.johyeonjung.myspringblog.dto.CommentRequest;
import me.johyeonjung.myspringblog.repository.UserRepository;
import me.johyeonjung.myspringblog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String addComment(@PathVariable Long articleId,
                             @Valid CommentRequest request,
                             @AuthenticationPrincipal(expression="id")  Long userId) {

        commentService.add(articleId, userId, request);
        return "redirect:/articles/"+articleId;
    }
    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long articleId,
                              @PathVariable Long commentId,
                              @RequestParam String content,
                              @AuthenticationPrincipal(expression="id") Long userId) {
        commentService.edit(commentId, userId, content);
        return "redirect:/articles/"+articleId;
    }
    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long articleId,
                                @PathVariable Long commentId,
                                @AuthenticationPrincipal(expression="id") Long userId) {
        commentService.delete(commentId, userId);
        return "redirect:/articles/"+articleId;
    }




}
