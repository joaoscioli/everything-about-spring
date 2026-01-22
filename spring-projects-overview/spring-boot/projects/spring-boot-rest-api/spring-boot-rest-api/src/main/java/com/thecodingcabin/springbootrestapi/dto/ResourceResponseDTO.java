package com.thecodingcabin.springbootrestapi.dto;

import java.time.LocalDateTime;

public record ResourceResponseDTO(

        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}