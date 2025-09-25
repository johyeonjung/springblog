package me.johyeonjung.myspringblog.dto;

import jakarta.validation.constraints.NotBlank;

//record 문법
// 댓글 작성, 수정시 사용할 DTO
public record CommentRequest (
    @NotBlank String content
) {}
