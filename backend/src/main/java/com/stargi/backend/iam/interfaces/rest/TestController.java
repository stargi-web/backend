package com.stargi.backend.iam.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/test", produces = MediaType.APPLICATION_JSON_VALUE)

public class TestController {
    public TestController(){

    }

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Autorizado!!!");
    }
}
