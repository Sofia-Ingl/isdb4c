package org.example.isdb4c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class IsbdApplication {
    public static void main(String[] args) {
        SpringApplication.run(IsbdApplication.class, args);
        System.out.println("Here");
    }

}
