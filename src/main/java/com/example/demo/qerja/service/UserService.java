package com.example.demo.qerja.service;

import com.example.demo.qerja.exception.UserNotFoundException;
import com.example.demo.qerja.model.UserComputer;
import com.example.demo.qerja.repository.UserRepository;
import com.example.demo.qerja.repository.criteria.SearchCriteria;
import com.example.demo.qerja.repository.spec.UserSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }
    public boolean findUser(UserComputer userComputer) {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("username", ":", userComputer.getUsername()));
        List<UserComputer> result = repository.findAll(spec);
        if(result.size()!=0){
            return true;
        }else{
            throw new UserNotFoundException(userComputer.getUsername());
        }
    }
}
