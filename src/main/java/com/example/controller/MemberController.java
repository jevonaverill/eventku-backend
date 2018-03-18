package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.model.User;
import com.example.model.UserRequest;
import com.example.repository.UserRepository;
import com.example.utility.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by jevon.averill on 30/08/2017.
 */
@RestController
@RequestMapping(value = "/api/User")
@Api(value = "Test", description = "Test")
public class MemberController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  @ApiOperation(value = "greet", notes = "greeting")
  String greet() {
    return "Hello, Jevon Averill!";
  }

  /**
   * <a href=
   * "https://cloud.google.com/appengine/docs/flexible/java/how-instances-are-managed#health_checking">
   * App Engine health checking</a> requires responding with 200 to {@code /_ah/health}.
   */
  @GetMapping
  @RequestMapping(value = "/_ah/health", method = RequestMethod.GET)
  @ApiOperation(value = "hc", notes = "healthcheck")
  public String healthy() {
    // Message body required though ignored
    return "Still surviving.";
  }

  @PostMapping
  @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
  @ApiOperation(value = "Save user", notes = "save user info to DB")
  public void saveTodo(@RequestBody @Valid UserRequest userRequest) throws Exception {
    this.userRepository.save(BeanMapper.map(userRequest, User.class));
  }

  @GetMapping
  @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
  @ApiOperation(value = "Find All User", notes = "find all user info from DB")
  public List<User> findAllUser() throws Exception {
    return this.userRepository.findAllByMarkForDeleteIsFalse();
  }

  @DeleteMapping
  @RequestMapping(value = "/delete/{userID}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete User by User ID", notes = "Delete User by User ID")
  public void deleteUserbyUserID(@PathVariable String userID) throws Exception {
    User existingUser = this.userRepository.findByUserIdAndMarkForDeleteIsFalse(userID);
    if (existingUser != null) {
      existingUser.setMarkForDelete(true);
      this.userRepository.save(existingUser);
    }
  }
}
