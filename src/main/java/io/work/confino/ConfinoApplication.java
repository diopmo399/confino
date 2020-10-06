package io.work.confino;

import io.work.confino.models.Company;
import io.work.confino.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfinoApplication {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public static void main(String[] args) {
        SpringApplication.run(ConfinoApplication.class, args);
    }

}
