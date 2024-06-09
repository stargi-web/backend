package com.stargi.backend.iam.interfaces.rest;

import com.stargi.backend.iam.domain.commands.DeleteUserCommand;
import com.stargi.backend.iam.domain.commands.EditPasswordCommand;
import com.stargi.backend.iam.domain.queries.IsUserLeaderQuery;
import com.stargi.backend.iam.domain.services.IUserCommandService;
import com.stargi.backend.iam.domain.services.IUserQueryService;
import com.stargi.backend.iam.interfaces.dto.UserPasswordChangedDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final IUserCommandService userCommandService;
    private final IUserQueryService userQueryService;

    public UserController(IUserCommandService userCommandService, IUserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        var response=this.userQueryService.handle();
        return ResponseEntity.ok(response);
    }
    @PutMapping("changePassword")
    public ResponseEntity<?> setPasswordByUserId(@RequestBody EditPasswordCommand command){
        var user=this.userCommandService.handle(command);
        var response= new UserPasswordChangedDTO(user.getId(),"Contraseña cambiada correctamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId")Long userId){
        var command=new DeleteUserCommand(userId);
        var response=this.userCommandService.handle(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("adminRolesTest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> testAdminRole(){
        return ResponseEntity.ok("La verificación de rol admin funcionó");
    }

    @GetMapping("/{userId}/isLeader")
    public ResponseEntity<?> isUserLeader(@PathVariable("userId") Long userId){
        var query=new IsUserLeaderQuery(userId);
        return ResponseEntity.ok(userQueryService.handle(query));
    }

}
