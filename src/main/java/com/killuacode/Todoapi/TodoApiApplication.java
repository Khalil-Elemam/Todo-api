package com.killuacode.Todoapi;

import com.killuacode.Todoapi.entity.Todo;
import com.killuacode.Todoapi.entity.User;
import com.killuacode.Todoapi.enums.Role;
import com.killuacode.Todoapi.repository.TodoRepository;
import com.killuacode.Todoapi.repository.UserRepository;
import com.killuacode.Todoapi.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TodoApiApplication {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(TodoApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			JwtService jwtService,
			PasswordEncoder passwordEncoder,
			TodoRepository todoRepository
	) {
		return args -> {

			var user = User
					.builder()
					.email("khalil20201001@gmail.com")
					.firstName("Khalil")
					.role(Role.ADMIN)
					.lastName("Elemam")
					.password(passwordEncoder.encode("Admin100100"))
					.build();
			userRepository.save(user);
			var todos = List.of(
					Todo.builder().content("This is the first Todo").user(user).targetDate(LocalDate.now().plusDays(4)).build(),
					Todo.builder().content("This is the Second Todo").user(user).targetDate(LocalDate.now().plusDays(20)).build()

			);
			String token = jwtService.generateAccessToken(user);
			logger.info("User Token is {}", token);
			todoRepository.saveAll(todos);
		};
	}

}
