package com.bm.getin.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIAuthController {

    @GetMapping("/sigh-up")
    public String sighUp() {
        return "sigh-up done.";
    }


    @GetMapping("/login")
    public String login() {
        return "login done.";
    }
}
