package com.getin.bm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/places")
public class PlaceController {

    @GetMapping("/")
    public String places() {
        return "place/index";
    }

    @GetMapping("/{placeId}")
    public String placesDetail(@PathVariable Integer placeId) {
        return "place/detail";
    }
}
