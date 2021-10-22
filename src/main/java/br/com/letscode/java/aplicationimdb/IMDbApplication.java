package br.com.letscode.java.aplicationimdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IMDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMDbApplication.class, args);
    }

}
