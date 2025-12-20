package com.astraval.backend.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @PostMapping("/add")
    public String addNewUser(@RequestBody String entity) {
        
        
        return entity;
    }
    
}
