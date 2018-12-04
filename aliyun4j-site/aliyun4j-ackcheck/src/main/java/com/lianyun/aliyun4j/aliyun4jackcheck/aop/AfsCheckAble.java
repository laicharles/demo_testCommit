package com.lianyun.aliyun4j.aliyun4jackcheck.aop;


import com.lianyun.aliyun4j.aliyun4jackcheck.exception.AfsCheckException;
import com.lianyun.aliyun4j.aliyun4jackcheck.utils.AfsCheckUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @param
 * @author charleslai@139.com
 * @date 2018-12-03
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

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String getNVCVal = request.getHeader("getNVCVal");

        if (getNVCVal != null) {
            String bizCode = afsCheckUtils.getNvcAnalyzeMessage(getNVCVal);
            logger.info("aliyun  return code " + bizCode);
            //bizCode = "400";
            //判断是否校验合法,由阿里云检测
            if ("200".equals(bizCode) || "100".equals(bizCode)) {
                return pjp.proceed();
            } else {
                logger.info(" lianyun  throw  code " + bizCode);
                throw new AfsCheckException("aliyun AfcCheck Exception", bizCode);

            }
        } else {
            throw new IllegalArgumentException("getNVCVal  is null ");
        }


    }
}
