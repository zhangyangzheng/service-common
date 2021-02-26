package com.yz.work.common.exception;

/**
 * @Description:
 * @Author YangZheng.Zhang
 * @Date 2021/02/26
 **/
public class ErrorException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ErrorException(Integer responseCode) {
        this(responseCode, "");
    }

    /**
     * @param responseCode
     * @param message
     */
    public ErrorException(Integer responseCode, String message) {
        this(responseCode, message, new Object[]{message});
    }

    /**
     * @param responseCode
     * @param message
     * @param data
     */
    public ErrorException(Integer responseCode, String message, Object data) {
        super(responseCode, message, data, null);
    }

}