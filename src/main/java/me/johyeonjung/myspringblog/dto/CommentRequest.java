package me.johyeonjung.myspringblog.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest (
    @NotBlank String content
) {}
