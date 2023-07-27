package com.mountain.backend.mountain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountain.backend.mountain.entity.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate,Long> {
}
