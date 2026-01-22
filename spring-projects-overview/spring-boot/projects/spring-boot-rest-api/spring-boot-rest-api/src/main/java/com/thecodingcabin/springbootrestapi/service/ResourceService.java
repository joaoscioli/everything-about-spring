package com.thecodingcabin.springbootrestapi.service;

import com.thecodingcabin.springbootrestapi.dto.ResourceCreateDTO;
import com.thecodingcabin.springbootrestapi.dto.ResourceResponseDTO;

import java.util.List;

public interface ResourceService {

    ResourceResponseDTO create(ResourceCreateDTO dto);

    ResourceResponseDTO findById(Long id);

    List<ResourceResponseDTO> findAll();
}
