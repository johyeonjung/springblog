package me.johyeonjung.myspringblog.controller;

import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.domain.Article;
import me.johyeonjung.myspringblog.dto.*;
import me.johyeonjung.myspringblog.service.ArticleService;
import me.johyeonjung.myspringblog.service.BlogService;
import me.johyeonjung.myspringblog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;
    private final CommentService commentService;


    @GetMapping("/articles")
    public String getArticle(Model model) {
        List<ArticleListViewResponse> articles = articleService.getAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleResponse article = articleService.get(id);
        model.addAttribute("article", new ArticleDetailViewResponse(article));

        //댓글 목록 추가
        List<CommentResponse> comments = commentService.getComments(id);
        model.addAttribute("comments", comments);

        return "article";
    }
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required=false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleDetailViewResponse());
        } else {
            ArticleResponse article = articleService.get(id);
            model.addAttribute("article", new ArticleDetailViewResponse(article));
        }


        return "newArticle";
    }
}
