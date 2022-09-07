package io.getarrays.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class UserserviceApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplicationTests.class, args);
		System.out.println("Done");
	}

}

