package com.stargi.backend.records.interfaces.transform;

import com.stargi.backend.records.domain.entities.ClientCollection;
import com.stargi.backend.records.interfaces.dtos.CollectionDTO;

public class FromCollectionToCollectionDTO {
    public static CollectionDTO toDTO(ClientCollection cc){
        if(cc==null)return null;
        return new CollectionDTO(cc.getId(),cc.getCollectionName());
    }
}
