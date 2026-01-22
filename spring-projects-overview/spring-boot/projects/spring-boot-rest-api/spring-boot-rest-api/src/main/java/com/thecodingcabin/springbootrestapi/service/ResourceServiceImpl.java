package com.thecodingcabin.springbootrestapi.service;

import com.thecodingcabin.springbootrestapi.domain.Resource;
import com.thecodingcabin.springbootrestapi.dto.ResourceCreateDTO;
import com.thecodingcabin.springbootrestapi.dto.ResourceResponseDTO;
import com.thecodingcabin.springbootrestapi.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repository;

    public ResourceServiceImpl(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResourceResponseDTO create(ResourceCreateDTO dto) {
        Resource resource = new Resource(
                dto.name(),
                dto.description()
        );

        Resource saved = repository.save(resource);

        return mapToResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ResourceResponseDTO findById(Long id) {
        Resource resource = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        return mapToResponseDTO(resource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResourceResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    private ResourceResponseDTO mapToResponseDTO(Resource resource) {
        return new ResourceResponseDTO(
                resource.getId(),
                resource.getName(),
                resource.getDescription(),
                resource.getCreatedAt(),
                resource.getUpdatedAt()
        );
    }
}
