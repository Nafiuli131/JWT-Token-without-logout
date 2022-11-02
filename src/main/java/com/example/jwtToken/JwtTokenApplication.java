package com.example.jwtToken;

import com.example.jwtToken.entity.User;
import com.example.jwtToken.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtTokenApplication {
	@Autowired
	private UserRepository userRepository;
	private static List<User> users ;
	@PostConstruct
	public void initUser(){
		 users = Stream.of(
				new User(101L, "nafiul", "123456", "nafiuli131@gmail.com"),
				new User(102L, "user1", "pwd1", "user1@gmail.com"),
				new User(103L, "user2", "pwd2", "user2@gmail.com"),
				new User(104L, "user3", "pwd3", "user3@gmail.com")
		).collect(Collectors.toList());
		 userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenApplication.class, args);
		System.out.println(users);
	}

}
