package com.mountain.backend.mountain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountain.backend.mountain.entity.Trail;

public interface TrailRepository extends JpaRepository<Trail,Long> {
}
