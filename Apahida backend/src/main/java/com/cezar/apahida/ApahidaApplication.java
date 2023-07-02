package com.cezar.apahida;

import com.cezar.apahida.service.DishService;
import com.cezar.apahida.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApahidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApahidaApplication.class, args);
	}

}
