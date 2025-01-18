package ch.hearc.jee2024.projetjees2devanthery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetJEES2DevantheryApplication {

	public static void main(String[] args) {
		System.out.println("before start");
		SpringApplication.run(ProjetJEES2DevantheryApplication.class, args);
		System.out.println("after start");
	}

}
