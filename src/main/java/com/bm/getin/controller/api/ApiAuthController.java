package com.bm.getin.controller.api;

import com.bm.getin.dto.ApiDataResponse;
import com.bm.getin.dto.AdminRequest;
import com.bm.getin.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api")
public class ApiAuthController {

    @PostMapping("/sign-up")
    public ApiDataResponse<String> sighUp(@RequestBody AdminRequest adminRequest) {
        return ApiDataResponse.empty();
    }


    @PostMapping("/login")
    public ApiDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return ApiDataResponse.empty();
    }
}
