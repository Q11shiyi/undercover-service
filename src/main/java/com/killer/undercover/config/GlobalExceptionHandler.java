package com.killer.undercover.config;

import cn.hutool.core.util.StrUtil;
import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.common.exception.ErrorCodeEnum;
import com.killer.undercover.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @author xuhaoming
 * @since 2020/04/22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public Response businessException(HttpServletRequest req, BusinessException e) {
        log.error(e.getMsg());
        return Response.fail(e.getCode(), e.getMsg());
    }

    /**
     * Controller参数校验
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException:", e);
        StringBuilder message = new StringBuilder();
        if (e.getBindingResult() != null && !CollectionUtils.isEmpty(e.getBindingResult().getAllErrors())) {
            for (ObjectError error : e.getBindingResult().getAllErrors()) {
               message.append(error.getDefaultMessage()).append(";");
            }
        } else {
            message.append(e.getMessage());
        }
        return Response.fail(ErrorCodeEnum.AUTHENTICATION_FAILED.getCode(), ErrorCodeEnum.AUTHENTICATION_FAILED.getMessage() + "： " + message);
    }

    /**
     * Service参数校验
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationException(ConstraintViolationException e) {
        log.error("constraintViolationException:", e);
        String message = formatConstraintViolationException(e);
        return Response.fail(ErrorCodeEnum.AUTHENTICATION_FAILED.getCode(), ErrorCodeEnum.AUTHENTICATION_FAILED.getMessage() + "：" + message);
    }

    /**
     * 文件上传大小超出限制异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Response maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("maxUploadSizeExceededException:", e);
        String detailMessage = this.getDetailMessage(e);
        return Response.fail(9999, "超出文件上传大小最大值5M" , detailMessage);
    }


    /**
     * 其余异常
     */
    @ExceptionHandler(Exception.class)
    public Response globalException(Exception e) {
        log.error("", e);
        String detailMessage = this.getDetailMessage(e);
        return Response.fail(9999, "请求错误" , detailMessage);
    }

    /**
     * 格式化validation约束异常信息
     * @param e 异常
     * @return 异常信息 e.g. name不能为空;size不能小于0
     */
    public static String formatConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        String detailMessage = e.getMessage();
        if (StrUtil.isNotBlank(detailMessage)) {
            String[] errorList = detailMessage.split(",");
            for (String error : errorList) {
                message.append(error.substring(error.indexOf(":") + 1)).append(";");
            }
        }
        return message.toString();
    }

    /**
     * 获取异常详细信息(仅dev环境打印调试)
     * @param e 异常
     * @return 异常类型 + 异常信息 + 堆栈信息
     */
    private String getDetailMessage(Exception e) {
        StringBuilder message = new StringBuilder();
        message.append(e).append(":").append(e.getMessage()).append(" ");
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            message.append(stackTraceElement.toString());
        }
        return message.toString();
    }

    private String getExceptionMsg(Throwable exception) {
        // 如果参数exception内仍有cause，递归获取异常信息
        Throwable cause = exception.getCause();
        if (cause != null) {
            return getExceptionMsg(cause);
        } else {
            return exception.getMessage();
        }
    }

}
