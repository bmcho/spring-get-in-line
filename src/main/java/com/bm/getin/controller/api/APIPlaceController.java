package com.bm.getin.controller.api;

import com.bm.getin.constant.PlaceType;
import com.bm.getin.dto.APIDataResponse;
import com.bm.getin.dto.PlaceDTO;
import com.bm.getin.dto.PlaceResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getPlaces() {
        return APIDataResponse.of(List.of(PlaceResponse.of(
                PlaceType.COMMON,
                "칼라배드민턴",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        )));
    }

    @PostMapping("/places")
     public Boolean createPlace() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceDTO> getPlace(@PathVariable Integer placeId) {
        if (placeId.equals(2)) {
            return APIDataResponse.of(null);
        }


        return APIDataResponse.of(PlaceDTO.of(
                PlaceType.COMMON,
                "칼라배드민턴",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean deletePlace(@PathVariable Integer placeId) {
        return true;
    }
}
