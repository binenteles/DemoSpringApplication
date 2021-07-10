package com.sda.mvc.controller;

import com.sda.mvc.model.User;
import com.sda.mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController()
@Slf4j
@RequestMapping(value = "/cinema/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<String> findAllUsers(@RequestParam(value = "minAge", required = false) Integer a,
                                               @RequestParam(value = "maxAge", required = false) Integer b) {
        if (a == null || b == null) {
            return new ResponseEntity<>("Please provide min and max age", HttpStatus.BAD_REQUEST);
        }
        List<User> userList = userService.findUsersInAgeGroup(a, b);
        log.info("Find all users successfully");
        log.warn("error");
        log.debug(userList.toString());
        return new ResponseEntity<>(userList.toString(), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // TODO try catch exception & is the user saved?
        try {
            userService.saveUser(user);
        } catch (IllegalArgumentException e) {
            log.info("Something went wrong, IllegalArgumentException");
            return new ResponseEntity<>("There is some missing data, please check your request...", HttpStatus.I_AM_A_TEAPOT);
        }
        log.info(user.toString());
        return new ResponseEntity<>("User: " + user, HttpStatus.CREATED);
    }


    @DeleteMapping("/users")
    @Transactional
    public ResponseEntity<String> deleteUser(@RequestParam(value = "username", required = false) String username,
                                             @RequestParam(value = "id", required = false) Integer id) {
        Integer result;
        if ((!(isBlank(username)) && id != null) || (isBlank(username) && id == null)) {
            throw new IllegalArgumentException("Please provide one of the options. Username or Id.");
        } else if (!isBlank(username)) {
            result = userService.deleteUserByUsername(username);
        } else {
            result = userService.deleteUserById(id);
        }
        return new ResponseEntity<>("User with id " + result + " has been removed.", HttpStatus.OK);
    }

    private boolean isBlank(String username) {
        return username == null || username.isEmpty();
    }

    //todo PutMapping
    //todo play around with Get :)

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Illegal Arguments " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> catchRuntimeException(RuntimeException e) {
        return new ResponseEntity<>("Runtime Exception " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

