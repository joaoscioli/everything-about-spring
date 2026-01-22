package com.thecodingcabin.springbootrestapi.repository;

import com.thecodingcabin.springbootrestapi.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
