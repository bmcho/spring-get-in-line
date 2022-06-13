package com.bm.getin.controller;

import com.bm.getin.constant.EventStatus;
import com.bm.getin.constant.PlaceType;
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
            @RequestParam(required = false) PlaceType placeType,
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
    public String adminPlacesDetail(@PathVariable Integer placesId) {
        return "/admin/places-detail";
    }

    @GetMapping("/events")
    public ModelAndView adminEvents(
            Integer placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
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
    public String adminEventDetail(@PathVariable Integer eventId) {
        return "admin/event-detail";
    }
}