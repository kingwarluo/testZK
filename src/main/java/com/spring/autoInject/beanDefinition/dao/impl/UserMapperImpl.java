package com.spring.autoInject.beanDefinition.dao.impl;

import com.spring.autoInject.beanDefinition.dao.UserMapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public void add(String value) {
        System.out.println("user add");
    }

    @Override
    public void remove(String key) {
        System.out.println("user remove");
    }
}
