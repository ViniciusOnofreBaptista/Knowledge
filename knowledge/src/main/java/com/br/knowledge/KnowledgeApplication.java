package com.br.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KnowledgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeApplication.class, args);
	}
	
	@GetMapping("/teste")
	public String testando() {
		return "Teste Ok";
	}
	
	@GetMapping("/primeiro/commit")
	public String primeiroCommit() {
		return "Primeiro Commit";
	}

}
