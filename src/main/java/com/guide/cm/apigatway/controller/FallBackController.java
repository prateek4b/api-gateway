package com.guide.cm.apigatway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/fallback")
    public String getFallBackResp()
    {
        return "fallback response";
    }
}
