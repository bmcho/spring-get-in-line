package com.bm.getin.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private final MockMvc mvc;

    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[view][GET] 로그인 페이지")
    void givenNothing_whenRequestingLoginPage_thenReturnsLoginPage() throws Exception {
        // Given
        // When & Then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("login page")))
                .andExpect(view().name("auth/login"));
    }

    @Test
    @DisplayName("[view][GET] 어드민 회원가입 페이지")
    void givenNothing_whenRequestingSignUpPage_thenReturnsSignUpPage() throws Exception {
        // Given
        // When & Then
        mvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("sign-up page")))
                .andExpect(view().name("auth/sign-up"));
    }
}