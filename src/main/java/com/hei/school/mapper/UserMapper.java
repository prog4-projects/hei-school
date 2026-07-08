package com.hei.school.mapper;

import com.hei.school.entity.User;
import com.hei.school.repository.model.JUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toDomain(JUser jUser) {

    return User.builder()
        .id(jUser.getId())
        .firstName(jUser.getFirstName())
        .lastName(jUser.getLastName())
        .userName(jUser.getUserName())
        .email(jUser.getEmail())
        .build();
  }
}
