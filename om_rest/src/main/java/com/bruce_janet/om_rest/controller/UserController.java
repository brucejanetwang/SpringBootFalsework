package com.bruce_janet.om_rest.controller;

import com.bruce_janet.om_rest.service.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    UserServiceProxy userServiceProxy;

    @GetMapping("/login")
    public String Hash(){
        return userServiceProxy.login();
    }


    @GetMapping("/hash")
    public String Hash(String sid){
        return userServiceProxy.hash(sid);
    }
}
