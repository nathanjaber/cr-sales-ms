package com.sensedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
