package com.hei.school.repository;

import com.hei.school.repository.model.JEnrollment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<JEnrollment, UUID> {
  boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
}
