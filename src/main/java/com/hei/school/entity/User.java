package com.hei.school.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  private UUID id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
}
