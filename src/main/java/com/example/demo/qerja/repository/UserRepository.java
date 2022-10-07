package com.example.demo.qerja.repository;

import com.example.demo.qerja.model.UserComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserComputer, Long>, JpaSpecificationExecutor<UserComputer> {

}