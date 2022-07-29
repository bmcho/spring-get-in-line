package com.bm.getin.controller;

import com.bm.getin.constant.EventStatus;
import com.bm.getin.constant.PlaceType;
import com.bm.getin.dto.EventDTO;
import com.bm.getin.dto.PlaceDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/places")
    public ModelAndView adminPlaces(
            PlaceType placeType,
            String placeName,
            String address
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeName);
        map.put("address", address);

        return new ModelAndView("admin/places", map);
    }

    @GetMapping("/places/{placesId}")
    public ModelAndView adminPlacesDetail(@PathVariable Integer placesId) {
        Map<String, Object> map = new HashMap<>();
        map.put("place", PlaceDTO.of(
                PlaceType.COMMON,
                "즐거운배드민턴장",
                "서울시 강남구 강남대로 1",
                "010-1234-1234",
                30,
                "신장개업"
        ));

        return new ModelAndView("admin/place-detail", map);
    }

    @GetMapping("/events")
    public ModelAndView adminEvents(
            Integer placeId,
            String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeName", "place-" + placeId);
        map.put("eventName", eventName);
        map.put("eventStatus", eventStatus);
        map.put("eventStartDatetime", eventStartDatetime);
        map.put("eventEndDatetime", eventEndDatetime);

        return new ModelAndView("admin/events", map);
    }

    @GetMapping("/events/{eventId}")
    public ModelAndView adminEventDetail(@PathVariable Long eventId) {
        Map<String, Object> map = new HashMap<>();
        map.put("event", EventDTO.of(
                eventId,
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2022, 7, 3, 17, 0, 0),
                LocalDateTime.of(2022, 7, 4, 17, 0, 0),
                0,
                24,
                "마스크 꼭 쓰기",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        return new ModelAndView("admin/event-detail",map);
    }
}
