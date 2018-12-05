package com.lianyun.aliyun4j.aliyun4jackcheck.interfaces;

import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author charleslai@139.com
 * @desc 阿里云无痕验证获取 NVCVal值
 */

@Repository
public interface AfsNVCVal {

    public String getNVCVal(HttpServletRequest request);


}




