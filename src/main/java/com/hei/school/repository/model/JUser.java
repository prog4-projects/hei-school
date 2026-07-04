package com.hei.school.repository.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JUser {
  @Id @GeneratedValue @UuidGenerator private UUID id;

  @Column private String firstName;
  @Column private String lastName;
  @Column private String userName;
  @Column private String email;

  @ManyToMany
  @JoinTable(
      name = "enrollments",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private Set<JCourse> courses = new HashSet<>();
}
