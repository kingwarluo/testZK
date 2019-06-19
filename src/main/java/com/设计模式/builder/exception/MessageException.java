package com.设计模式.builder.exception;

import com.设计模式.builder.constant.MessageCodeConstant;

/**
 * @author kingwarluo
 * 消息异常类
 * @date 2019/1/11 16:52
 */
public class MessageException extends RuntimeException {

    private Integer code = MessageCodeConstant.FAILURE;

    public MessageException(String message) {
        super(message);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MessageException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + String.format(" (code: %s) ", code);
    }

}
