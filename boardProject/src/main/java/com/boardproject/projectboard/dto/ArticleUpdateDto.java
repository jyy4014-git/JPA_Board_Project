package com.boardproject.projectboard.dto;

import com.boardproject.projectboard.domain.Article;
import com.boardproject.projectboard.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


public record ArticleUpdateDto(

        String title,
        String content,
        String hashtagDto

) {
// of : 여러 매개 변수를 받아서 객체를 생성
    public static ArticleUpdateDto of(String title, String content, String hashtagDtos) {
        return new ArticleUpdateDto(title, content, hashtagDtos);
    }
}