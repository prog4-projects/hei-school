package com.hei.school.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
  private UUID id;
  private String title;
  private Instant startDate;
  private Instant endDate;
}
