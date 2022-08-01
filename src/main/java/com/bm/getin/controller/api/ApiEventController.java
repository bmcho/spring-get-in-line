package com.bm.getin.controller.api;

import com.bm.getin.constant.EventStatus;
import com.bm.getin.dto.ApiDataResponse;
import com.bm.getin.dto.EventRequest;
import com.bm.getin.dto.EventResponse;
import com.bm.getin.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

//@Validated
@RequiredArgsConstructor
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
        List<EventResponse> eventResponses = eventService.getEvents(
                        placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime)
                .stream().map(EventResponse::from).toList();

        return ApiDataResponse.of(eventResponses);
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiDataResponse<String> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        boolean result = eventService.createEvent(eventRequest.ToDto());
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("/events/{eventId}")
    public ApiDataResponse<EventResponse> getEvent(@Positive @PathVariable Long eventId) {
        EventResponse eventResponse = EventResponse.from(eventService.getEvent(eventId).orElse(null));

        return ApiDataResponse.of(eventResponse);
    }

    @PutMapping("/events/{eventId}")
    public ApiDataResponse<String> modifyEvent(@Positive @PathVariable Long eventId, @Valid @RequestBody EventRequest eventRequest) {
        boolean result = eventService.modifyEvent(eventId, eventRequest.ToDto());
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
