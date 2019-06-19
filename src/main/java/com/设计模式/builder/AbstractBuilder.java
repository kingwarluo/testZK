package com.设计模式.builder;

import com.设计模式.builder.constant.MessageCodeConstant;
import com.设计模式.builder.exception.MessageException;
import com.设计模式.builder.exception.ValidationException;
import com.设计模式.builder.util.ValidationUtils;

import java.util.UUID;

/**
 * @author kingwarluo
 * 消息构建父类
 * @date 2019/1/11 16:52
 */
public abstract class AbstractBuilder implements Builder {

    AbstractBuilder(){}

    @Override
    public Message build() throws MessageException {
        try {
            check();
            getMessage().setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        } catch(MessageException e){
            throw e;
        } catch (Exception e ){
            throw new MessageException(e);
        }
        return getMessage();
    }

    /**
     * 校验参数
     */
    protected void check() throws MessageException {
        try {
            ValidationUtils.validate(getMessage());
        } catch(ValidationException e){
            throw new MessageException(MessageCodeConstant.FAILURE, e.getMessage());
        }
    }

    protected abstract BaseMessage getMessage();
}
