package com.bm.getin.controller;

import com.bm.getin.config.SecurityCustomConfig;
import com.bm.getin.dto.PlaceDto;
import com.bm.getin.service.PlaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 장소")
@WebMvcTest(
        controllers = PlaceController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = SecurityCustomConfig.class)
)
class PlaceControllerTest {

    private final MockMvc mvc;
    @MockBean
    private PlaceService placeService;

    public PlaceControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[view][GET] 장소 리스트 페이지")
    void givenNothing_whenRequestingPlacesPage_thenReturnsPlacesPage() throws Exception {
        // Given
        given(placeService.getPlaces(any())).willReturn(List.of());
        // When & Then
        mvc.perform(get("/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("place/index"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("places"));
        then(placeService).should().getPlaces(any());
    }

    @Test
    @DisplayName("[view][GET] 장소 상세 정보 페이지")
    void givenNothing_thenRequestingPlaceDetailPage_thenReturnsPlaceDetailPage() throws Exception {
        // Given
        long placeId = 1L;
        given(placeService.getPlace(placeId)).willReturn(Optional.of(
                PlaceDto.of(null, null, null, null, null, null, null, null, null)
        ));

        // When & Then
        mvc.perform(get("/places/" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("place/detail"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("place"));
        then(placeService).should().getPlace(placeId);
    }

    @DisplayName("[view][GET] 장소 세부 정보 페이지 - 데이터 없음")
    @Test
    void givenNonexistentPlaceId_whenRequestingPlaceDetailPage_thenReturnsErrorPage() throws Exception {
        // Given
        long placeId = 0L;
        given(placeService.getPlace(placeId)).willReturn(Optional.empty());

        // When & Then
        mvc.perform(get("/places/" + placeId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("error"));
        then(placeService).should().getPlace(placeId);
    }
}