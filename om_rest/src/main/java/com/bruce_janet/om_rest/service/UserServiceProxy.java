package com.bruce_janet.om_rest.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service("userServiceProxy")
public class UserServiceProxy {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceProxy.class);

    @Value("${business.OMServerUrlPrex}")
    private String OMServerUrlPrex;

    @Autowired
    RestTemplate restTemplate;

    public String  login(){
        JSONObject postData = new JSONObject();

        String url =  OMServerUrlPrex+ "/user/login?user=om_admin&pass=1Q2w3e4r5t^y";
        try {
            JSONObject resultJson = restTemplate.getForEntity(url, JSONObject.class).getBody();
           // JSONObject resultJson = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            return "ok";
        }catch (Exception e){
            return "error";
        }
    }

    public String  hash(String sid){
        JSONObject postData = new JSONObject();
        JSONObject user  =  new JSONObject();
        JSONObject options  =  new JSONObject();
        user.put("login","huanglin");
        user.put("externalId","4");
        user.put("externalType","user");
        options.put("roomId",7);
        options.put("moderator",false);

        HashMap<String,JSONObject> request = new   HashMap<String,JSONObject>();

        request.put("user",user);
        request.put("options",options);

        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType((MediaType.APPLICATION_JSON));
        HttpEntity<Map<String, JSONObject>> postData2 = new HttpEntity<Map<String, JSONObject>>(request, headers);
        String url =  OMServerUrlPrex+ "/user/hash?sid="+sid;
        try {
            JSONObject resultJson = restTemplate.postForEntity(url, postData2, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            return "ok";
        }catch (Exception e){
            return "error";
        }
    }


}
