package com.example.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EnableConfigServer
public class ConfigurationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigurationServiceApplication.class, args);
  }
}
