package com.yz.work.common.exception;

/**
 * @Description: 异常基类
 * @Author YangZheng.Zhang
 * @Date 2021/02/26
 **/
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //异常客户端返回代码
    private Integer responseCode;

    //异常数据
    private Object data;

    protected BaseException(Integer responseCode, String message, Object data, Throwable cause) {
        super(message, cause);
        this.setData(data);
        this.setResponseCode(responseCode);
    }

    public Integer getResponseCode() {
        return responseCode;
    }
    

    public BaseException setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseException setData(Object data) {
        this.data = data;
        return this;
    }
}