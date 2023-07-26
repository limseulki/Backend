package com.mountain.backend.meet.repository;

import com.mountain.backend.meet.entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<Meet, Long> {
}
