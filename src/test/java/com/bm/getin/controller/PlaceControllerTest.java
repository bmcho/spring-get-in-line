package com.bm.getin.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PlaceController.class)
class PlaceControllerTest {

    private final MockMvc mvc;

    public PlaceControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[view][GET] 장소 리스트 페이지")
    void givenNothing_whenRequestingPlacesPage_thenReturnsPlacesPage() throws Exception {
        // Given
        // When & Then
        mvc.perform(get("/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("place/index"));
    }

    @Test
    @DisplayName("[view][GET] 장소 상세 정보 페이지")
    void givenNothing_thenRequestingPlaceDetailPage_thenReturnsPlaceDetailPage() throws Exception {
        // Given
        long placesId = 1L;
        // When & Then
        mvc.perform(get("/places/" + placesId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("place/detail"));
    }
}