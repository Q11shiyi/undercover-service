package com.killer.undercover.common.exception;

/**
 * <p>Title: ErrorCodeEnum</p>
 * <p>Description: 错误码枚举</p>
 * @author: huanghuiqiang
 * @create: 21.1.9 10:24
 */

public enum ErrorCodeEnum {

    /**
     * 系统通用异常
     */
    AUTHENTICATION_FAILED(500,"系统异常，可爱的攻城狮正常努力修复中~"),

    /**
     * 房间异常
     */
    ROOM_NOT_FIND(601,"房间不存在，请确认输入的房间号是否正确哦~")
    ;

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}