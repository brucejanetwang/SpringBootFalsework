package com.brucejanet.springaop.controller;

import com.brucejanet.springaop.service.MakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private MakeDataService makeDataService;


    @RequestMapping("/index")
    public String index(){
        return  "index";
    }

    @RequestMapping("/save")
    public String save(Long age){
        int age2 = age.intValue();
        int age3 = makeDataService.inc(age2);
        return  "save "+ age3;
    }

    @RequestMapping("/insert")
    public String insert(int age){
        age = makeDataService.dec(age);
        return  "insert "+ age;
    }
}
