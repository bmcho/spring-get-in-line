package com.getin.bm.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController implements ErrorController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
