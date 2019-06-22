package com.bruce_janet.om_rest.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;


@Service("fileServiceProxy")
public class FileServiceProxy {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceProxy.class);

    @Value("${business.OMServerUrlPrex}")
    private String OMServerUrlPrex;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserServiceProxy userServiceProxy;

    public String add() throws IOException {
        //意外的元素 (uri:"", local:"name")。所需元素为<{}fileItemDTO>
        String url =  OMServerUrlPrex+ "/services/file?sid="+userServiceProxy.getSid();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type",  "multipart/form-data; charset=utf-8");
        HttpClient httpClient = new DefaultHttpClient();

        String filepath = "/Users/bruce/a.png";
        FileBody fundFileBin = new FileBody(new File(filepath),"application/octet-stream");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
       // builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset( Charset.forName("utf-8"));
        //builder.setBoundary()
        builder.addPart("stream", fundFileBin);

        JSONObject file = new JSONObject();
        file.put("type","Image");
        file.put("name","helle.png");
        file.put("roomId",7);
        String filestr = file.toString();
        StringBody  stringBody = StringBody.create(filestr, "application/json", Charset.forName("utf-8"));

        builder.addPart("file",stringBody);

        post.setEntity(builder.build());
        org.apache.http.HttpResponse response = httpClient.execute(post);
         // 检验返回码
        int statusCode = response.getStatusLine().getStatusCode();

        return "result="+statusCode;
    }
    public String add2(){
        String url =  OMServerUrlPrex+ "/services/file?sid="+userServiceProxy.getSid();
        JSONObject file = new JSONObject();
        file.put("type","Image");
        file.put("name","helle.png");
        file.put("roomId",7);

         MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();

        request.add("file",file.toJSONString());
        String filepath = "/Users/bruce/a.png";
        FileSystemResource fileSystemResource = new FileSystemResource(new File( filepath));
        request.add("stream", fileSystemResource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
      //  HttpEntity<MultiValueMap<String, Object>> postData = new HttpEntity<MultiValueMap<String, Object>>(request, headers);


        try {

            JSONObject resultJson =  null ; //restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
            logger.info("resultJson:"+resultJson.toString());
            return resultJson.toString();

        }catch (Exception e){
            return e.getMessage();
        }
    }

}
