package com.mountain.backend.mountain.repository;

import com.mountain.backend.mountain.entity.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainRepository extends JpaRepository<Mountain, Long> {
}
