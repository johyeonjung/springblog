package me.johyeonjung.myspringblog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.dto.ArticleRequest;
import me.johyeonjung.myspringblog.dto.ArticleResponse;
import me.johyeonjung.myspringblog.security.UserPrincipal;
import me.johyeonjung.myspringblog.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 * 인증 연동 전제:
 *  - 지금은 authorId/currentUserId를 파라미터로 받지만,
 *    실제 서비스에서는 SecurityContext에서 꺼내는 방식으로 바꾸면 더 안전함.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponse> create(
            //요청 바디가 아닌 로그인한 사용자 정보에서 자동으로 꺼냄
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody @Valid ArticleRequest req) {
        ArticleResponse created = articleService.create(req, user.getId());
        return ResponseEntity.created(URI.create("/api/articles/" +created.getId()))
                .body(created);

    }
    //태그에 해당되는 글을 찾음
    @GetMapping(/{tag})
    public ResponseEntity<ArticleResponse> findTag(@PathVariable String tag) {
        return ResponseEntity.ok(articleService.findByTag(tag));
    }


    //ID 값에 해당하는 글 하나를 찾음
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.get(id));
    }
    //글 전체를 조회하는 메서드 호출
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> list() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> update(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody @Valid ArticleRequest req
    ) {
        return ResponseEntity.ok(articleService.update(id, user.getId(), req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal user
    ) {
        articleService.delete(id, user.getId());
        return ResponseEntity.noContent().build();
    }


}
