package com.设计模式.builder.util;

import com.设计模式.builder.annotation.CollectionValidate;
import com.设计模式.builder.annotation.StringValidate;
import com.设计模式.builder.annotation.Validate;
import com.设计模式.builder.exception.ValidationException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import sun.reflect.misc.FieldUtil;
import java.lang.reflect.Field;

/**
 * 实体验证类
 * @author kingwarluo
 * @date 2019/1/11 16:27
 */
public class ValidationUtils {

    /**
     * 验证bean
     * @param bean
     */
    public static void validate(Object bean) throws ValidationException {
        if (bean == null) {
            throw new ValidationException("bean cannot be null");
        }

        for (Field field : FieldUtil.getDeclaredFields(bean.getClass())){
            validateField(field, bean);
        }
    }

    /**
     * 验证字段
     * @param field
     */
    public static void validateField(Field field, Object bean) {
        if(field == null){
            throw new ValidationException("field cannot be null");
        }
        /**
         * 当isAccessible()的结果是false时不允许通过反射访问该字段，所以设置成true
         */
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        try {
            valueValidate(field.getAnnotation(Validate.class), field.get(bean));
            stringValidate(field.getAnnotation(StringValidate.class), field.get(bean));
            collectionValidate(field.getAnnotation(CollectionValidate.class), field.get(bean));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void collectionValidate(CollectionValidate validate, Object value) {
        if (validate == null) {
            return;
        }
        if(validate.empty()){
            return;
        }
        int size = 0;
        try{
            size = CollectionUtils.size(value);
        } catch(IllegalArgumentException e){
            throw new ValidationException("不支持的类型");
        }
        if(!validate.empty() && size == 0){
            throw new ValidationException("元素个数不能为0");
        }
        if(size < validate.min()){
            throw new ValidationException(String.format("元素个数不能小于%s", validate.min()));
        }
        if(size > validate.max()){
            throw new ValidationException(String.format("元素个数不能大于%s", validate.max()));
        }
    }

    public static void stringValidate(StringValidate validate, Object value) {
        if(validate == null){
            return;
        }
        if(!(value instanceof String)){
            throw new ValidationException("类型错误");
        }
        if (!validate.empty() && !validate.blank()) {
            return;
        }
        String stringvalue = String.valueOf(value);
        if(validate.empty() && StringUtils.isEmpty(stringvalue)){
            throw new ValidationException("值不能为空");
        }
        if(validate.blank() && StringUtils.isBlank(stringvalue)){
            throw new ValidationException("不能为空字符串");
        }
    }

    public static void valueValidate(Validate validate, Object value) {
        if(validate == null){
            return;
        }
        if(validate.nullable() && value == null){
            throw new ValidationException("值不能为空");
        }
    }

}
