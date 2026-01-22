package com.thecodingcabin.springbootrestapi.controller;

import com.thecodingcabin.springbootrestapi.dto.ResourceCreateDTO;
import com.thecodingcabin.springbootrestapi.dto.ResourceResponseDTO;
import com.thecodingcabin.springbootrestapi.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    // -------------------------
    // CREATE
    // -------------------------
    @PostMapping
    public ResponseEntity<ResourceResponseDTO> create(
            @RequestBody @Valid ResourceCreateDTO dto
    ) {
        ResourceResponseDTO created = service.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    // -------------------------
    // READ - ALL
    // -------------------------
    @GetMapping
    public ResponseEntity<List<ResourceResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // -------------------------
    // READ - BY ID
    // -------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}

