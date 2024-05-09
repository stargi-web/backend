package com.stargi.backend.iam.interfaces.rest;

import com.stargi.backend.iam.domain.commands.CreateUserCommand;
import com.stargi.backend.iam.domain.commands.LogInUserCommand;
import com.stargi.backend.iam.domain.services.IUserCommandService;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    IUserCommandService userCommandService;
    public AuthenticationController(IUserCommandService userCommandService){
        this.userCommandService=userCommandService;
    }

    @GetMapping()
    public String holaMundo(){
        return "Hola Mundo";
    }

    @PostMapping("signUp")
    public ResponseEntity<?> SignUp(@RequestBody CreateUserCommand command){
        var response= userCommandService.handle(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("signIn")
    public ResponseEntity<?> SignIn(@RequestBody LogInUserCommand logInUserCommand){
        var response=userCommandService.handle(logInUserCommand);
        return ResponseEntity.ok(response);
    }

}
