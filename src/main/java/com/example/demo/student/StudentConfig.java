package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args->{

            Student jim= new Student(1L,"DingJian","dingjian412@gmail.com", LocalDate.of(2000, Month.JANUARY,5));

            Student dj= new Student("dj","dj412@gmail.com", LocalDate.of(2001, Month.JANUARY,21));

            studentRepository.saveAll(List.of(jim,dj));
        };
    };
}