package br.com.livresbs.livres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan
@Configuration
public class LivresApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivresApplication.class, args);
	}

}
