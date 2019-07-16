package com.proxy.fieldConverter.convertor;

/**
 * @description:字段和值转换接口
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
public interface FieldValueConverter {

    /**
     * @description:转换
     *
     * @author jianhua.luo
     * @date 2019/7/16
     */
    Object convert(Object source);

    class DefaultFieldValueConverter implements FieldValueConverter {

        @Override
        public Object convert(Object source) {
            return source;
        }

    }

}
