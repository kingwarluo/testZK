package com.proxy.fieldConverter;

import com.proxy.fieldConverter.convertor.FieldDescriber;
import com.proxy.fieldConverter.convertor.FieldValueConverter;
import lombok.Data;

import java.util.Date;

/**
 * @description:用户实体
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer sex;

    private Date birthday;

    @FieldDescriber(value = "性别", converter = SexConverter.class)
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * public static 缺一不可，public标识符，static用于初始化init()方法
     */
    public static class SexConverter implements FieldValueConverter {

        @Override
        public Object convert(Object source) {
            int sex = source == null ? SexEnum.MALE.getIndex() : (int)source;
            return SexEnum.getValue(sex);
        }

    }

}
