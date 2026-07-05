package com.hei.school.enrollment.event;

import java.util.UUID;

public record EnrollmentCreatedEvent(UUID userId, UUID courseId) {}
