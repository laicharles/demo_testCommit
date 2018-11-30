package com.example.demo.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AnalyzeNvcRequest;
import com.aliyuncs.afs.model.v20180112.AnalyzeNvcResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AfsCheckUtils {
    @Value("${org.aliyun.sdk.nc.accessKey}")
    private String accessKey;
    @Value("${org.aliyun.sdk.nc.accessSecret}")
    private String accessSecret;
    @Value("${org.aliyun.sdk.nc.endpoinName}")
    private String endpoinName;
    @Value("${org.aliyun.sdk.nc.reqionId}")
    private String reqionId;
    @Value("${org.aliyun.sdk.nc.product}")
    private String product;
    @Value("${org.aliyun.sdk.nc.domain}")
    private String domain;

    public String getNvcAnalyzeMessage(String a, String callback) {
        IClientProfile profile = DefaultProfile.getProfile(reqionId, accessKey, accessSecret);
        IAcsClient client = null;
        //YOUR ACCESS_KEY、YOUR ACCESS_SECRET请替换成您的阿里云accesskey id和secret
        //IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "YOUR ACCESS_KEY", "YOUR ACCESS_SECRET");
        client = new DefaultAcsClient(profile);

        try {
            DefaultProfile.addEndpoint(endpoinName, reqionId, product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        AnalyzeNvcRequest request = new AnalyzeNvcRequest();
        request.setData(a);//必填参数，前端获取getNVCVal函数的值
        request.setScoreJsonStr("{\"200\":\"PASS\",\"400\":\"NC\",\"600\":\"SC\",\"800\":\"BLOCK\"}");// 根据需求填写
        AnalyzeNvcResponse response = null;
        try {
            response = client.getAcsResponse(request);

            String bizCode = response.getBizCode();
            if ("100".equals(bizCode)) {
                System.out.println("验签通过");
            } else if ("200".equals(bizCode)) {
                System.out.println("直接通过");
            } else if ("400".equals(bizCode)) {
                System.out.println("前端弹出nc");
            } else if ("600".equals(bizCode)) {
                System.out.println("前端弹出sc");
            } else if ("800".equals(bizCode)) {
                System.out.println("直接拦截");
            } else if ("900".equals(bizCode)) {
                System.out.println("验签失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.getBizCode();
    }


}
