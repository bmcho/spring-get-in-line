package com.bm.getin.controller.api;

import com.bm.getin.constant.ErrorCode;
import com.bm.getin.constant.EventStatus;
import com.bm.getin.dto.APIDataResponse;
import com.bm.getin.dto.APIErrorResponse;
import com.bm.getin.dto.EventRequest;
import com.bm.getin.dto.EventResponse;
import com.bm.getin.exception.GeneralException;
import com.bm.getin.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class APIEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDateTime,
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDateTime
    ) {
        List<EventResponse> eventResponses = eventService.getEvents(
                        placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime)
                .stream().map(EventResponse::from).toList();

        return APIDataResponse.of(eventResponses);
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public APIDataResponse<String> createEvent(@RequestBody EventRequest eventRequest) {
        boolean result = eventService.createEvent(eventRequest.ToDTO());
        return APIDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("/events/{eventId}")
    public APIDataResponse<EventResponse> getEvent(@PathVariable Long eventId) {
        EventResponse eventResponse = EventResponse.from(eventService.getEvent(eventId).orElse(null));

        return APIDataResponse.of(eventResponse);
    }

    @PutMapping("/events/{eventId}")
    public APIDataResponse<String> modifyEvent(@PathVariable Long eventId, @RequestBody EventRequest eventRequest) {
        boolean result = eventService.modifyEvent(eventId, eventRequest.ToDTO());
        return APIDataResponse.of(Boolean.toString(result));
    }

    @DeleteMapping("/events/{eventId}")
    public APIDataResponse<String> removeEvent(@PathVariable Long eventId) {
        boolean result = eventService.removeEvent(eventId);
        return APIDataResponse.of(Boolean.toString(result));
    }

//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e) {
//        ErrorCode errorCode = e.getErrorCode();
//        HttpStatus status = errorCode.isClientSideError() ?
//                HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
//
//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false, errorCode, errorCode.getMessage(e)
//                ));
//    }
}
