package com.example.demo.qerja.controller;


import com.example.demo.qerja.model.UserComputer;
import com.example.demo.qerja.security.JwtToken;
import com.example.demo.qerja.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
class LoginController {
    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    JwtToken jwtToken;

    @GetMapping("/generate-token")
    public ResponseEntity<Map<String, Object>> generate(@RequestBody UserComputer userComputer) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if(userService.findUser(userComputer)){
                map.put("token", jwtToken.generateToken(userComputer));
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

    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validate(
            @RequestHeader(value = "token", defaultValue = "") String token) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            map.put("status", jwtToken.validate(token));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            map.put("notif", e.getMessage());
            map.put("status", false);
            e.printStackTrace();
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

}
