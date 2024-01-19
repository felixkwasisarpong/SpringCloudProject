package com.example.demo.student;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository
    ){
        return args -> {
          Student mariam= new Student(
                 "Maria","felix@mail.com",
                 LocalDate.of(2000,Month.AUGUST,5)


         );
           Student felix = new Student(
                    "felix","mariam@mail.com",
                    LocalDate.of(2010,Month.AUGUST,15)


            );
           repository.saveAll(
                   List.of(mariam,felix)
           );
        };
    }
}
