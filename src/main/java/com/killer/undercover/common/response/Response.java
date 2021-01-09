package com.killer.undercover.common.response;

import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.common.exception.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用响应
 *
 * @author xuhaoming
 * @since 2020/6/2
 */
@Slf4j
@Data
@ApiModel(description = "通用响应类")
@SuppressWarnings("unused")
public class Response<D> implements Serializable {

    private static final ThreadLocal<DateFormat> SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @ApiModelProperty(value = "当前服务器时间")
    private String sysdate;

    @ApiModelProperty(value = "是否成功")
    private boolean success = true;

    @ApiModelProperty(value = "状态码")
    private int code = 200;

    @ApiModelProperty(value = "返回数据")
    private D results;

    @ApiModelProperty(value = "返回消息")
    private String message = "success";

    /**
     * 仅dev环境打印调试
     */
    @ApiModelProperty(value = "错误堆栈信息")
    private String stackTrace;

    public Response() {
        sysdate = SDF.get().format(new Date());
    }

    public Response(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    public Response(D results) {
        this();
        this.results = results;
    }


    public static <D> Response<D> success() {
        return new Response<>();
    }

    public static <D> Response<D> success(D data) {
        return new Response<>(data);
    }

    public static <D> Response<D> fail(int code, String message) {
        Response<D> response = new Response<>(code, message);
        response.setSuccess(false);
        return response;
    }

    public static <D> Response<D> fail(int code, String message, String stackTrace) {
        Response<D> response = new Response<>(code, message);
        response.setSuccess(false);
        response.setStackTrace(stackTrace);
        return response;
    }

    public static <D> Response<D> fail(ErrorCodeEnum e) {
        Response<D> response = new Response<>(e.getCode(), e.getMessage());
        response.setSuccess(false);
        return response;
    }

    /**
     * 请求成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    public D checkResponse() {
        if (code != SUCCESS_CODE) {
            log.error("请求失败: code={}, message={}", code, message);
            throw new BusinessException("请求失败");
        }
        return results;
    }
}
