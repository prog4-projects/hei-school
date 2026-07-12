package com.hei.school.service;

import com.hei.school.entity.User;
import com.hei.school.mapper.UserMapper;
import com.hei.school.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  public User getById(UUID id) {
    return userMapper.toDomain(
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("User with id %s not found !".formatted(id))));
  }
}
