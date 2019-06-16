package com.bruce_janet.om_rest.controller;

import com.bruce_janet.om_rest.service.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceProxy userServiceProxy;

    @GetMapping("/login")
    public String Login(){
        return userServiceProxy.login();
    }

    @GetMapping("/hash/{roomId}")
    public String Hash( @PathVariable("roomId") String roomId){
        return userServiceProxy.hash(roomId);
    }


}
