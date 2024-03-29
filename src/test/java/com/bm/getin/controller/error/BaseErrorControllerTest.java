package com.bm.getin.controller.error;

import com.bm.getin.config.SecurityCustomConfig;
import com.bm.getin.controller.EventController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("View 컨트롤러 - 에러")
@WebMvcTest(
        controllers = BaseErrorController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = SecurityCustomConfig.class)
)
class BaseErrorControllerTest {
    private final MockMvc mvc;

    public BaseErrorControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[view][GET] 에러페이지 - 페이지 없음 (404 NotFound)")
    void givenWrongURI_WhenRequestingRootPage_Returns404ErrorCode() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/wrong-uri"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}