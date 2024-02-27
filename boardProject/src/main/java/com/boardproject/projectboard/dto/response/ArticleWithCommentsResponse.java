package com.boardproject.projectboard.dto.response;

import com.boardproject.projectboard.dto.ArticleCommentDto;
import com.boardproject.projectboard.dto.ArticleWithCommentsDto;
import com.boardproject.projectboard.dto.HashtagDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        Set<String> hashtags,
        LocalDateTime createdAt,
        String email,
        String nickname,
        String userId,
        Set<ArticleCommentResponse> articleCommentsResponse
) {

    public static ArticleWithCommentsResponse of(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtags, createdAt, email, nickname, userId, articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtagDtos().stream()
                        .map(HashtagDto::hashtagName)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname,
                dto.userAccountDto().userId(),
                organizeChildComments(dto.articleCommentDtos())
        );
    }


    private static Set<ArticleCommentResponse> organizeChildComments(Set<ArticleCommentDto> dtos) {
        // DTOs를 ArticleCommentResponse 객체로 변환하고, 이를 ID를 키로 하는 맵에 저장
        Map<Long, ArticleCommentResponse> map = dtos.stream()
                .map(ArticleCommentResponse::from) // DTO를 Response 객체로 변환
                // 변환된 객체를 맵에 저장
                .collect(Collectors.toMap(ArticleCommentResponse::id, Function.identity()));

        // 맵에서 모든 댓글 객체를 순회하며 대댓글을 부모 댓글의 자식 목록에 추가
        map.values().stream()
                // 부모 댓글이 있는 댓글만 필터링
                .filter(ArticleCommentResponse::hasParentComment)
                .forEach(comment -> {
                    // 부모 댓글 객체를 맵에서 찾음
                    ArticleCommentResponse parentComment = map.get(comment.parentCommentId());
                    // 찾은 부모 댓글에 현재 댓글(대댓글)을 자식 목록에 추가
                    parentComment.childComments().add(comment);
                });

        // 최상위 댓글만 선택하고, 생성 시간 및 ID로 정렬하여 TreeSet에 저장
        return map.values().stream()
                // 부모 댓글이 없는 댓글만 필터링
                .filter(comment -> !comment.hasParentComment())
                // 최상위 댓글을 생성 시간 내림차순, 동일 시간일 경우 ID 오름차순으로 정렬하여 TreeSet에 저장
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(ArticleCommentResponse::createdAt) // 생성 시간으로 먼저 정렬
                                .reversed() // 생성 시간을 내림차순으로 정렬
                                .thenComparingLong(ArticleCommentResponse::id) // 생성 시간이 같을 경우 ID로 정렬
                        )
                ));
    }
}