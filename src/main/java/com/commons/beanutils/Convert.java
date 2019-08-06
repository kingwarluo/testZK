package com.commons.beanutils;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试ConvertUtils、BeanUtils
 * 
 * @author jianhua.luo
 * @date 2019/8/6
 */
public class Convert {

    public static void main(String[] args) {
        try {
            registerDateConverter();
            mapToObject();
            setProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mapToObject() throws Exception {
        Map map = new HashMap();
        map.put("name", "李四");
        //设置日期类型
        map.put("birthday", "2019-22-11");

        User user = new User();
        //将Map集合注入到JavaBean
        BeanUtils.populate(user, map) ;
        System.out.println(user);
    }

    private static void setProperties() throws Exception {
        User user = new User();
        //设置String类型的属性
        BeanUtils.setProperty(user, "name", "张三");
        //设置int类型属性
        BeanUtils.setProperty(user, "age", 25);
        //设置Date类型的属性
        BeanUtils.setProperty(user, "birthday", "2019-99-11");
        System.out.println(user);
    }

    private static void registerDateConverter() {
        //注册String到Date类型转换器
        ConvertUtils.register(new Converter() {
            @Override
            public Date convert(Class aClass, Object o) {
                if(aClass != Date.class) {
                    return null;
                }
                if(o == null || "".equals(o.toString().trim())) {
                    return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse((String) o);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        }, Date.class);
    }

    @Data
    public static class User implements Serializable {
        private static final long serialVersionUID = -1291553376171313152L;

        private String name ;
        private int age ;
        private boolean gender ;   //性别：trye-男，false-女
        private Date birthday ; //生日
        private String[] hobbies ; //爱好
        private String address ;
        private List<String> strong ; //特长
        private Map<String, String> fault ; //缺点
    }

    @Data
    public static class Student implements Serializable {
        private String name;
    }

}
