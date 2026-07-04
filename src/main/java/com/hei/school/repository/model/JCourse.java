package com.hei.school.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Check(constraints = "end_date > start_date")
public class JCourse {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @ManyToMany(mappedBy = "courses")
  private Set<JUser> users = new HashSet<>();
  @Column(nullable = false)
  private String title;

  @Column(name = "start_date", nullable = false)
  private Instant startDate;

  @Column(name = "end_date", nullable = false)
  private Instant endDate;
}
