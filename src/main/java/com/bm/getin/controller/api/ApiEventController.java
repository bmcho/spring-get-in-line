package com.bm.getin.controller.api;

import com.bm.getin.constant.EventStatus;
import com.bm.getin.constant.PlaceType;
import com.bm.getin.dto.ApiDataResponse;
import com.bm.getin.dto.EventRequest;
import com.bm.getin.dto.EventResponse;
import com.bm.getin.dto.PlaceDto;
import com.bm.getin.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring Data REST 로 API 를 만들어서 당장 필요가 없어진 컨트롤러.
 * 우선 deprecated 하고, 향후 사용 방안을 고민해 본다.
 * 필요에 따라서는 다시 살릴 수도 있음
 *
 * @deprecated 0.1.2
 */
@Deprecated
@RequiredArgsConstructor
//@Validated
//@RestController
//@RequestMapping("/api")
public class ApiEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public ApiDataResponse<List<EventResponse>> getEvents(
            @Positive Long placeId,
            @Size(min = 2) String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDateTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDateTime
    ) {
        return ApiDataResponse.of(List.of(EventResponse.of(
                1L,
                PlaceDto.of(
                        1L,
                        PlaceType.SPORTS,
                        "배드민턴장",
                        "서울시 가나구 다라동",
                        "010-1111-2222",
                        0,
                        null,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 1, 1, 13, 0, 0),
                LocalDateTime.of(2021, 1, 1, 16, 0, 0),
                0,
                24,
                "마스크 꼭 착용하세요"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/place/{placeId}/events")
    public ApiDataResponse<String> createEvent(
            @Valid @RequestBody EventRequest eventRequest,
            @PathVariable Long placeId
    ) {
        boolean result = eventService.createEvent(eventRequest.toDto(PlaceDto.idOnly(placeId)));
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("/events/{eventId}")
    public ApiDataResponse<EventResponse> getEvent(@Positive @PathVariable Long eventId) {
        EventResponse eventResponse = EventResponse.from(eventService.getEvent(eventId).orElse(null));

        return ApiDataResponse.of(eventResponse);
    }

    @PutMapping("/events/{eventId}")
    public ApiDataResponse<String> modifyEvent(@Positive @PathVariable Long eventId, @Valid @RequestBody EventRequest eventRequest) {
        boolean result = eventService.modifyEvent(eventId, eventRequest.toDto(null));
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @DeleteMapping("/events/{eventId}")
    public ApiDataResponse<String> removeEvent(@Positive @PathVariable Long eventId) {
        boolean result = eventService.removeEvent(eventId);
        return ApiDataResponse.of(Boolean.toString(result));
    }

//    @ExceptionHandler
//    public ResponseEntity<ApiErrorResponse> general(GeneralException e) {
//        ErrorCode errorCode = e.getErrorCode();
//        HttpStatus status = errorCode.isClientSideError() ?
//                HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
//
//        return ResponseEntity
//                .status(status)
//                .body(ApiErrorResponse.of(
//                        false, errorCode, errorCode.getMessage(e)
//                ));
//    }
}
