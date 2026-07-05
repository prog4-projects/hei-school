package com.hei.school.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    private UUID id;
    private UUID userId;
    private UUID courseId;
    private Instant enrolledAt;
}