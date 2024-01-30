package com.boardproject.projectboard.controller;


import com.boardproject.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("View 컨트롤러 인증부분")
@Import(SecurityConfig.class)
@WebMvcTest
public class AuthControllerTest {
    private final MockMvc mvc;

    public AuthControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("[view] [GET] 로그인페이지 (게시판-페이지")
    @Test
    public void givenNothing_whenTryingtoLoing_thenReturningLoginView() throws Exception {
        //given

        //when_then
        mvc.perform(get("/login")) // login 뷰를 만들지 않아도 스프링시큐리티에 내장되있기에 테스트 동작한다
                .andExpect(status().isOk())//200으로 정상호출되야하며
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));//컨텐트 내용의 type확인, utf-8도 허용하게한다


    }
}
