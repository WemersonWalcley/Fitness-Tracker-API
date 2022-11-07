package io.github.wemersonwalcley.CadastroDeUsuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CadastroDeUsuariosApplication {

	@GetMapping("/hello")
	public String helloWorld(){
		System.out.println("Hello, World!");
		return "Hello, World.";
	}

	public static void main(String[] args) {
		SpringApplication.run(CadastroDeUsuariosApplication.class, args);
	}

}
