package com.project.literalura;

import com.project.literalura.principal.Principal;
import com.project.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepositorio;

	public static void main(String[] args) {SpringApplication.run(LiteraluraApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepositorio);
		principal.mostrarMenu();
	}

}
