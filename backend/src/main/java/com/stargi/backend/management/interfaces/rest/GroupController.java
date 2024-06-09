package com.stargi.backend.management.interfaces.rest;

import com.stargi.backend.management.application.internal.GroupCommandService;
import com.stargi.backend.management.application.internal.GroupQueryService;
import com.stargi.backend.management.domain.commands.AddMemberToGroupCommand;
import com.stargi.backend.management.domain.commands.ChangeLeaderGroupCommand;
import com.stargi.backend.management.domain.commands.CreateGroupWithoutLeaderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/group",produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {
    private final GroupCommandService groupCommandService;
    private final GroupQueryService groupQueryService;

    @GetMapping
    public ResponseEntity<?> getAllGroups(){
        return ResponseEntity.ok(this.groupQueryService.handle());
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupWithoutLeaderCommand command){
        Long response=this.groupCommandService.handle(command);
        if(response==1L){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("{leaderId}/leader/{teamId}/group")
    public ResponseEntity<?> assignLeader(@PathVariable("leaderId")Long leaderId,@PathVariable("teamId") Long teamId){
        var query=new ChangeLeaderGroupCommand(leaderId,teamId);
        Long response=this.groupCommandService.handle(query);
        if(response==1L){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> addMemberToGroup(@RequestBody AddMemberToGroupCommand command){
        Long response=this.groupCommandService.handle(command);
        if(response==1L){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().build();
        }

    }
}
