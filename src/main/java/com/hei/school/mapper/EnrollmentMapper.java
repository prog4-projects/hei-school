package com.hei.school.mapper;

import com.hei.school.entity.Enrollment;
import com.hei.school.repository.model.JEnrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toDomain(JEnrollment jEnrollment) {
        return Enrollment.builder()
                .id(jEnrollment.getId())
                .userId(jEnrollment.getUserId())
                .courseId(jEnrollment.getCourseId())
                .enrolledAt(jEnrollment.getEnrolledAt())
                .build();
    }

    public JEnrollment toEntity(Enrollment enrollment) {
        return JEnrollment.builder()
                .id(enrollment.getId())
                .userId(enrollment.getUserId())
                .courseId(enrollment.getCourseId())
                .enrolledAt(enrollment.getEnrolledAt())
                .build();
    }
}