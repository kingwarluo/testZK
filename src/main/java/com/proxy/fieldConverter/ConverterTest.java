package com.proxy.fieldConverter;

import com.proxy.fieldConverter.convertor.ConverterProxy;

/**
 * @description:转换器测试类
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
public class ConverterTest {

    public static void main(String[] args) {
        User user = new User();
        user.setSex(1);

        user = (User) ConverterProxy.getProxy(user);
        user.setSex(2);

    }

}
