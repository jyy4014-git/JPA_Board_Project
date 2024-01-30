//package com.boardproject.projectboard.service;
//
//import com.boardproject.projectboard.domain.type.SearchType;
//import com.boardproject.projectboard.dto.ArticleDto;
//import com.boardproject.projectboard.repository.ArticleRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@DisplayName("게시글 비즈니스로직")
//@ExtendWith(MockitoExtension.class)
//class ArticleServiceTest {
//
//    @InjectMocks private ArticleService sut;
//    @Mock private ArticleRepository articleRepository;
//
//    @DisplayName("게시글 검색하면, 게시글 리스트 반환")
//    @Test
//    void given_when_then(){
////        given
//
//
//        //when
//        List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");
//
//        //Then
//        Assertions.assertThat(articles).isNotNull();
//    }
//
//    @DisplayName("게시글 정보 입력시 게시글 생성")
//    @Test
//    void 아티클정보넣으면아티클저장(){
//        //given
//        ArticleDto.of(LocalDateTime.now(), "title", "#spring", "")
//    }
//
//}