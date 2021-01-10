package com.killer.undercover.common.exception;


/**
 * 业务异常
 *
 * @author xuhaoming
 * @since 2020/6/2
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.code = 9999;
        this.msg = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BusinessException(ErrorCodeEnum error) {
        this(error.getCode(),error.getMessage());
    }

    public BusinessException(ErrorCodeEnum error, String... params) {
        super(String.format(error.getMessage(), params));
        this.code = error.getCode();
        this.msg = String.format(error.getMessage(), params);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
