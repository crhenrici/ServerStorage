package com.prose.crhen.SSServer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    @ResponseBody
    public void getString(@RequestBody String test) {
        System.out.println("String received: " + test);
    }
}
