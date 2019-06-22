package com.bruce_janet.om_rest.controller;

import com.bruce_janet.om_rest.service.RoomServiceProxy;
import com.bruce_janet.om_rest.service.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoomController {
    @Autowired
    RoomServiceProxy roomServiceProxy;

    @GetMapping("/count/{roomId}")
    public String Count(@PathVariable("roomId") String roomId){
        return roomServiceProxy.count(roomId);
    }

    @GetMapping("/closeUrl")
    public String closeUrl(){
        return "welcome back!";
    }

    @GetMapping("room/add")
    public String add(){
        return roomServiceProxy.add();
    }

}
