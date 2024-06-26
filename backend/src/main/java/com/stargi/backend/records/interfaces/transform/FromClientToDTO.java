package com.stargi.backend.records.interfaces.transform;

import com.stargi.backend.records.domain.entities.Client;
import com.stargi.backend.records.interfaces.dtos.ClientDTO;

public class FromClientToDTO {
    public static ClientDTO toDTO(Client c){
        String userName=c.getUser()!=null?c.getUser().getFullName():null;
        return new ClientDTO(c.getId(),c.getStage().name(), c.getDataInfo(), userName);

    }
}
