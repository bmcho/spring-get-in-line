package com.bm.getin.controller.api;

import com.bm.getin.constant.ErrorCode;
import com.bm.getin.dto.AdminRequest;
import com.bm.getin.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled("API 컨트롤러가 필요없는 상황이어서 비활성화")
@DisplayName("API 컨트롤러 - 인증")
@WebMvcTest(APIAuthController.class)
class APIAuthControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper mapper;

    public APIAuthControllerTest(@Autowired MockMvc mvc, @Autowired ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @Test
    @DisplayName("[API][POST] 관리자 가입 - 정상 입력하면 회정 정보를 추가하고 안내메세지 리턴")
    void givenAdminDetails_whenSigningUp_thenCreatesAdminAndReturns() throws Exception {
        // Given
        AdminRequest adminRequest = AdminRequest.of(
                "test@test.com",
                "testNickName",
                "password",
                "010-1234-1234",
                "testAdmin"
        );
        // When & Then
        mvc.perform(post("/api/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(adminRequest))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @Test
    @DisplayName("[API][POST] 로그인 - 존재하는 유저 정보로 인증요청하면 인증 통과")
    void givenUsernameAndPassword_whenLoggingIn_thenCreatesAdminAndReturns() throws Exception {
        // Given
        LoginRequest loginRequest = LoginRequest.of(
                "test@test.com",
                "password"
        );

        // When & Then
        mvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }
}