package com.example.demo.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AnalyzeNvcRequest;
import com.aliyuncs.afs.model.v20180112.AnalyzeNvcResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.BeforeClass;
import org.junit.Test;


public class None {

    public class TestAfsCheck {

        IAcsClient client = null;

        @BeforeClass
        public void setUp() throws Exception {
            //YOUR ACCESS_KEY、YOUR ACCESS_SECRET请替换成您的阿里云accesskey id和secret
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "YOUR ACCESS_KEY", "YOUR ACCESS_SECRET");
            client = new DefaultAcsClient(profile);

            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");
        }

        @Test
        public void test() {
            AnalyzeNvcRequest request = new AnalyzeNvcRequest();
            request.setData("xxx");//必填参数，前端获取getNVCVal函数的值
            request.setScoreJsonStr("{\"200\":\"PASS\",\"400\":\"NC\",\"600\":\"SC\",\"800\":\"BLOCK\"}");// 根据需求填写

            try {
                AnalyzeNvcResponse response = client.getAcsResponse(request);

                if (response.getBizCode() == "100")
                    if (response.getBizCode() == "100") {
                        System.out.println("验签通过");
                    } else if (response.getBizCode() == "200") {
                        System.out.println("直接通过");
                    } else if (response.getBizCode() == "400") {
                        System.out.println("前端弹出nc");
                    } else if (response.getBizCode() == "600") {
                        System.out.println("前端弹出sc");
                    } else if (response.getBizCode() == "800") {
                        System.out.println("直接拦截");
                    } else if (response.getBizCode() == "900") {
                        System.out.println("验签失败");
                    }
                // TODO
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
