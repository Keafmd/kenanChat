package com.keafmd.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.keafmd.model.Response;
import com.keafmd.util.HttpUtils;

import java.io.IOException;
import java.net.URLEncoder;


/**
 * Keafmd
 *
 * @ClassName: QingYunkeRobotServiceImpl
 * @Description: 青云客
 * @author: 牛哄哄的柯南
 * @date: 2021-08-05 9:28
 */
public class QingYunkeRobotServiceImpl implements RobotService{

    //青云客平台的API地址
    private String uri = "https://api.qingyunke.com/api.php?key=free&appid=0&msg=%s";
    private String api;



    @Override
    public Response answer(String question) throws IOException {

        try{
            api = String.format(uri, URLEncoder.encode(question,"UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

        String ans = HttpUtils.request(api);

        if(!ans.contains("出现错误")){

//            JSONObject userJson = JSONObject.parseObject(userString);
//            User user = JSON.toJavaObject(userJson,User.class);
            JSONObject responseObject = JSONObject.parseObject(ans);
            Response response = JSON.toJavaObject(responseObject,Response.class);
            return response;
        }else{
            Response response = new Response();
            response.setContent(ans);
            return response;
        }

    }
}
