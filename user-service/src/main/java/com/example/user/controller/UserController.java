package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.model.UserRequest;
import com.example.user.service.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public User create(@RequestBody UserRequest request) {
    return userService.createUser(request);
  }

  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable UUID id) {
    return userService.getById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    userService.delete(id);
  }
}
