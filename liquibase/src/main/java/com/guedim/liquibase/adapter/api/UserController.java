package com.guedim.liquibase.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guedim.liquibase.adapter.datastore.UserRepository;
import com.guedim.liquibase.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping
  public User addUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") Long userId) {
    return ResponseEntity.of(userRepository.findById(userId));
  }

}
