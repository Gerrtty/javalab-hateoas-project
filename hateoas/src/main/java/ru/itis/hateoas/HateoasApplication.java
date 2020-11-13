package ru.itis.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HateoasApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(HateoasApplication.class, args);

    }
}
