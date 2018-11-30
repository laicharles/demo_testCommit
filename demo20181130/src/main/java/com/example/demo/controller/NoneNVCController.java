package com.example.demo.controller;

import com.example.demo.annotation.AfsCheck;
import com.example.demo.dto.ResponDTO;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("nonenvc")
public class NoneNVCController {


    @RequestMapping("/outMessage")
    //调用无痕验证注解
    @AfsCheck()
    public JSONPObject getNvcAnalyzeMessage(@RequestParam String a, @RequestParam String callback) {

        //实际的业务功能
        ResponDTO dto = new ResponDTO();
        dto.setSuccess(true);
        ResponDTO.ResultSovl resultSovl = new ResponDTO.ResultSovl(true, "ok", "200");

        dto.setResult(resultSovl);


        return new JSONPObject(callback, dto);


    }
}
