package com.sda.mvc.repository;

import com.sda.mvc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserRepository {
    List<User> userList;

    public UserRepository(List<User> userList) {
        this.userList = userList;
        User user1 = new User(1, "Alex", "ajelescu", "pasworrd", "alexandu.jelescu@yahoo.com", 38);
        User user2 = new User(2, "Oana", "olupea", "paswword", "oana.lupea@yahoo.com", 25);
        User user3 = new User(2, "Mihai", "mcodrin", "password", "mihai.codrin@yahoo.com", 20);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }
    // create, read, update, delete

    // create new user - save
    public void save(User user) {
        userList.add(user);
    }

//    // read
//    public User findUserById(int userId);
//
//    public  User findUserByUsername(String username);

    public List<User> findAllUsers() {


        return userList;
    }

    public User delete(int index) {
        User user = userList.remove(index);
        return user;
    }


}
