package com.spring.autoInject.beanDefinition;

import java.util.ArrayList;
import java.util.List;

public class CustomBaseMapper implements BaseMapper {

    private List<String> list = new ArrayList<String>();

    @Override
    public void add(String value) {
        list.add(value);
    }

    @Override
    public void remove(String key) {
        if(list.isEmpty()) {
            return;
        }
        list.remove(key);
    }
}
