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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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


    private String mysid;

    public String  login(){
        JSONObject postData = new JSONObject();

        String url =  OMServerUrlPrex+ "/services/user/login?user=om_admin&pass=1Q2w3e4r5t^y";
        try {
            JSONObject resultJson = restTemplate.getForEntity(url, JSONObject.class).getBody();
           // JSONObject resultJson = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            mysid = resultJson.getJSONObject("serviceResult").getString("message");
            return resultJson.toString();
        }catch (Exception e){
            return "error";
        }
    }

    public String  hash(String sid){
        if(sid==null){
            sid = this.mysid;
        }
        JSONObject postData = new JSONObject();

        JSONObject user_zj_extern  =  new JSONObject();
        user_zj_extern.put("login","zhoujia");
        user_zj_extern.put("externalId","4");
        user_zj_extern.put("externalType","external");


        JSONObject user   =  new JSONObject();
        user.put("login","huanglin");
        user.put("externalId","6");
        user.put("externalType","user");

        JSONObject options  =  new JSONObject();
        options.put("roomId",7);
        options.put("moderator",false);


        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        request.add("user",user.toJSONString());
        request.add("options",options.toJSONString());

        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.valueOf((MediaType.APPLICATION_FORM_URLENCODED_VALUE)));

        HttpEntity<MultiValueMap<String, Object>> postData2 = new HttpEntity<MultiValueMap<String, Object>>(request, headers);

        String url =  OMServerUrlPrex+ "/services/user/hash?sid="+sid;
        try {
            JSONObject resultJson = restTemplate.postForEntity(url, postData2, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            String hash = resultJson.getJSONObject("serviceResult").getString("message");
            return OMServerUrlPrex+ "hash?secure="+hash;
        }catch (Exception e){
            return "error";
        }
    }


}
