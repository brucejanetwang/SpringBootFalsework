package com.bruce_janet.om_rest.controller;

import com.bruce_janet.om_rest.service.FileServiceProxy;
import com.bruce_janet.om_rest.service.UserServiceProxy;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    FileServiceProxy fileServiceProxy;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test(){
       // String url ="https://www.easy-mock.com/mock/5c3c2873637b5d07f1536e3b/example/users";
        String url ="https://www.easy-mock.com/mock/5c500ee783d1f70e7174ea27/example/getuser";
        return restTemplate.getForObject(url, String.class).toString();
    }

    @GetMapping("/add")
    public String add(){
        try {
            return fileServiceProxy.add();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


}
