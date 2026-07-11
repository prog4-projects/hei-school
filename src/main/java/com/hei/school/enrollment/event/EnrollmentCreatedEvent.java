package com.hei.school.enrollment.event;

public record EnrollmentCreatedEvent(String firstName, String email, String courseTitle) {}
