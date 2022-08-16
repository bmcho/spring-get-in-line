package com.bm.getin.controller;

import com.bm.getin.constant.ErrorCode;
import com.bm.getin.constant.EventStatus;
import com.bm.getin.constant.PlaceType;
import com.bm.getin.domain.Event;
import com.bm.getin.domain.Place;
import com.bm.getin.dto.EventResponse;
import com.bm.getin.dto.PlaceResponse;
import com.bm.getin.exception.GeneralException;
import com.bm.getin.service.EventService;
import com.bm.getin.service.PlaceService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final EventService eventService;
    private final PlaceService placeService;

    @GetMapping("/places")
    public ModelAndView adminPlaces(@QuerydslPredicate(root = Place.class) Predicate predicate) {
        List<PlaceResponse> places = placeService.getPlaces(predicate)
                .stream()
                .map(PlaceResponse::from)
                .toList();

        return new ModelAndView("admin/places", Map.of(
                "places", places,
                "placeType", PlaceType.values()
        ));
    }

    @GetMapping("/places/{placesId}")
    public ModelAndView adminPlacesDetail(@PathVariable Long placesId) {
        PlaceResponse place = placeService.getPlace(placesId)
                .map(PlaceResponse::from)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND));

        return new ModelAndView("admin/place-detail", Map.of(
                "place", place,
                "placeType", PlaceType.values()
        ));
    }

    @GetMapping("/events")
    public ModelAndView adminEvents(@QuerydslPredicate(root = Event.class) Predicate predicate) {
        List<EventResponse> events = eventService.getEvents(predicate)
                .stream()
                .map(EventResponse::from)
                .toList();

        return new ModelAndView("admin/events", Map.of(
                "events", events,
                "eventStatus", EventStatus.values()
        ));
    }

    @GetMapping("/events/{eventId}")
    public ModelAndView adminEventDetail(@PathVariable Long eventId) {
        EventResponse event = eventService.getEvent(eventId)
                .map(EventResponse::from)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND));

        return new ModelAndView("admin/event-detail", Map.of(
                "event", event,
                "eventStatus", EventStatus.values()
        ));
    }
}
