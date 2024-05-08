package com.stargi.backend.iam.interfaces.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AuthenticationController {

    @GetMapping()
    public String holaMundo(){
        return "Hola Mundo";
    }
}
