package com.bruce_janet.om_rest.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class UserServiceProxy {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceProxy.class);

    @Value("${OMServerUrlPrex}")
    private String OMServerUrlPrex;

    @Autowired
    RestTemplate restTemplate;

    public String  Hash(){
        JSONObject postData = new JSONObject();

        postData.put("deviceCode", "hello");
        String url =  OMServerUrlPrex+ "/" + "userservice";
        try {
            JSONObject resultJson = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            return "ok";
        }catch (Exception e){
            return "error";
        }
    }
}
