package com.hei.school.repository.model;

import jakarta.persistence.*;
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
}
