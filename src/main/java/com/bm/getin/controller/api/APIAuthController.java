package com.bm.getin.controller.api;

import com.bm.getin.dto.APIDataResponse;
import com.bm.getin.dto.AdminRequest;
import com.bm.getin.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class APIAuthController {

    @PostMapping("/sign-up")
    public APIDataResponse<String> sighUp(@RequestBody AdminRequest adminRequest) {
        return APIDataResponse.empty();
    }


    @PostMapping("/login")
    public APIDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return APIDataResponse.empty();
    }
}
