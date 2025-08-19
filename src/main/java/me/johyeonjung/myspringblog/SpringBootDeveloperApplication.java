package me.johyeonjung.myspringblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Application 클래스에 JPA Auditing 켜기
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootDeveloperApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }

}
