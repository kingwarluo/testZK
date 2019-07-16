package com.proxy.fieldConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:性别枚举类
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private final int index;

    private final String value;

    SexEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public int getIndex() {
        return this.index;
    }

    private static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        for (SexEnum sex : SexEnum.values()) {
            map.put(sex.getIndex(), sex.getValue());
        }
    }

    public static String getValue(Integer index) {
        if(index == null) {
            return null;
        }
        return map.get(index);
    }

}
