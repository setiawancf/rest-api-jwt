package com.example.demo.qerja;

import com.example.demo.qerja.model.Employee;
import com.example.demo.qerja.model.UserComputer;
import com.example.demo.qerja.repository.EmployeeRepository;
import com.example.demo.qerja.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + employeeRepository.save(new Employee("Bilbo Baggins", "burglar")));
            log.info("Preloading " + employeeRepository.save(new Employee("Frodo Baggins", "thief")));
            log.info("Preloading " + userRepository.save(new UserComputer("bibi", "", "1234")));
            log.info("Preloading " + userRepository.save(new UserComputer("baba", "", "4321")));
        };
    }
}