package com.hei.school.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "Course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Check(constraints = "end_date > start_date")
public class JCourse {
  @Id @GeneratedValue @UuidGenerator private UUID id;

  @Column private String title;
  @Column private Instant startDate;

  @Column private Instant endDate;
}
