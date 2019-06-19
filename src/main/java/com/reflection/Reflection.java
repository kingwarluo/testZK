package com.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author kingwarluo
 * java反射机制测试类
 * @date 2019/1/22 9:58
 */
public class Reflection {

    public static void main(String[] args) throws NoSuchMethodException {
        Reflection ref = new Reflection();
        /**
         * 第一种，从实例中获取类
         */
        Class cls = ref.getClass();
        System.out.println(cls.getName());
        /**
         * 获取方法
         */
        Method method = cls.getMethod("mankind", null);
        /**
         * 获取方法所在类的名称
         */
        System.out.println(method.getDeclaringClass().getName());

        /**
         * 第二种获取类方式
         */
        Class clazz = ReflectClass.class;
        Method m = clazz.getMethod("value");
        System.out.println(m.getDeclaringClass() == Object.class);

        Class claz = ReflectInterface.class;
        Method clam = claz.getMethod("value");
        System.out.println("3." + (clam.getDeclaringClass() == Object.class));

        Method methodName = NormalClass.class.getMethod("hashCode");
        System.out.println(methodName.getDeclaringClass() == Object.class);

    }

    class NormalClass {
        public void getName(){
            System.out.println("name");
        }
    }

    class ReflectClass implements ReflectInterface{

        @Override
        public void value() {
            System.out.println("value");
        }
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface People{
        String value();
    }

    @People("mankind")
    public String mankind(){
        return "";
    }

}
