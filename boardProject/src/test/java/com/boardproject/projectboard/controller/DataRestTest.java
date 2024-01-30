package com.boardproject.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Disabled("통합테스트는 불필요하고 무거워서 제외시킴")
@DisplayName("data test - api 테스트")
@Transactional //DB 영향주는 테스트가 되어, 롤백을 기본동작으로 한다
@AutoConfigureMockMvc
//mvc 테스트는 controller 관련된 빈만 불러오기 떄문에 저장된 데이터를 불러오지 않는다.
//mvc 테스트를 쓸수 없어 데이터레스트와 관련된 Autoconfiguration을 불러올수 있게
// 통합 테스트 형태로 진행한다
@SpringBootTest
public class DataRestTest {
    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void test() throws Exception {
        //Given

        //when&Taken
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk()) //status검사
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))) //컨텐트 검
                .andDo(print()); // api호출 결과가 print로 보여준다
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void article_one_request() throws Exception {
        //Given

        //when&Taken
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk()) //status검사
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 게시글 댓글 리스트 조회")
    @Test
    void articlecomments_from_article() throws Exception {
        //Given

        //when&Taken
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk()) //status검사
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void ReturnArticleComments() throws Exception {
        //Given

        //when&Taken
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk()) //status검사
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void articleComments_one_request() throws Exception {
        //Given

        //when&Taken
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk()) //status검사
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 회원 관련 API 는 일체 제공하지 않는다.")
    @Test
    void givenNothing_whenRequestingUserAccounts_thenThrowsException() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(post("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(put("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(patch("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(delete("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(head("/api/userAccounts")).andExpect(status().isNotFound());
    }


}
