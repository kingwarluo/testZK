package com.bean;

import lombok.Data;

/**
 * @description:测试在application.xml中，读取application.properties文件中配置
 * 将properties中的值注入到这个bean中
 *
 * 注意：要能在spring中配置bean的属性，必须设置getter和setter方法
 *
 * @author jianhua.luo
 * @date 2019/7/17
 */
@Data
public class SpringBeanConfig {

    private String name;

    private String mail;


}
