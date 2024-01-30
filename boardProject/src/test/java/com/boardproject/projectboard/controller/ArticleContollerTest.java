//package com.boardproject.projectboard.controller;
//
//import com.boardproject.projectboard.config.SecurityConfig;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@DisplayName("View 컨트롤러-게시글")
//@Import(SecurityConfig.class) //시큐리티 설정으로 테스트 정상작동
//@WebMvcTest(ArticleContoller.class) //여기있는 컨트롤러만 빈으로 등록해 테스트한다
//class ArticleContollerTest {
//    private final MockMvc mvc;
//
//    public ArticleContollerTest(@Autowired MockMvc mvc){
//        this.mvc = mvc;
//    }
////    @Disabled("개발중")
//    @DisplayName("[view] [GET] 게시글 리스트 (게시판-페이지")
//    @Test
//    public void given_whenRequestingArticlesView_thenResurnsArticlesView() throws Exception {
//        //given
//
//        //when_then
//       mvc.perform(get("/articles")) // 이경로로 요청하면
//               .andExpect(status().isOk())//200으로 정상호출되야하며
//               .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))//컨텐트 내용의 type확인, utf-8도 허용하게한다
//               .andExpect(model().attributeExists("articles")) //데이터로 articles를 넘겨줘야 한다.
//               .andExpect(view().name("articles/index")); //뷰 이름은 article/index에 있어야한다
//
//    }
//
////    @Disabled("개발중")
//    @DisplayName("[view] [GET] 게시글 상세페이지 정상호출")
//    @Test
//    public void givenNoting_whenRequestingArticlesView_thenReturnsArticleView() throws Exception {
//        //given
//
//        //when_then
//        mvc.perform(get("/articles/1"))
//                .andExpect(status().isOk())//정상호출되는지 검사
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))//컨텐트 내용의 type확인
//                .andExpect(view().name("articles/detail")) //뷰에대한 이름도 조건에 추가
//                .andExpect(model().attributeExists("articles")) //데이터가 있는지 확인
//                .andExpect(model().attributeExists("articleComments")); //데이터가 있는지 확인
//
//
//    }
//
//    @Disabled("개발중")
//    @DisplayName("[view] [GET] 게시글 검색전용 페이지")
//    @Test
//    public void givenNothing_whenRequestingArticleSearchView_thenResurnsArticlesView() throws Exception {
//        //given
//
//        //when_then
//        mvc.perform(get("/articles/search"))
//                .andExpect(status().isOk())//정상호출되는지 검사
//                .andExpect(model().attributeExists("articles/search")) //데이터가 있는지 확인
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));//컨텐트 내용의 type확인
//
//
//    }
//
//    @Disabled("개발중")
//    @DisplayName("[view] [GET] 게시글 해시태크 검색 페이지")
//    @Test
//    public void givenNothing_whenRequestingArticleHashtagSearchView_thenResurnsArticlesView() throws Exception {
//        //given
//
//        //when_then
//        mvc.perform(get("/articles/search-hashtag"))
//                .andExpect(status().isOk())//정상호출되는지 검사
//                .andExpect(model().attributeExists("articles/search-hashtag")) //데이터가 있는지 확인
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));//컨텐트 내용의 type확인
//
//    }
//
//
//
//
//}