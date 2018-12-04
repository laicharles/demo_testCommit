package com.lianyun.aliyun4j.aliyun4jackcheck.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AnalyzeNvcRequest;
import com.aliyuncs.afs.model.v20180112.AnalyzeNvcResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @param
 * @author charleslai@139.com
 * @date 2018-12-03
 * @return
 */

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
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getNvcAnalyzeMessage(String getNVCVal) {
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
        request.setData(getNVCVal);//必填参数，前端获取getNVCVal函数的值
        request.setScoreJsonStr("{\"200\":\"PASS\",\"400\":\"NC\",\"600\":\"SC\",\"800\":\"BLOCK\"}");// 根据需求填写
        AnalyzeNvcResponse response = null;
        String bizCode ="";
        try {
            response = client.getAcsResponse(request);

             bizCode = response.getBizCode();
            logger.info("aliyun  return code  " + bizCode);
            return bizCode;
        } catch (Exception e) {
            logger.error("aliyun  return errorMess  "+e.getMessage());
        }

        return bizCode;
    }


}
