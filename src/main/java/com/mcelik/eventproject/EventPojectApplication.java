package com.mcelik.eventproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
                    @Info(title = "Event Project", version = "1.0", description = "API Event Project using Swagger 3",
                    contact = @Contact(
                            name = "Murat Celik",
                            email = "muratcelikk93@gmail.com"
                    )))
public class EventPojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventPojectApplication.class, args);
    }

}


