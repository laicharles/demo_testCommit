package com.example.facdemo;

import com.lianyun.aliyun4j.aliyun4jackcheck.annotation.AfsCheck;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author charleslai@139.com
 * @Title: TestController
 * @ProjectName facdemo
 * @Description: TODO
 * @date 2018/12/314:39
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    @AfsCheck
    public String sayHello(String a, String callback) {


        return "hello";
    }
}
