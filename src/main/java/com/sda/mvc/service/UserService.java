package com.sda.mvc.service;

import com.sda.mvc.model.User;
import com.sda.mvc.repository.UserRepository;
import com.sda.mvc.repository.UserRepositoryIF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Iterable<User> allUsersIF = userRepositoryIF.findAll();
        List<User> allUsers = userRepository.findAllUsers();

        return allUsers.stream()
                .filter(user -> user.getAge() > x && user.getAge() < y)
                .collect(Collectors.toList());
    }

    public void saveUser(User user) {
        if (user.getAge() !=0 && user.getUsername() != null && user.getEmail() != null && user.getName() != null && user.getPassword() != null) {
//            userRepository.save(user);
            userRepositoryIF.save(user);
            log.info("User was created successfully");
        } else {
            throw new IllegalArgumentException("Please fill in all required fields.");
        }


    }

    public User deleteUser(String username){
        List<User> allUsers = userRepository.findAllUsers();
        Integer index  = null;
        for (User user : allUsers ){
            if(user.getUsername().equals(username)){
                index = allUsers.indexOf(user);
            }
        }
        if( index != null){
           return userRepository.delete(index);
        }else{
            throw new IllegalArgumentException("User not found");
        }

    }
}

