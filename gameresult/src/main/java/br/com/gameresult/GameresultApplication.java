package br.com.gameresult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.gameresult"})
public class GameresultApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameresultApplication.class, args);
	}
}
