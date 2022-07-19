package com.sparta.week05_spring_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // @WebServlet 어노테이션이 동작하게 함
@SpringBootApplication
public class Week05SpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week05SpringProjectApplication.class, args);
    }

}
