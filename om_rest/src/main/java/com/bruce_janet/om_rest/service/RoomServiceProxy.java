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


@Service("roomServiceProxy")
public class RoomServiceProxy {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceProxy.class);

    @Value("${business.OMServerUrlPrex}")
    private String OMServerUrlPrex;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserServiceProxy userServiceProxy;


    public String  count(String roomId){
        String url =  OMServerUrlPrex+ "/services/room/count/"+roomId+"?sid="+userServiceProxy.getSid();
        try {
            JSONObject resultJson = restTemplate.getForEntity(url, JSONObject.class).getBody();
            return resultJson.toString();
        }catch (Exception e){
            return "error";
        }
    }


    public String add(){
        String url =  OMServerUrlPrex+ "/services/room?sid="+userServiceProxy.getSid();
        JSONObject room = new JSONObject();
        room.put("type","conference");
        room.put("name","test2");
        room.put("capacity",12);
        room.put("comment","comment test");
        room.put("isPublic",true);
        room.put("allowRecording",true);
        room.put("allowUserQuestions",true);
      //  {"roomDTO":{"id":11,"name":"","comment":"","capacity":4,"appointment":false,"isPublic":false,"demo":false,"closed":false,"redirectUrl":"","moderated":false,"allowUserQuestions":false,"allowRecording":false,"waitForRecording":false,"audioOnly":false}}
        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        request.add("room",room.toJSONString());
        HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.valueOf((MediaType.APPLICATION_FORM_URLENCODED_VALUE)));
        HttpEntity<MultiValueMap<String, Object>> postData = new HttpEntity<MultiValueMap<String, Object>>(request, headers);
        try {
            JSONObject resultJson = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            return resultJson.toString();

        }catch (Exception e){
            return e.getMessage();
        }
    }

}
