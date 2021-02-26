package com.yz.work.common.exception;

/**
 * @Description:
 * @Author YangZheng.Zhang
 * @Date 2021/02/26
 **/
public class ServiceException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ServiceException(Integer responseCode) {
        this(responseCode, "");
    }

    /**
     * @param responseCode
     * @param message
     */
    public ServiceException(Integer responseCode, String message) {
        this(responseCode, message, null);
    }

    /**
     * @param responseCode
     * @param message
     */
    public ServiceException(Integer responseCode, String message, Object object) {
        super(responseCode, message, object, null);
    }


}
