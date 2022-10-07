package com.example.demo.qerja.controller;


import com.example.demo.qerja.exception.EmployeeNotFoundException;
import com.example.demo.qerja.model.Employee;
import com.example.demo.qerja.repository.EmployeeRepository;
import com.example.demo.qerja.security.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/job")
class JobController {
    public static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    JwtToken jwtToken;

    @GetMapping("/list")
    ResponseEntity<Map<String, Object>> all(
            @RequestHeader(value = "token", defaultValue = "") String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (jwtToken.validate(token)) {
                String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
                RestTemplate restTemplate = new RestTemplate();
                Object[] jobs = restTemplate.getForObject(url, Object[].class);
                map.put("results", Arrays.asList(jobs));
                map.put("status", true);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            map.put("notif", e.getMessage());
            map.put("status", false);
            e.printStackTrace();
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> findOne(
            @RequestHeader(value = "token", defaultValue = "") String token,
            @PathVariable String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (jwtToken.validate(token)) {
                String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id;
                RestTemplate restTemplate = new RestTemplate();
                Object job = restTemplate.getForObject(url, Object.class);
                map.put("results", job);
                map.put("status", true);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            map.put("notif", e.getMessage());
            map.put("status", false);
            e.printStackTrace();
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
