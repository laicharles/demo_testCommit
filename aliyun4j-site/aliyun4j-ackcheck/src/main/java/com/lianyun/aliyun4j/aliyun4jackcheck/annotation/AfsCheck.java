package com.lianyun.aliyun4j.aliyun4jackcheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @param
 * @author charleslai@139.com
 * @date 2018-12-03
 * @return
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AfsCheck {
}
