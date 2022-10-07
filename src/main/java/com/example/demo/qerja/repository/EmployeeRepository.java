package com.example.demo.qerja.repository;

import com.example.demo.qerja.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}