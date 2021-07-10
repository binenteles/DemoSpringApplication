package com.sda.mvc.service;

import com.sda.mvc.model.User;
import com.sda.mvc.repository.UserRepository;
import com.sda.mvc.repository.UserRepositoryIF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryIF userRepositoryIF;


    public List<User> findUsersInAgeGroup(int x, int y) {
        return userRepositoryIF.getUsersByAgeBetween(x, y);
    }

    public void saveUser(User user) {
        if (user.getAge() != 0 && user.getUsername() != null && user.getEmail() != null && user.getName() != null && user.getPassword() != null) {
//            userRepository.save(user);
            userRepositoryIF.save(user);
            log.info("User was created successfully");
        } else {
            throw new IllegalArgumentException("Please fill in all required fields.");
        }
    }

//        public void deleteUser(String username)
//    {
//        List<User> allUsers = userRepository.findAllUsers();
//        allUsers.removeAll(allUsers.stream()
//                .filter(user -> user.getUsername().equals(username))
//                .collect(Collectors.toList()));
//    }

    @Transactional
    public Integer deleteUserByUsername(String username) {

        Integer userId = userRepositoryIF.deleteByUsername(username);

        if (userId == 0) {
            log.warn("User has not been deleted");
        } else {
            log.info("User with id: " + userId + " has been deleted");
        }
        return userId;
    }

    public Integer deleteUserById(Integer id) {

        Integer user = userRepositoryIF.deleteUserById(id);

        if (user != 0) {
            log.info("User with id: " + id + " has been deleted.");
        } else {
            log.info("User has not been deleted");
        }
        return user;
    }

}

