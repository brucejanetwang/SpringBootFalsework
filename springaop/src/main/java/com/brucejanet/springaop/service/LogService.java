package com.brucejanet.springaop.service;

import org.springframework.stereotype.Service;

@Service
public class LogService {
    public String info(String info){
        System.out.println(info);
        return "info:" +  info;
    }

    public String error(String error){
        System.out.println(error);
        return "error:"+ error;
    }
}
