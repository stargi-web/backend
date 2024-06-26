package com.stargi.backend.records.domain.queries;


import org.springframework.data.domain.Pageable;

public record FindClientsByCollectionQuery(Long collectionId, Pageable pageable) {
}
