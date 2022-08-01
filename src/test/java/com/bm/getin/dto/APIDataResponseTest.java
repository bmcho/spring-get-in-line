package com.bm.getin.dto;

import com.bm.getin.constant.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("데이터 - Api 기본 응답")
class ApiDataResponseTest {

    @Test
    @DisplayName("문자열 데이터가 주어지면, 표준 성공 응답을 생성")
    void givenStringData_whenCreatingResponse_thenReturnSuccessfulResponse(){
        // Given
        String data = "test data";

        // When
        ApiDataResponse<String> response = ApiDataResponse.of(data);

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data", data);
    }

    @Test
    @DisplayName("데이터가 없을때, 비어있는 표준 성공 응답을 생성")
    void givenNothingData_whenCreatingResponse_thenReturnEmptySuccessfulResponse(){
        // Given

        // When
        ApiDataResponse<String> response = ApiDataResponse.empty();

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data", null);

    }
}