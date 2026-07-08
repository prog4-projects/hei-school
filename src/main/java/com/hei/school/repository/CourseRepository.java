package com.hei.school.repository;

import com.hei.school.repository.model.JCourse;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<JCourse, UUID> {}
