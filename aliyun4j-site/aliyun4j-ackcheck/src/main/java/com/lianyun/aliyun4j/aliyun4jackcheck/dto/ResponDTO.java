package com.lianyun.aliyun4j.aliyun4jackcheck.dto;

/**
 * @param
 * @author charleslai@139.com
 * @date 2018-12-03
 * @return
 */

public class ResponDTO {


    private boolean success;


    private ResultSovl result;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultSovl getResult() {
        return result;
    }

    public void setResult(ResultSovl result) {
        this.result = result;
    }


    public static class ResultSovl {

        private boolean success;


        private String msg;


        private String code;


        public ResultSovl(boolean success, String msg, String code) {
            this.success = success;
            this.msg = msg;
            this.code = code;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
