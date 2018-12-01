package com.example.demo.exception;

/*
 * @author charleslai@139.com
 * @date
 * @param
 * @return
 */

public class AfsCheckException extends RuntimeException {

    //aliyun  return  code
    private String exceptionCode;

    public AfsCheckException(String message, String exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
