package com.example.cloneprojectsoc;

import com.example.cloneprojectsoc.security.entity.Permission;
import com.example.cloneprojectsoc.security.entity.User;
import com.example.cloneprojectsoc.security.service.UserServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class CloneProjectSocApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneProjectSocApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserServiceImp userServiceImp){
//		return args -> {
//			userServiceImp.savePermission(new Permission(null, "PERMISSION_READ", new ArrayList<>()));
//			userServiceImp.savePermission(new Permission(null, "PERMISSION_WRITE", new ArrayList<>()));
//
//			userServiceImp.saveUser(new User(null, "admin", "admin", new ArrayList<>()));
//
//			userServiceImp.addPermissionToUser("admin", "PERMISSION_WRITE");
//			userServiceImp.addPermissionToUser("admin", "PERMISSION_READ");
//
//		};
//	}

}
