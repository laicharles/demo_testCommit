package com.example.demo.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 */
public class TestAfsCheck {

    static IAcsClient client = null;

    @BeforeClass
    public static void setUp() throws Exception {
        //YOUR ACCESS_KEY、YOUR ACCESS_SECRET请替换成您的阿里云accesskey id和secret

        String ACCESS_SECRET = "vApXJPjKWIUfgqIhNgpXEvFGaTNjgg";
        String ACCESS_KEY = "LTAIwJOpkXB8eVyM";

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY, ACCESS_SECRET);
        client = new DefaultAcsClient(profile);

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");
    }

    @Test
    public void test() {
        AuthenticateSigRequest request = new AuthenticateSigRequest();

        String sessionId = "010B3TrWzwsdkM2Lqh18cIU9S6J9BWHxGR3PlfZLJ9myE-LzoTrXCE72sU5Fu4yccaA7azAeehc3ikTC4yFanex3XJXTdU4b4hKmcHBidLYCMEArzMGU3MlEwXLkJXhTnDqdckN081Qcfh6zj3H-sXgIKm8LKXFXzscIEZkVxQ7aw9bRm7P-r4jq8-OGs7ymae";
        String sig = "05XqrtZ0EaFgmmqIQes-s-CAMl_eyPNakh5GH9OYxoJ0c47Gq7C1PCu9DDkat8fSlP4kK9C851_tUK71B3cjGxgONCW6VsZS0aLehwtwax9qdc61lO4c5xF4cmdomh5dU68CHQNwpVES2iGTJD_p-15jPAIPD8Kl-cTSvy7uHOeSBz8bYLOp13Q7JkbErQbEyPanOvuEMCLwj2TjAX41NuFcCPdMrBcZpAbUUk8Fh21NbGrQXoISmDM-9Wu6B6th5mcdYkG6uGCuRWAWXK9EtPHADk4BLyzPmmllgWFhWraLmAJNJO0v4tlqpaK5xaym0xIk6qYZ5VZgIU4dzSmpVu6sM1FL9FzpFUV4FYFXZlOSS_8N77KN4YaigwCpZRhm9ZQ_7odEwGFR4mIUoDYn9tF4oK2m5z7tkLmTzGkFlqw8iR4GSvRNrIixJHUsfPSDqx";
        String token = "FFFF0N000000000072D6:1543386476247:0.2619049079903457";
        String scene = "nc_login";
        String appkey = "FFFF0N000000000072D6";
        String remoteip = "120.237.155.119";


        request.setSessionId(sessionId);// 必填参数，从前端获取，不可更改，android和ios只传这个参数即可
        request.setSig(sig);// 必填参数，从前端获取，不可更改
        request.setToken(token);// 必填参数，从前端获取，不可更改
        request.setScene(scene);// 必填参数，从前端获取，不可更改
        request.setAppKey(appkey);// 必填参数，后端填写
        request.setRemoteIp(remoteip);// 必填参数，后端填写

        try {
            AuthenticateSigResponse response = client.getAcsResponse(request);
            if (response.getCode() == 100) {
                System.out.println("验签通过");
            } else {
                System.out.println("验签失败");
            }
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
