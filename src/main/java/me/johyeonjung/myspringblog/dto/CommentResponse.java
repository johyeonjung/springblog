package me.johyeonjung.myspringblog.dto;

// 서버 -> 클라이언트

import lombok.Builder;
import lombok.Getter;
import me.johyeonjung.myspringblog.domain.Comment;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {
    private Long id;
    private Long authorId;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


//엔티티 -> dto 변환 메서드
public static CommentResponse fromEntity(Comment comment) {
    return CommentResponse.builder()
            .id(comment.getId())
            .authorId(comment.getAuthor().getId())
            .content(comment.getContent())
            .authorName(comment.getAuthor().getDisplayName())
            .createdAt(comment.getCreatedAt())
            .updatedAt(comment.getUpdatedAt())
            .build();
    }
}
