package com.stargi.backend.records.interfaces.rest;

import com.stargi.backend.records.application.internal.InfoCommandService;
import com.stargi.backend.records.application.internal.InfoQueryService;
import com.stargi.backend.records.domain.commands.CreateInfoCommand;
import com.stargi.backend.records.domain.commands.DeleteInfoByIdCommand;
import com.stargi.backend.records.domain.commands.EditInfoCommand;
import com.stargi.backend.records.domain.queries.FindInfoByNameQuery;
import com.stargi.backend.records.domain.queries.FindInfoByUserIdQuery;
import com.stargi.backend.records.domain.services.IInfoCommandService;
import com.stargi.backend.records.domain.services.IInfoQueryService;
import com.stargi.backend.records.interfaces.dtos.InfoDTO;
import com.stargi.backend.records.interfaces.dtos.requestbodies.EditInfoRequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/info",produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoController {
    private final InfoCommandService infoCommandService;
    private final InfoQueryService infoQueryService;
    public InfoController(InfoCommandService infoCommandService, InfoQueryService infoQueryService) {
        this.infoCommandService = infoCommandService;
        this.infoQueryService = infoQueryService;
    }

    @PostMapping()
    public ResponseEntity<?> createInfo(@RequestBody CreateInfoCommand command){
        var response=infoCommandService.handle(command);
        return ResponseEntity.ok(response.get());
    }
    @GetMapping()
    public ResponseEntity<?> getAllInfos(){
        var response=infoQueryService.handle();
        List<InfoDTO> infoDTOList= response.stream().map(info -> new InfoDTO(info.getId(),info.getRuc(), info.getBusinessName(), info.getCountry(),info.getUpdatedAt().toString(),info.getExpirationAt().toString(),info.getCloseAt().toString())).toList();

        return ResponseEntity.ok(infoDTOList);
    }

    @GetMapping("{userId}/infosUser")
    public ResponseEntity<?> getInfosByUserId(@PathVariable("userId")Long userId){
        var query=new FindInfoByUserIdQuery(userId);
        var response=infoQueryService.handle(query);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        List<InfoDTO> infoDTOList= response.stream().map(info -> new InfoDTO(info.getId(),info.getRuc(), info.getBusinessName(), info.getCountry(),info.getUpdatedAt().toString(),info.getExpirationAt().toString(),info.getCloseAt().toString())).toList();
        return ResponseEntity.ok(infoDTOList);
    }

    @DeleteMapping("{infoId}")
    public ResponseEntity<?> deleteInfoById(@PathVariable("infoId")Long infoId){
        var command=new DeleteInfoByIdCommand(infoId);
        var response=infoCommandService.handle(command);
        if(response==0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{infoId}")
    public ResponseEntity<?> editInfoById(@PathVariable("infoId")Long infoId, @RequestBody EditInfoRequestBody body){

        var command= new EditInfoCommand(infoId,body.newStage(),body.newCommentary(),body.newExpirationAt(), body.newCloseAt());
        var response=infoCommandService.handle(command);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response.get());
    }

}
