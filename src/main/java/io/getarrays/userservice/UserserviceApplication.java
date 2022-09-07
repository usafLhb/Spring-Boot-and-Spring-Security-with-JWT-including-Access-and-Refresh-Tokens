package io.getarrays.userservice;

import io.getarrays.userservice.entity.Role;
import io.getarrays.userservice.entity.User;
import io.getarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
		System.out.println("Deal");
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"Role_USER"));
			userService.saveRole(new Role(null,"Role_MANAGER"));
			userService.saveRole(new Role(null,"Role_ADMIN"));
			userService.saveRole(new Role(null,"Role_SUPER_ADMIN"));

			userService.saveUser(new User(null,"usaf lhb","usaf","123",new ArrayList<>()));
			userService.saveUser(new User(null,"eminem riles","riles","123",new ArrayList<>()));
			userService.saveUser(new User(null,"ali bizou","bizou","123",new ArrayList<>()));
			userService.saveUser(new User(null,"amigoss code","amigoss","123",new ArrayList<>()));

			userService.addRoleToUser("amigoss","Role_USER");
			userService.addRoleToUser("amigoss","Role_MANAGER");
			userService.addRoleToUser("riles","Role_MANAGER");
			userService.addRoleToUser("bizou","Role_ADMIN");
			userService.addRoleToUser("usaf","Role_SUPER_ADMIN");
			userService.addRoleToUser("usaf","Role_ADMIN");
			userService.addRoleToUser("usaf","Role_USER");


		};
	}

}
