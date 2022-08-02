package com.bm.getin.controller.api;

import com.bm.getin.constant.PlaceType;
import com.bm.getin.dto.ApiDataResponse;
import com.bm.getin.dto.PlaceDto;
import com.bm.getin.dto.PlaceRequest;
import com.bm.getin.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

    //@RestController
    //@RequestMapping("/api")
public class ApiPlaceController {

    @GetMapping("/places")
    public ApiDataResponse<List<PlaceResponse>> getPlaces() {
        return ApiDataResponse.of(List.of(PlaceResponse.of(
                1L,
                PlaceType.COMMON,
                "칼라배드민턴",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/places")
    public ApiDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
        return ApiDataResponse.empty();
    }

    @GetMapping("/places/{placeId}")
    public ApiDataResponse<PlaceDto> getPlace(@PathVariable Long placeId) {
        if (placeId.equals(2)) {
            return ApiDataResponse.of(null);
        }


        return ApiDataResponse.of(PlaceDto.of(
                placeId,
                PlaceType.COMMON,
                "칼라배드민턴",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
    }

    @PutMapping("/places/{placeId}")
    public ApiDataResponse<Void> modifyPlace(
            @PathVariable Long placeId,
            @RequestBody PlaceRequest placeRequest
    ) {
        return ApiDataResponse.empty();
    }

    @DeleteMapping("/places/{placeId}")
    public ApiDataResponse<Void> removePlace(@PathVariable Long placeId) {
        return ApiDataResponse.empty();
    }
}
