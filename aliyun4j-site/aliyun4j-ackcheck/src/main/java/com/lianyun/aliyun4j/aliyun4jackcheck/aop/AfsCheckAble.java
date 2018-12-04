package com.lianyun.aliyun4j.aliyun4jackcheck.aop;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lianyun.aliyun4j.aliyun4jackcheck.dto.ResponDTO;
import com.lianyun.aliyun4j.aliyun4jackcheck.exception.AfsCheckException;
import com.lianyun.aliyun4j.aliyun4jackcheck.utils.AfsCheckUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author charleslai@139.com
 * @date 2018-12-03
 * @param
 * @return
 */


@Aspect
@Component
public class AfsCheckAble {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AfsCheckUtils afsCheckUtils;


    @Pointcut("@annotation(com.lianyun.aliyun4j.aliyun4jackcheck.annotation.AfsCheck)")
    public void pointcut() {
    }


    @Around(value = "pointcut()")
    public Object AroudProccess(ProceedingJoinPoint pjp) throws Throwable {

        //1.获取到所有的参数值的数组
        ResponDTO dto = new ResponDTO();
        JSONPObject jsonpObject = null;
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //2.获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        // Method method = methodSignature.getMethod();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0, len = parameterNames.length; i < len; i++) {

            map.put(parameterNames[i], args[i].toString());
            // System.out.println("参数名：" + parameterNames[i] + " = " + args[i]);
        }

        String bizCode = afsCheckUtils.getNvcAnalyzeMessage(map.get(parameterNames[0]), map.get(parameterNames[1]));
        logger.info("aliyun  return code " + bizCode);
        //bizCode = "400";
        //判断是否校验合法,由阿里云检测
        if ("200".equals(bizCode) || "100".equals(bizCode)) {
            return pjp.proceed();
        } else {
            /*dto.setSuccess(true);
            ResponDTO.ResultSovl resultSovl = new ResponDTO.ResultSovl(true, "ok", bizCode);
            dto.setResult(resultSovl);*/

            throw new AfsCheckException("aliyun AfcCheck Exception", bizCode);
        }

        //return new JSONPObject(map.get(parameterNames[1]), dto);

    }
}
