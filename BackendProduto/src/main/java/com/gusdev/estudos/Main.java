package com.gusdev.estudos;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Arquivo Principal do projeto, levanta e starta a aplicação, primeiro arquivo a ser executado
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //Carra as variáveis de ambiente do arquivo .env
        Dotenv dotenv = Dotenv.load();

        //Define as variáveis como propriedades do sistema, se necessário
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));

        SpringApplication.run(Main.class, args);
    }
}