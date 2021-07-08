//package com.sda.demo;
//
//import com.sda.mvc.model.User;
//import com.sda.mvc.repository.UserRepository;
//import com.sda.mvc.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//
//import java.util.List;
//
//@SpringBootApplication
//@ComponentScan(basePackages = "com.sda.demo")
//public class DemoProjectApplication {
//	@Autowired
//	private  static UserRepository userRepository;
//	public static void main(String[] args) {
//
//		SpringApplication.run(DemoProjectApplication.class, args);
//
//		UserService userService = new UserService(userRepository);
//
//
//		List<User> usersInAgeGroup = userService.findUsersInAgeGroup(20, 50);
//
//		System.out.println(usersInAgeGroup);
//
//	}
//
//}
