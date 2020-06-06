package br.com.livresbs.livres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LivresApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivresApplication.class, args);
	}

}
