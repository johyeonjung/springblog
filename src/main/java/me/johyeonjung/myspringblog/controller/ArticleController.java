package me.johyeonjung.myspringblog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.johyeonjung.myspringblog.dto.ArticleRequest;
import me.johyeonjung.myspringblog.dto.ArticleResponse;
import me.johyeonjung.myspringblog.service.ArticleService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ArticleResponse> create(@RequestBody @Valid ArticleRequest req) {
        ArticleResponse created = articleService.create(req);
        return ResponseEntity.created(URI.create("/api/articles/" +created.getId()))
                .body(created);


    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> list() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> update(
            @PathVariable Long id,
            @RequestParam Long currentUserId,
            @RequestBody @Valid ArticleRequest req
    ) {
        return ResponseEntity.ok(articleService.update(id, currentUserId, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestParam Long currentUserId
    ) {
        articleService.delete(id, currentUserId);
        return ResponseEntity.noContent().build();
    }
}
