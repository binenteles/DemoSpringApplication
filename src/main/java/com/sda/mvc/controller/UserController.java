package com.sda.mvc.controller;

import com.sda.mvc.model.User;
import com.sda.mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Slf4j
@RequestMapping(value = "/cinema/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<String> findAllUsers(@RequestParam(value = "minAge", required = false) Integer a, @RequestParam(value = "maxAge", required = false) Integer b) {
        if (a == null || b == null) {
            return new ResponseEntity<>("Please provide min and max age", HttpStatus.BAD_REQUEST);
        }
        List<User> userList = userService.findUsersInAgeGroup(a, b);
        log.info("Find all users successfully");
        log.warn("eroare");
        log.debug(userList.toString());
        return new ResponseEntity<>(userList.toString(), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello() {

        return "hello!";
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // TODO trycatch exception & is the user saved?
        log.info(user.toString());
        userService.saveUser(user);
        return new ResponseEntity<>("User: " + user, HttpStatus.CREATED);
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        User user = userService.deleteUser(username);
        return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>("Illegal Arguments " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> catchRuntimeException(RuntimeException e){
        return new ResponseEntity<>("Runtime Exception " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

